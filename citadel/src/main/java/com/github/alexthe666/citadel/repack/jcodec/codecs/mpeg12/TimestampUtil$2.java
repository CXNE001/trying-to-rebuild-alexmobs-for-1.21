/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.TimestampUtil;
import com.github.alexthe666.citadel.repack.jcodec.common.model.RationalLarge;

static final class TimestampUtil.2
extends TimestampUtil.BaseCommand {
    final /* synthetic */ RationalLarge val$scale;

    TimestampUtil.2(String stream, RationalLarge rationalLarge) {
        this.val$scale = rationalLarge;
        super(stream);
    }

    @Override
    protected long withTimestamp(long pts, boolean isPts) {
        return this.val$scale.multiplyS(pts);
    }
}
