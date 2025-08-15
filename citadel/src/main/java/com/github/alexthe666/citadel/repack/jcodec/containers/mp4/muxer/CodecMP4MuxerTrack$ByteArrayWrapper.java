/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.muxer;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.nio.ByteBuffer;
import java.util.Arrays;

private static class CodecMP4MuxerTrack.ByteArrayWrapper {
    private byte[] bytes;

    public CodecMP4MuxerTrack.ByteArrayWrapper(ByteBuffer bytes) {
        this.bytes = NIOUtils.toArray(bytes);
    }

    public ByteBuffer get() {
        return ByteBuffer.wrap(this.bytes);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CodecMP4MuxerTrack.ByteArrayWrapper)) {
            return false;
        }
        return Platform.arrayEqualsByte(this.bytes, ((CodecMP4MuxerTrack.ByteArrayWrapper)obj).bytes);
    }

    public int hashCode() {
        return Arrays.hashCode(this.bytes);
    }
}
