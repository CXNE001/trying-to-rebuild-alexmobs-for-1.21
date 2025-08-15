/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.FileDescriptor;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

public class GenericDataEssenceDescriptor
extends FileDescriptor {
    private UL dataEssenceCoding;

    public GenericDataEssenceDescriptor(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        super.read(tags);
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block3: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            ByteBuffer _bb = entry.getValue();
            switch (entry.getKey()) {
                case 15873: {
                    this.dataEssenceCoding = UL.read(_bb);
                    break;
                }
                default: {
                    Logger.warn(String.format("Unknown tag [ FileDescriptor: " + this.ul + "]: %04x", entry.getKey()));
                    continue block3;
                }
            }
            it.remove();
        }
    }

    public UL getDataEssenceCoding() {
        return this.dataEssenceCoding;
    }
}
