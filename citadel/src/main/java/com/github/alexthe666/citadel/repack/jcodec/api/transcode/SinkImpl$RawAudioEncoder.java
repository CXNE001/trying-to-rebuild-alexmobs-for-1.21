/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.api.transcode;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioEncoder;
import java.nio.ByteBuffer;

private static class SinkImpl.RawAudioEncoder
implements AudioEncoder {
    private SinkImpl.RawAudioEncoder() {
    }

    @Override
    public ByteBuffer encode(ByteBuffer audioPkt, ByteBuffer buf) {
        return audioPkt;
    }
}
