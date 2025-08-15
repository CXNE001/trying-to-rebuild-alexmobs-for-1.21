/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.WaveAudioDescriptor;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

public class AES3PCMDescriptor
extends WaveAudioDescriptor {
    private byte emphasis;
    private short blockStartOffset;
    private byte auxBitsMode;
    private ByteBuffer channelStatusMode;
    private ByteBuffer fixedChannelStatusData;
    private ByteBuffer userDataMode;
    private ByteBuffer fixedUserData;

    public AES3PCMDescriptor(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        super.read(tags);
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block9: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            ByteBuffer _bb = entry.getValue();
            switch (entry.getKey()) {
                case 15629: {
                    this.emphasis = _bb.get();
                    break;
                }
                case 15631: {
                    this.blockStartOffset = _bb.getShort();
                    break;
                }
                case 15624: {
                    this.auxBitsMode = _bb.get();
                    break;
                }
                case 15632: {
                    this.channelStatusMode = _bb;
                    break;
                }
                case 15633: {
                    this.fixedChannelStatusData = _bb;
                    break;
                }
                case 15634: {
                    this.userDataMode = _bb;
                    break;
                }
                case 15635: {
                    this.fixedUserData = _bb;
                    break;
                }
                default: {
                    Logger.warn(String.format("Unknown tag [ " + this.ul + "]: %04x", entry.getKey()));
                    continue block9;
                }
            }
            it.remove();
        }
    }

    public byte getEmphasis() {
        return this.emphasis;
    }

    public short getBlockStartOffset() {
        return this.blockStartOffset;
    }

    public byte getAuxBitsMode() {
        return this.auxBitsMode;
    }

    public ByteBuffer getChannelStatusMode() {
        return this.channelStatusMode;
    }

    public ByteBuffer getFixedChannelStatusData() {
        return this.fixedChannelStatusData;
    }

    public ByteBuffer getUserDataMode() {
        return this.userDataMode;
    }

    public ByteBuffer getFixedUserData() {
        return this.fixedUserData;
    }
}
