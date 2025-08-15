/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.Boxes;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.IBoxFactory;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;

public class SimpleBoxFactory
implements IBoxFactory {
    private Boxes boxes;

    public SimpleBoxFactory(Boxes boxes) {
        this.boxes = boxes;
    }

    @Override
    public Box newBox(Header header) {
        Class<? extends Box> claz = this.boxes.toClass(header.getFourcc());
        if (claz == null) {
            return new Box.LeafBox(header);
        }
        Box box = Platform.newInstance(claz, new Object[]{header});
        return box;
    }
}
