/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.BoxFactory;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.BoxUtil;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import java.io.IOException;
import java.nio.channels.WritableByteChannel;

public static class MP4Util.Atom {
    private long offset;
    private Header header;

    public MP4Util.Atom(Header header, long offset) {
        this.header = header;
        this.offset = offset;
    }

    public long getOffset() {
        return this.offset;
    }

    public Header getHeader() {
        return this.header;
    }

    public Box parseBox(SeekableByteChannel input) throws IOException {
        input.setPosition(this.offset + this.header.headerSize());
        return BoxUtil.parseBox(NIOUtils.fetchFromChannel(input, (int)this.header.getBodySize()), this.header, BoxFactory.getDefault());
    }

    public void copy(SeekableByteChannel input, WritableByteChannel out) throws IOException {
        input.setPosition(this.offset);
        NIOUtils.copy(input, out, this.header.getSize());
    }
}
