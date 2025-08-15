/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.MXFInterchangeObject;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

public class MXFStructuralComponent
extends MXFInterchangeObject {
    private long duration;
    private UL dataDefinitionUL;

    public MXFStructuralComponent(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block4: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            switch (entry.getKey()) {
                case 514: {
                    this.duration = entry.getValue().getLong();
                    break;
                }
                case 513: {
                    this.dataDefinitionUL = UL.read(entry.getValue());
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

    public long getDuration() {
        return this.duration;
    }

    public UL getDataDefinitionUL() {
        return this.dataDefinitionUL;
    }
}
