/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac;

import com.github.alexthe666.citadel.repack.jaad.aac.AACException;
import com.github.alexthe666.citadel.repack.jaad.aac.ChannelConfiguration;
import com.github.alexthe666.citadel.repack.jaad.aac.DecoderConfig;
import com.github.alexthe666.citadel.repack.jaad.aac.Profile;
import com.github.alexthe666.citadel.repack.jaad.aac.SampleBuffer;
import com.github.alexthe666.citadel.repack.jaad.aac.filterbank.FilterBank;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.BitStream;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.Constants;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.PCE;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.SyntacticElements;
import com.github.alexthe666.citadel.repack.jaad.aac.transport.ADIFHeader;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class Decoder
implements Constants {
    private final DecoderConfig config;
    private final SyntacticElements syntacticElements;
    private final FilterBank filterBank;
    private BitStream in;
    private ADIFHeader adifHeader;

    public static boolean canDecode(Profile profile) {
        return profile.isDecodingSupported();
    }

    public Decoder(byte[] decoderSpecificInfo) throws AACException {
        this.config = DecoderConfig.parseMP4DecoderSpecificInfo(decoderSpecificInfo);
        if (this.config == null) {
            throw new IllegalArgumentException("illegal MP4 decoder specific info");
        }
        if (!Decoder.canDecode(this.config.getProfile())) {
            throw new AACException("unsupported profile: " + this.config.getProfile().getDescription());
        }
        this.syntacticElements = new SyntacticElements(this.config);
        this.filterBank = new FilterBank(this.config.isSmallFrameUsed(), this.config.getChannelConfiguration().getChannelCount());
        this.in = new BitStream();
        LOGGER.log(Level.FINE, "profile: {0}", (Object)this.config.getProfile());
        LOGGER.log(Level.FINE, "sf: {0}", this.config.getSampleFrequency().getFrequency());
        LOGGER.log(Level.FINE, "channels: {0}", this.config.getChannelConfiguration().getDescription());
    }

    public DecoderConfig getConfig() {
        return this.config;
    }

    public void decodeFrame(byte[] frame, SampleBuffer buffer) throws AACException {
        if (frame != null) {
            this.in.setData(frame);
        }
        try {
            this.decode(buffer);
        }
        catch (AACException e) {
            if (!e.isEndOfStream()) {
                throw e;
            }
            LOGGER.log(Level.WARNING, "unexpected end of frame", e);
        }
    }

    private void decode(SampleBuffer buffer) throws AACException {
        if (ADIFHeader.isPresent(this.in)) {
            this.adifHeader = ADIFHeader.readHeader(this.in);
            PCE pce = this.adifHeader.getFirstPCE();
            this.config.setProfile(pce.getProfile());
            this.config.setSampleFrequency(pce.getSampleFrequency());
            this.config.setChannelConfiguration(ChannelConfiguration.forInt(pce.getChannelCount()));
        }
        if (!Decoder.canDecode(this.config.getProfile())) {
            throw new AACException("unsupported profile: " + this.config.getProfile().getDescription());
        }
        this.syntacticElements.startNewFrame();
        try {
            this.syntacticElements.decode(this.in);
            this.syntacticElements.process(this.filterBank);
            this.syntacticElements.sendToOutput(buffer);
        }
        catch (AACException e) {
            buffer.setData(new byte[0], 0, 0, 0, 0);
            throw e;
        }
        catch (Exception e) {
            buffer.setData(new byte[0], 0, 0, 0, 0);
            throw new AACException(e);
        }
    }

    static {
        for (Handler h : LOGGER.getHandlers()) {
            LOGGER.removeHandler(h);
        }
        LOGGER.setLevel(Level.WARNING);
        ConsoleHandler h = new ConsoleHandler();
        h.setLevel(Level.ALL);
        LOGGER.addHandler(h);
    }
}
