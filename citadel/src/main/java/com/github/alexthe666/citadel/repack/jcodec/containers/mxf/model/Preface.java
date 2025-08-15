/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.MXFInterchangeObject;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class Preface
extends MXFInterchangeObject {
    private Date lastModifiedDate;
    private int objectModelVersion;
    private UL op;
    private UL[] essenceContainers;
    private UL[] dmSchemes;

    public Preface(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block7: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            ByteBuffer _bb = entry.getValue();
            switch (entry.getKey()) {
                case 15106: {
                    this.lastModifiedDate = Preface.readDate(_bb);
                    break;
                }
                case 15111: {
                    this.objectModelVersion = _bb.getInt();
                    break;
                }
                case 15113: {
                    this.op = UL.read(_bb);
                    break;
                }
                case 15114: {
                    this.essenceContainers = Preface.readULBatch(_bb);
                    break;
                }
                case 15115: {
                    this.dmSchemes = Preface.readULBatch(_bb);
                    break;
                }
                default: {
                    Logger.warn(String.format("Unknown tag [ " + this.ul + "]: %04x", entry.getKey()));
                    continue block7;
                }
            }
            it.remove();
        }
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public int getObjectModelVersion() {
        return this.objectModelVersion;
    }

    public UL getOp() {
        return this.op;
    }

    public UL[] getEssenceContainers() {
        return this.essenceContainers;
    }

    public UL[] getDmSchemes() {
        return this.dmSchemes;
    }
}
