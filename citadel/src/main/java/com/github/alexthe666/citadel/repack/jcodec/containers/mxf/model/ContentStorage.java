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

public class ContentStorage
extends MXFInterchangeObject {
    private UL[] packageRefs;
    private UL[] essenceContainerData;

    public ContentStorage(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block4: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            ByteBuffer _bb = entry.getValue();
            switch (entry.getKey()) {
                case 6401: {
                    this.packageRefs = ContentStorage.readULBatch(_bb);
                    break;
                }
                case 6402: {
                    this.essenceContainerData = ContentStorage.readULBatch(_bb);
                    break;
                }
                default: {
                    Logger.warn(String.format("Unknown tag [ ContentStorage: " + this.ul + "]: %04x", entry.getKey()));
                    continue block4;
                }
            }
            it.remove();
        }
    }

    public UL[] getPackageRefs() {
        return this.packageRefs;
    }

    public UL[] getEssenceContainerData() {
        return this.essenceContainerData;
    }
}
