/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac;

import com.github.alexthe666.citadel.repack.jaad.aac.AACException;
import com.github.alexthe666.citadel.repack.jaad.aac.ChannelConfiguration;
import com.github.alexthe666.citadel.repack.jaad.aac.Profile;
import com.github.alexthe666.citadel.repack.jaad.aac.SampleFrequency;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.BitStream;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.Constants;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.PCE;

public class DecoderConfig
implements Constants {
    private Profile profile = Profile.AAC_MAIN;
    private Profile extProfile = Profile.UNKNOWN;
    private SampleFrequency sampleFrequency = SampleFrequency.SAMPLE_FREQUENCY_NONE;
    private ChannelConfiguration channelConfiguration = ChannelConfiguration.CHANNEL_CONFIG_UNSUPPORTED;
    private boolean frameLengthFlag = false;
    private boolean dependsOnCoreCoder;
    private int coreCoderDelay;
    private boolean extensionFlag;
    private boolean sbrPresent = false;
    private boolean downSampledSBR = false;
    private boolean sbrEnabled = true;
    private boolean sectionDataResilience = false;
    private boolean scalefactorResilience = false;
    private boolean spectralDataResilience = false;

    private DecoderConfig() {
    }

    public ChannelConfiguration getChannelConfiguration() {
        return this.channelConfiguration;
    }

    public void setChannelConfiguration(ChannelConfiguration channelConfiguration) {
        this.channelConfiguration = channelConfiguration;
    }

    public int getCoreCoderDelay() {
        return this.coreCoderDelay;
    }

    public void setCoreCoderDelay(int coreCoderDelay) {
        this.coreCoderDelay = coreCoderDelay;
    }

    public boolean isDependsOnCoreCoder() {
        return this.dependsOnCoreCoder;
    }

    public void setDependsOnCoreCoder(boolean dependsOnCoreCoder) {
        this.dependsOnCoreCoder = dependsOnCoreCoder;
    }

    public Profile getExtObjectType() {
        return this.extProfile;
    }

    public void setExtObjectType(Profile extObjectType) {
        this.extProfile = extObjectType;
    }

    public int getFrameLength() {
        return this.frameLengthFlag ? 960 : 1024;
    }

    public boolean isSmallFrameUsed() {
        return this.frameLengthFlag;
    }

    public void setSmallFrameUsed(boolean shortFrame) {
        this.frameLengthFlag = shortFrame;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public SampleFrequency getSampleFrequency() {
        return this.sampleFrequency;
    }

    public void setSampleFrequency(SampleFrequency sampleFrequency) {
        this.sampleFrequency = sampleFrequency;
    }

    public boolean isSBRPresent() {
        return this.sbrPresent;
    }

    public boolean isSBRDownSampled() {
        return this.downSampledSBR;
    }

    public boolean isSBREnabled() {
        return this.sbrEnabled;
    }

    public void setSBREnabled(boolean enabled) {
        this.sbrEnabled = enabled;
    }

    public boolean isScalefactorResilienceUsed() {
        return this.scalefactorResilience;
    }

    public boolean isSectionDataResilienceUsed() {
        return this.sectionDataResilience;
    }

    public boolean isSpectralDataResilienceUsed() {
        return this.spectralDataResilience;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static DecoderConfig parseMP4DecoderSpecificInfo(byte[] data) throws AACException {
        BitStream in = new BitStream(data);
        DecoderConfig config = new DecoderConfig();
        try {
            config.profile = DecoderConfig.readProfile(in);
            int sf = in.readBits(4);
            config.sampleFrequency = sf == 15 ? SampleFrequency.forFrequency(in.readBits(24)) : SampleFrequency.forInt(sf);
            config.channelConfiguration = ChannelConfiguration.forInt(in.readBits(4));
            switch (config.profile) {
                case AAC_SBR: {
                    config.extProfile = config.profile;
                    config.sbrPresent = true;
                    sf = in.readBits(4);
                    config.downSampledSBR = config.sampleFrequency.getIndex() == sf;
                    config.sampleFrequency = SampleFrequency.forInt(sf);
                    config.profile = DecoderConfig.readProfile(in);
                    break;
                }
                case AAC_MAIN: 
                case AAC_LC: 
                case AAC_SSR: 
                case AAC_LTP: 
                case ER_AAC_LC: 
                case ER_AAC_LTP: 
                case ER_AAC_LD: {
                    config.frameLengthFlag = in.readBool();
                    if (config.frameLengthFlag) {
                        throw new AACException("config uses 960-sample frames, not yet supported");
                    }
                    config.dependsOnCoreCoder = in.readBool();
                    config.coreCoderDelay = config.dependsOnCoreCoder ? in.readBits(14) : 0;
                    config.extensionFlag = in.readBool();
                    if (config.extensionFlag) {
                        if (config.profile.isErrorResilientProfile()) {
                            config.sectionDataResilience = in.readBool();
                            config.scalefactorResilience = in.readBool();
                            config.spectralDataResilience = in.readBool();
                        }
                        in.skipBit();
                    }
                    if (config.channelConfiguration == ChannelConfiguration.CHANNEL_CONFIG_NONE) {
                        PCE pce = new PCE();
                        pce.decode(in);
                        config.profile = pce.getProfile();
                        config.sampleFrequency = pce.getSampleFrequency();
                        config.channelConfiguration = ChannelConfiguration.forInt(pce.getChannelCount());
                    }
                    if (in.getBitsLeft() <= 10) break;
                    DecoderConfig.readSyncExtension(in, config);
                    break;
                }
                default: {
                    throw new AACException("profile not supported: " + config.profile.getIndex());
                }
            }
            DecoderConfig decoderConfig = config;
            return decoderConfig;
        }
        finally {
            in.destroy();
        }
    }

    private static Profile readProfile(BitStream in) throws AACException {
        int i = in.readBits(5);
        if (i == 31) {
            i = 32 + in.readBits(6);
        }
        return Profile.forInt(i);
    }

    private static void readSyncExtension(BitStream in, DecoderConfig config) throws AACException {
        int type = in.readBits(11);
        switch (type) {
            case 695: {
                Profile profile = Profile.forInt(in.readBits(5));
                if (!profile.equals((Object)Profile.AAC_SBR)) break;
                config.sbrPresent = in.readBool();
                if (!config.sbrPresent) break;
                config.profile = profile;
                int tmp = in.readBits(4);
                if (tmp == config.sampleFrequency.getIndex()) {
                    config.downSampledSBR = true;
                }
                if (tmp != 15) break;
                throw new AACException("sample rate specified explicitly, not supported yet!");
            }
        }
    }
}
