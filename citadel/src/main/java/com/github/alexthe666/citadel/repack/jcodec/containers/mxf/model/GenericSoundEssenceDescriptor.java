/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Rational;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.FileDescriptor;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

public class GenericSoundEssenceDescriptor
extends FileDescriptor {
    private Rational audioSamplingRate;
    private byte locked;
    private byte audioRefLevel;
    private byte electroSpatialFormulation;
    private int channelCount;
    private int quantizationBits;
    private byte dialNorm;
    private UL soundEssenceCompression;

    public GenericSoundEssenceDescriptor(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        super.read(tags);
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block10: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            ByteBuffer _bb = entry.getValue();
            switch (entry.getKey()) {
                case 15619: {
                    this.audioSamplingRate = new Rational(_bb.getInt(), _bb.getInt());
                    break;
                }
                case 15618: {
                    this.locked = _bb.get();
                    break;
                }
                case 15620: {
                    this.audioRefLevel = _bb.get();
                    break;
                }
                case 15621: {
                    this.electroSpatialFormulation = _bb.get();
                    break;
                }
                case 15623: {
                    this.channelCount = _bb.getInt();
                    break;
                }
                case 15617: {
                    this.quantizationBits = _bb.getInt();
                    break;
                }
                case 15628: {
                    this.dialNorm = _bb.get();
                    break;
                }
                case 15622: {
                    this.soundEssenceCompression = UL.read(_bb);
                    break;
                }
                default: {
                    Logger.warn(String.format("Unknown tag [ " + this.ul + "]: %04x", entry.getKey()));
                    continue block10;
                }
            }
            it.remove();
        }
    }

    public Rational getAudioSamplingRate() {
        return this.audioSamplingRate;
    }

    public byte getLocked() {
        return this.locked;
    }

    public byte getAudioRefLevel() {
        return this.audioRefLevel;
    }

    public byte getElectroSpatialFormulation() {
        return this.electroSpatialFormulation;
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public int getQuantizationBits() {
        return this.quantizationBits;
    }

    public byte getDialNorm() {
        return this.dialNorm;
    }

    public UL getSoundEssenceCompression() {
        return this.soundEssenceCompression;
    }
}
