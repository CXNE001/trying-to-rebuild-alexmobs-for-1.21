/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.CDCIEssenceDescriptor;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

public class MPEG2VideoDescriptor
extends CDCIEssenceDescriptor {
    private byte singleSequence;
    private byte constantBFrames;
    private byte codedContentType;
    private byte lowDelay;
    private byte closedGOP;
    private byte identicalGOP;
    private short maxGOP;
    private short bPictureCount;
    private int bitRate;
    private byte profileAndLevel;

    public MPEG2VideoDescriptor(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        super.read(tags);
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block12: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            ByteBuffer _bb = entry.getValue();
            switch (entry.getKey()) {
                case 32768: {
                    this.singleSequence = _bb.get();
                    break;
                }
                case 32769: {
                    this.constantBFrames = _bb.get();
                    break;
                }
                case 32770: {
                    this.codedContentType = _bb.get();
                    break;
                }
                case 32771: {
                    this.lowDelay = _bb.get();
                    break;
                }
                case 32772: {
                    this.closedGOP = _bb.get();
                    break;
                }
                case 32773: {
                    this.identicalGOP = _bb.get();
                    break;
                }
                case 32774: {
                    this.maxGOP = _bb.getShort();
                    break;
                }
                case 32775: {
                    this.bPictureCount = (short)(_bb.get() & 0xFF);
                    break;
                }
                case 32776: {
                    this.bitRate = _bb.getInt();
                    break;
                }
                case 32777: {
                    this.profileAndLevel = _bb.get();
                    break;
                }
                default: {
                    Logger.warn(String.format("Unknown tag [ " + this.ul + "]: %04x + (" + _bb.remaining() + ")", entry.getKey()));
                    continue block12;
                }
            }
            it.remove();
        }
    }

    public byte getSingleSequence() {
        return this.singleSequence;
    }

    public byte getConstantBFrames() {
        return this.constantBFrames;
    }

    public byte getCodedContentType() {
        return this.codedContentType;
    }

    public byte getLowDelay() {
        return this.lowDelay;
    }

    public byte getClosedGOP() {
        return this.closedGOP;
    }

    public byte getIdenticalGOP() {
        return this.identicalGOP;
    }

    public short getMaxGOP() {
        return this.maxGOP;
    }

    public short getbPictureCount() {
        return this.bPictureCount;
    }

    public int getBitRate() {
        return this.bitRate;
    }

    public byte getProfileAndLevel() {
        return this.profileAndLevel;
    }
}
