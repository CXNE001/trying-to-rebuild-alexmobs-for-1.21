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

public class TimecodeComponent
extends MXFStructuralComponent {
    private long start;
    private int base;
    private int dropFrame;

    public TimecodeComponent(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        super.read(tags);
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block5: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            ByteBuffer _bb = entry.getValue();
            switch (entry.getKey()) {
                case 5377: {
                    this.start = _bb.getLong();
                    break;
                }
                case 5378: {
                    this.base = _bb.getShort();
                    break;
                }
                case 5379: {
                    this.dropFrame = _bb.get();
                    break;
                }
                default: {
                    Logger.warn(String.format("Unknown tag [ " + this.ul + "]: %04x", entry.getKey()));
                    continue block5;
                }
            }
            it.remove();
        }
    }

    public long getStart() {
        return this.start;
    }

    public int getBase() {
        return this.base;
    }

    public int getDropFrame() {
        return this.dropFrame;
    }
}
