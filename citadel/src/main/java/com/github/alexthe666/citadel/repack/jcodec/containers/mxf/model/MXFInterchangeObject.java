/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.MXFMetadata;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

public abstract class MXFInterchangeObject
extends MXFMetadata {
    private UL generationUID;
    private UL objectClass;

    public MXFInterchangeObject(UL ul) {
        super(ul);
    }

    @Override
    public void readBuf(ByteBuffer bb) {
        bb.order(ByteOrder.BIG_ENDIAN);
        HashMap<Integer, ByteBuffer> tags = new HashMap<Integer, ByteBuffer>();
        block5: while (bb.hasRemaining()) {
            int tag = bb.getShort() & 0xFFFF;
            int size = bb.getShort() & 0xFFFF;
            ByteBuffer _bb = NIOUtils.read(bb, size);
            switch (tag) {
                case 15370: {
                    this.uid = UL.read(_bb);
                    continue block5;
                }
                case 258: {
                    this.generationUID = UL.read(_bb);
                    continue block5;
                }
                case 257: {
                    this.objectClass = UL.read(_bb);
                    continue block5;
                }
            }
            tags.put(tag, _bb);
        }
        if (tags.size() > 0) {
            this.read(tags);
        }
    }

    protected abstract void read(Map<Integer, ByteBuffer> var1);

    public UL getGenerationUID() {
        return this.generationUID;
    }

    public UL getObjectClass() {
        return this.objectClass;
    }
}
