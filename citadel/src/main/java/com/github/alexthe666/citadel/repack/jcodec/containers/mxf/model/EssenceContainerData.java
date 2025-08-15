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

public class EssenceContainerData
extends MXFInterchangeObject {
    private UL linkedPackageUID;
    private int indexSID;
    private int bodySID;

    public EssenceContainerData(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block5: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            ByteBuffer _bb = entry.getValue();
            switch (entry.getKey()) {
                case 9985: {
                    this.linkedPackageUID = UL.read(_bb);
                    break;
                }
                case 16134: {
                    this.indexSID = _bb.getInt();
                    break;
                }
                case 16135: {
                    this.bodySID = _bb.getInt();
                    break;
                }
                default: {
                    Logger.warn(String.format("Unknown tag [ EssenceContainerData: " + this.ul + "]: %04x", entry.getKey()));
                    continue block5;
                }
            }
            it.remove();
        }
    }

    public UL getLinkedPackageUID() {
        return this.linkedPackageUID;
    }

    public int getIndexSID() {
        return this.indexSID;
    }

    public int getBodySID() {
        return this.bodySID;
    }
}
