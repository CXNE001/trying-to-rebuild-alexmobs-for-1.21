/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.syntax;

import com.github.alexthe666.citadel.repack.jaad.aac.AACException;
import com.github.alexthe666.citadel.repack.jaad.aac.ChannelConfiguration;
import com.github.alexthe666.citadel.repack.jaad.aac.DecoderConfig;
import com.github.alexthe666.citadel.repack.jaad.aac.Profile;
import com.github.alexthe666.citadel.repack.jaad.aac.SampleBuffer;
import com.github.alexthe666.citadel.repack.jaad.aac.SampleFrequency;
import com.github.alexthe666.citadel.repack.jaad.aac.filterbank.FilterBank;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.SBR;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.BitStream;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.CCE;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.CPE;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.Constants;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.DSE;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.Element;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.FIL;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.ICSInfo;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.ICStream;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.PCE;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.SCE_LFE;
import com.github.alexthe666.citadel.repack.jaad.aac.tools.IS;
import com.github.alexthe666.citadel.repack.jaad.aac.tools.LTPrediction;
import com.github.alexthe666.citadel.repack.jaad.aac.tools.MS;
import java.util.logging.Level;

public class SyntacticElements
implements Constants {
    private DecoderConfig config;
    private boolean sbrPresent;
    private boolean psPresent;
    private int bitsRead;
    private int frame = 0;
    private final PCE pce;
    private final Element[] elements;
    private final CCE[] cces;
    private final DSE[] dses;
    private final FIL[] fils;
    private int curElem;
    private int curCCE;
    private int curDSE;
    private int curFIL;
    private float[][] data;

    public SyntacticElements(DecoderConfig config) {
        this.config = config;
        this.pce = new PCE();
        this.elements = new Element[64];
        this.cces = new CCE[16];
        this.dses = new DSE[16];
        this.fils = new FIL[16];
        this.startNewFrame();
    }

    public final void startNewFrame() {
        this.curElem = 0;
        this.curCCE = 0;
        this.curDSE = 0;
        this.curFIL = 0;
        this.sbrPresent = false;
        this.psPresent = false;
        this.bitsRead = 0;
    }

    public void decode(BitStream in) throws AACException {
        ++this.frame;
        int start = in.getPosition();
        Element prev = null;
        boolean content = true;
        if (!this.config.getProfile().isErrorResilientProfile()) {
            int type;
            while (content && (type = in.readBits(3)) != 7) {
                switch (type) {
                    case 0: 
                    case 3: {
                        LOGGER.finest("SCE");
                        prev = this.decodeSCE_LFE(in);
                        break;
                    }
                    case 1: {
                        LOGGER.finest("CPE");
                        prev = this.decodeCPE(in);
                        break;
                    }
                    case 2: {
                        LOGGER.finest("CCE");
                        this.decodeCCE(in);
                        prev = null;
                        break;
                    }
                    case 4: {
                        LOGGER.finest("DSE");
                        this.decodeDSE(in);
                        prev = null;
                        break;
                    }
                    case 5: {
                        LOGGER.finest("PCE");
                        this.decodePCE(in);
                        prev = null;
                        break;
                    }
                    case 6: {
                        LOGGER.finest("FIL");
                        this.decodeFIL(in, prev);
                        prev = null;
                    }
                }
            }
            LOGGER.finest("END");
            content = false;
            prev = null;
        } else {
            switch (this.config.getChannelConfiguration()) {
                case CHANNEL_CONFIG_MONO: {
                    this.decodeSCE_LFE(in);
                    break;
                }
                case CHANNEL_CONFIG_STEREO: {
                    this.decodeCPE(in);
                    break;
                }
                case CHANNEL_CONFIG_STEREO_PLUS_CENTER: {
                    this.decodeSCE_LFE(in);
                    this.decodeCPE(in);
                    break;
                }
                case CHANNEL_CONFIG_STEREO_PLUS_CENTER_PLUS_REAR_MONO: {
                    this.decodeSCE_LFE(in);
                    this.decodeCPE(in);
                    this.decodeSCE_LFE(in);
                    break;
                }
                case CHANNEL_CONFIG_FIVE: {
                    this.decodeSCE_LFE(in);
                    this.decodeCPE(in);
                    this.decodeCPE(in);
                    break;
                }
                case CHANNEL_CONFIG_FIVE_PLUS_ONE: {
                    this.decodeSCE_LFE(in);
                    this.decodeCPE(in);
                    this.decodeCPE(in);
                    this.decodeSCE_LFE(in);
                    break;
                }
                case CHANNEL_CONFIG_SEVEN_PLUS_ONE: {
                    this.decodeSCE_LFE(in);
                    this.decodeCPE(in);
                    this.decodeCPE(in);
                    this.decodeCPE(in);
                    this.decodeSCE_LFE(in);
                    break;
                }
                default: {
                    throw new AACException("unsupported channel configuration for error resilience: " + this.config.getChannelConfiguration());
                }
            }
        }
        in.byteAlign();
        this.bitsRead = in.getPosition() - start;
    }

    private Element decodeSCE_LFE(BitStream in) throws AACException {
        if (this.elements[this.curElem] == null) {
            this.elements[this.curElem] = new SCE_LFE(this.config);
        }
        ((SCE_LFE)this.elements[this.curElem]).decode(in, this.config);
        ++this.curElem;
        return this.elements[this.curElem - 1];
    }

    private Element decodeCPE(BitStream in) throws AACException {
        if (this.elements[this.curElem] == null) {
            this.elements[this.curElem] = new CPE(this.config);
        }
        ((CPE)this.elements[this.curElem]).decode(in, this.config);
        ++this.curElem;
        return this.elements[this.curElem - 1];
    }

    private void decodeCCE(BitStream in) throws AACException {
        if (this.curCCE == 16) {
            throw new AACException("too much CCE elements");
        }
        if (this.cces[this.curCCE] == null) {
            this.cces[this.curCCE] = new CCE(this.config);
        }
        this.cces[this.curCCE].decode(in, this.config);
        ++this.curCCE;
    }

    private void decodeDSE(BitStream in) throws AACException {
        if (this.curDSE == 16) {
            throw new AACException("too much CCE elements");
        }
        if (this.dses[this.curDSE] == null) {
            this.dses[this.curDSE] = new DSE();
        }
        this.dses[this.curDSE].decode(in);
        ++this.curDSE;
    }

    private void decodePCE(BitStream in) throws AACException {
        this.pce.decode(in);
        this.config.setProfile(this.pce.getProfile());
        this.config.setSampleFrequency(this.pce.getSampleFrequency());
        this.config.setChannelConfiguration(ChannelConfiguration.forInt(this.pce.getChannelCount()));
    }

    private void decodeFIL(BitStream in, Element prev) throws AACException {
        if (this.curFIL == 16) {
            throw new AACException("too much FIL elements");
        }
        if (this.fils[this.curFIL] == null) {
            this.fils[this.curFIL] = new FIL(this.config.isSBRDownSampled());
        }
        this.fils[this.curFIL].decode(in, prev, this.config.getSampleFrequency(), this.config.isSBREnabled(), this.config.isSmallFrameUsed());
        ++this.curFIL;
        if (prev != null && prev.isSBRPresent()) {
            this.sbrPresent = true;
            if (!this.psPresent && prev.getSBR().isPSUsed()) {
                this.psPresent = true;
            }
        }
    }

    public void process(FilterBank filterBank) throws AACException {
        int mult;
        Profile profile = this.config.getProfile();
        SampleFrequency sf = this.config.getSampleFrequency();
        int chs = this.config.getChannelConfiguration().getChannelCount();
        if (chs == 1 && this.psPresent) {
            ++chs;
        }
        int n = mult = this.sbrPresent ? 2 : 1;
        if (this.data == null || chs != this.data.length || mult * this.config.getFrameLength() != this.data[0].length) {
            this.data = new float[chs][mult * this.config.getFrameLength()];
        }
        int channel = 0;
        for (int i = 0; i < this.elements.length && channel < chs; ++i) {
            Element e = this.elements[i];
            if (e == null) continue;
            if (e instanceof SCE_LFE) {
                SCE_LFE scelfe = (SCE_LFE)e;
                channel += this.processSingle(scelfe, filterBank, channel, profile, sf);
                continue;
            }
            if (e instanceof CPE) {
                CPE cpe = (CPE)e;
                this.processPair(cpe, filterBank, channel, profile, sf);
                channel += 2;
                continue;
            }
            if (!(e instanceof CCE)) continue;
            ((CCE)e).process();
            ++channel;
        }
    }

    private int processSingle(SCE_LFE scelfe, FilterBank filterBank, int channel, Profile profile, SampleFrequency sf) throws AACException {
        ICStream ics = scelfe.getICStream();
        ICSInfo info = ics.getInfo();
        LTPrediction ltp = info.getLTPrediction();
        int elementID = scelfe.getElementInstanceTag();
        float[] iqData = ics.getInvQuantData();
        if (profile.equals((Object)Profile.AAC_MAIN) && info.isICPredictionPresent()) {
            info.getICPrediction().process(ics, iqData, sf);
        }
        if (ltp != null) {
            ltp.process(ics, iqData, filterBank, sf);
        }
        this.processDependentCoupling(false, elementID, 0, iqData, null);
        if (ics.isTNSDataPresent()) {
            ics.getTNS().process(ics, iqData, sf, false);
        }
        this.processDependentCoupling(false, elementID, 1, iqData, null);
        filterBank.process(info.getWindowSequence(), info.getWindowShape(1), info.getWindowShape(0), iqData, this.data[channel], channel);
        if (ltp != null) {
            ltp.updateState(this.data[channel], filterBank.getOverlap(channel), profile);
        }
        this.processIndependentCoupling(false, elementID, this.data[channel], null);
        if (ics.isGainControlPresent()) {
            ics.getGainControl().process(iqData, info.getWindowShape(1), info.getWindowShape(0), info.getWindowSequence());
        }
        int chs = 1;
        if (this.sbrPresent && this.config.isSBREnabled()) {
            SBR sbr;
            if (this.data[channel].length == this.config.getFrameLength()) {
                LOGGER.log(Level.WARNING, "SBR data present, but buffer has normal size!");
            }
            if ((sbr = scelfe.getSBR()).isPSUsed()) {
                chs = 2;
                scelfe.getSBR().processPS(this.data[channel], this.data[channel + 1], false);
            } else {
                scelfe.getSBR().process(this.data[channel], false);
            }
        }
        return chs;
    }

    private void processPair(CPE cpe, FilterBank filterBank, int channel, Profile profile, SampleFrequency sf) throws AACException {
        ICStream ics1 = cpe.getLeftChannel();
        ICStream ics2 = cpe.getRightChannel();
        ICSInfo info1 = ics1.getInfo();
        ICSInfo info2 = ics2.getInfo();
        LTPrediction ltp1 = info1.getLTPrediction();
        LTPrediction ltp2 = info2.getLTPrediction();
        int elementID = cpe.getElementInstanceTag();
        float[] iqData1 = ics1.getInvQuantData();
        float[] iqData2 = ics2.getInvQuantData();
        if (cpe.isCommonWindow() && cpe.isMSMaskPresent()) {
            MS.process(cpe, iqData1, iqData2);
        }
        if (profile.equals((Object)Profile.AAC_MAIN)) {
            if (info1.isICPredictionPresent()) {
                info1.getICPrediction().process(ics1, iqData1, sf);
            }
            if (info2.isICPredictionPresent()) {
                info2.getICPrediction().process(ics2, iqData2, sf);
            }
        }
        IS.process(cpe, iqData1, iqData2);
        if (ltp1 != null) {
            ltp1.process(ics1, iqData1, filterBank, sf);
        }
        if (ltp2 != null) {
            ltp2.process(ics2, iqData2, filterBank, sf);
        }
        this.processDependentCoupling(true, elementID, 0, iqData1, iqData2);
        if (ics1.isTNSDataPresent()) {
            ics1.getTNS().process(ics1, iqData1, sf, false);
        }
        if (ics2.isTNSDataPresent()) {
            ics2.getTNS().process(ics2, iqData2, sf, false);
        }
        this.processDependentCoupling(true, elementID, 1, iqData1, iqData2);
        filterBank.process(info1.getWindowSequence(), info1.getWindowShape(1), info1.getWindowShape(0), iqData1, this.data[channel], channel);
        filterBank.process(info2.getWindowSequence(), info2.getWindowShape(1), info2.getWindowShape(0), iqData2, this.data[channel + 1], channel + 1);
        if (ltp1 != null) {
            ltp1.updateState(this.data[channel], filterBank.getOverlap(channel), profile);
        }
        if (ltp2 != null) {
            ltp2.updateState(this.data[channel + 1], filterBank.getOverlap(channel + 1), profile);
        }
        this.processIndependentCoupling(true, elementID, this.data[channel], this.data[channel + 1]);
        if (ics1.isGainControlPresent()) {
            ics1.getGainControl().process(iqData1, info1.getWindowShape(1), info1.getWindowShape(0), info1.getWindowSequence());
        }
        if (ics2.isGainControlPresent()) {
            ics2.getGainControl().process(iqData2, info2.getWindowShape(1), info2.getWindowShape(0), info2.getWindowSequence());
        }
        if (this.sbrPresent && this.config.isSBREnabled()) {
            if (this.data[channel].length == this.config.getFrameLength()) {
                LOGGER.log(Level.WARNING, "SBR data present, but buffer has normal size!");
            }
            cpe.getSBR().process(this.data[channel], this.data[channel + 1], false);
        }
    }

    private void processIndependentCoupling(boolean channelPair, int elementID, float[] data1, float[] data2) {
        for (int i = 0; i < this.cces.length; ++i) {
            CCE cce = this.cces[i];
            int index = 0;
            if (cce == null || cce.getCouplingPoint() != 2) continue;
            for (int c = 0; c <= cce.getCoupledCount(); ++c) {
                int chSelect = cce.getCHSelect(c);
                if (cce.isChannelPair(c) == channelPair && cce.getIDSelect(c) == elementID) {
                    if (chSelect != 1) {
                        cce.applyIndependentCoupling(index, data1);
                        if (chSelect != 0) {
                            ++index;
                        }
                    }
                    if (chSelect == 2) continue;
                    cce.applyIndependentCoupling(index, data2);
                    ++index;
                    continue;
                }
                index += 1 + (chSelect == 3 ? 1 : 0);
            }
        }
    }

    private void processDependentCoupling(boolean channelPair, int elementID, int couplingPoint, float[] data1, float[] data2) {
        for (int i = 0; i < this.cces.length; ++i) {
            CCE cce = this.cces[i];
            int index = 0;
            if (cce == null || cce.getCouplingPoint() != couplingPoint) continue;
            for (int c = 0; c <= cce.getCoupledCount(); ++c) {
                int chSelect = cce.getCHSelect(c);
                if (cce.isChannelPair(c) == channelPair && cce.getIDSelect(c) == elementID) {
                    if (chSelect != 1) {
                        cce.applyDependentCoupling(index, data1);
                        if (chSelect != 0) {
                            ++index;
                        }
                    }
                    if (chSelect == 2) continue;
                    cce.applyDependentCoupling(index, data2);
                    ++index;
                    continue;
                }
                index += 1 + (chSelect == 3 ? 1 : 0);
            }
        }
    }

    public void sendToOutput(SampleBuffer buffer) {
        boolean be = buffer.isBigEndian();
        int chs = Math.max(this.data.length, 2);
        int mult = this.sbrPresent && this.config.isSBREnabled() ? 2 : 1;
        int length = mult * this.config.getFrameLength();
        int freq = mult * this.config.getSampleFrequency().getFrequency();
        byte[] b = buffer.getData();
        if (b.length != chs * length * 2) {
            b = new byte[chs * length * 2];
        }
        for (int i = 0; i < chs; ++i) {
            float[] cur = this.data[i < this.data.length ? i : 0];
            for (int j = 0; j < length; ++j) {
                short s = (short)Math.max(Math.min(Math.round(cur[j]), Short.MAX_VALUE), Short.MIN_VALUE);
                int off = (j * chs + i) * 2;
                if (be) {
                    b[off] = (byte)(s >> 8 & 0xFF);
                    b[off + 1] = (byte)(s & 0xFF);
                    continue;
                }
                b[off + 1] = (byte)(s >> 8 & 0xFF);
                b[off] = (byte)(s & 0xFF);
            }
        }
        buffer.setData(b, freq, chs, 16, this.bitsRead);
    }
}
