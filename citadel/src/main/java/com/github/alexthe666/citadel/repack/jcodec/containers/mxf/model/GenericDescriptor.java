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

public class GenericDescriptor
extends MXFInterchangeObject {
    private UL[] locators;
    private UL[] subDescriptors;

    public GenericDescriptor(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block4: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            ByteBuffer _bb = entry.getValue();
            switch (entry.getKey()) {
                case 12033: {
                    this.locators = GenericDescriptor.readULBatch(_bb);
                    break;
                }
                case 16129: {
                    this.subDescriptors = GenericDescriptor.readULBatch(_bb);
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

    public UL[] getLocators() {
        return this.locators;
    }

    public UL[] getSubDescriptors() {
        return this.subDescriptors;
    }
}
