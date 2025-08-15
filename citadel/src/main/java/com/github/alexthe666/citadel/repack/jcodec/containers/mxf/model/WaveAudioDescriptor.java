/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.GenericSoundEssenceDescriptor;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

public class WaveAudioDescriptor
extends GenericSoundEssenceDescriptor {
    private short blockAlign;
    private byte sequenceOffset;
    private int avgBps;
    private UL channelAssignment;
    private int peakEnvelopeVersion;
    private int peakEnvelopeFormat;
    private int pointsPerPeakValue;
    private int peakEnvelopeBlockSize;
    private int peakChannels;
    private int peakFrames;
    private ByteBuffer peakOfPeaksPosition;
    private ByteBuffer peakEnvelopeTimestamp;
    private ByteBuffer peakEnvelopeData;

    public WaveAudioDescriptor(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        super.read(tags);
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block15: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            ByteBuffer _bb = entry.getValue();
            switch (entry.getKey()) {
                case 15626: {
                    this.blockAlign = _bb.getShort();
                    break;
                }
                case 15627: {
                    this.sequenceOffset = _bb.get();
                    break;
                }
                case 15625: {
                    this.avgBps = _bb.getInt();
                    break;
                }
                case 15666: {
                    this.channelAssignment = UL.read(_bb);
                    break;
                }
                case 15657: {
                    this.peakEnvelopeVersion = _bb.getInt();
                    break;
                }
                case 15658: {
                    this.peakEnvelopeFormat = _bb.getInt();
                    break;
                }
                case 15659: {
                    this.pointsPerPeakValue = _bb.getInt();
                    break;
                }
                case 15660: {
                    this.peakEnvelopeBlockSize = _bb.getInt();
                    break;
                }
                case 15661: {
                    this.peakChannels = _bb.getInt();
                    break;
                }
                case 15662: {
                    this.peakFrames = _bb.getInt();
                    break;
                }
                case 15663: {
                    this.peakOfPeaksPosition = _bb;
                    break;
                }
                case 15664: {
                    this.peakEnvelopeTimestamp = _bb;
                    break;
                }
                case 15665: {
                    this.peakEnvelopeData = _bb;
                    break;
                }
                default: {
                    System.out.println(String.format("Unknown tag [ " + this.ul + "]: %04x", entry.getKey()));
                    continue block15;
                }
            }
            it.remove();
        }
    }

    public short getBlockAlign() {
        return this.blockAlign;
    }

    public byte getSequenceOffset() {
        return this.sequenceOffset;
    }

    public int getAvgBps() {
        return this.avgBps;
    }

    public UL getChannelAssignment() {
        return this.channelAssignment;
    }

    public int getPeakEnvelopeVersion() {
        return this.peakEnvelopeVersion;
    }

    public int getPeakEnvelopeFormat() {
        return this.peakEnvelopeFormat;
    }

    public int getPointsPerPeakValue() {
        return this.pointsPerPeakValue;
    }

    public int getPeakEnvelopeBlockSize() {
        return this.peakEnvelopeBlockSize;
    }

    public int getPeakChannels() {
        return this.peakChannels;
    }

    public int getPeakFrames() {
        return this.peakFrames;
    }

    public ByteBuffer getPeakOfPeaksPosition() {
        return this.peakOfPeaksPosition;
    }

    public ByteBuffer getPeakEnvelopeTimestamp() {
        return this.peakEnvelopeTimestamp;
    }

    public ByteBuffer getPeakEnvelopeData() {
        return this.peakEnvelopeData;
    }
}
