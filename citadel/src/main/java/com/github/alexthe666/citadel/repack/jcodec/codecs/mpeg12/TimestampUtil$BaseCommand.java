/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.FixTimestamp;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.TimestampUtil;

private static abstract class TimestampUtil.BaseCommand
extends FixTimestamp {
    private String streamSelector;

    public TimestampUtil.BaseCommand(String stream) {
        this.streamSelector = stream;
    }

    @Override
    protected long doWithTimestamp(int streamId, long pts, boolean isPts) {
        if (TimestampUtil.STREAM_ALL.equals(this.streamSelector) || TimestampUtil.STRAM_VIDEO.equals(this.streamSelector) && this.isVideo(streamId) || TimestampUtil.STREAM_AUDIO.equals(this.streamSelector) && this.isAudio(streamId)) {
            return this.withTimestamp(pts, isPts);
        }
        return pts;
    }

    protected abstract long withTimestamp(long var1, boolean var3);
}
