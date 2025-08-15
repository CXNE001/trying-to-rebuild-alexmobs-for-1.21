/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.TimestampUtil;

static final class TimestampUtil.1
extends TimestampUtil.BaseCommand {
    final /* synthetic */ long val$shift;

    TimestampUtil.1(String stream, long l) {
        this.val$shift = l;
        super(stream);
    }

    @Override
    protected long withTimestamp(long pts, boolean isPts) {
        return Math.max(pts + this.val$shift, 0L);
    }
}
