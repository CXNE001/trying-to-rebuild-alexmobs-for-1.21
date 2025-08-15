/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.TimestampUtil;

static final class TimestampUtil.3
extends TimestampUtil.BaseCommand {
    final /* synthetic */ int val$precision;

    TimestampUtil.3(String stream, int n) {
        this.val$precision = n;
        super(stream);
    }

    @Override
    protected long withTimestamp(long pts, boolean isPts) {
        return Math.round((double)pts / (double)this.val$precision) * (long)this.val$precision;
    }
}
