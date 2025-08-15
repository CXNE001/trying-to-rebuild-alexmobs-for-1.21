/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.IBoxFactory;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.UdtaMetaBox;

class UdtaBox.1
implements IBoxFactory {
    final /* synthetic */ IBoxFactory val$_factory;

    UdtaBox.1(IBoxFactory iBoxFactory) {
        this.val$_factory = iBoxFactory;
    }

    @Override
    public Box newBox(Header header) {
        if (header.getFourcc().equals(UdtaMetaBox.fourcc())) {
            UdtaMetaBox box = new UdtaMetaBox(header);
            box.setFactory(this.val$_factory);
            return box;
        }
        return this.val$_factory.newBox(header);
    }
}
