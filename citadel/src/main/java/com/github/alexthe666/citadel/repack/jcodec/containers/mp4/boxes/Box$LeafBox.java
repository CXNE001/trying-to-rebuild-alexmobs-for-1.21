/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import java.nio.ByteBuffer;

public static class Box.LeafBox
extends Box {
    ByteBuffer data;

    public Box.LeafBox(Header atom) {
        super(atom);
    }

    @Override
    public void parse(ByteBuffer input) {
        this.data = NIOUtils.read(input, (int)this.header.getBodySize());
    }

    public ByteBuffer getData() {
        return this.data.duplicate();
    }

    @Override
    protected void doWrite(ByteBuffer out) {
        NIOUtils.write(out, this.data);
    }

    @Override
    public int estimateSize() {
        return this.data.remaining() + Header.estimateHeaderSize(this.data.remaining());
    }
}
