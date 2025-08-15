/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Rational;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.GenericTrack;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

public class TimelineTrack
extends GenericTrack {
    private Rational editRate;
    private long origin;

    public TimelineTrack(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        super.read(tags);
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block4: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            ByteBuffer _bb = entry.getValue();
            switch (entry.getKey()) {
                case 19201: {
                    this.editRate = new Rational(_bb.getInt(), _bb.getInt());
                    break;
                }
                case 19202: {
                    this.origin = _bb.getLong();
                    break;
                }
                default: {
                    Logger.warn(String.format("Unknown tag [ " + this.ul + "]: %04x", entry.getKey()));
                    continue block4;
                }
            }
            it.remove();
        }
    }

    public Rational getEditRate() {
        return this.editRate;
    }

    public long getOrigin() {
        return this.origin;
    }
}
