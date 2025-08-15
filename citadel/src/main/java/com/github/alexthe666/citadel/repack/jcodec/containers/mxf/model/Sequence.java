/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.MXFStructuralComponent;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

public class Sequence
extends MXFStructuralComponent {
    private UL[] structuralComponentsRefs;

    public Sequence(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        super.read(tags);
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block3: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            switch (entry.getKey()) {
                case 4097: {
                    this.structuralComponentsRefs = Sequence.readULBatch(entry.getValue());
                    break;
                }
                default: {
                    Logger.warn(String.format("Unknown tag [ " + this.ul + "]: %04x", entry.getKey()));
                    continue block3;
                }
            }
            it.remove();
        }
    }

    public UL[] getStructuralComponentsRefs() {
        return this.structuralComponentsRefs;
    }
}
