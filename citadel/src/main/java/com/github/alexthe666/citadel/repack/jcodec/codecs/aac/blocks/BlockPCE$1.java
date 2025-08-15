/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.aac.blocks;

import com.github.alexthe666.citadel.repack.jcodec.codecs.aac.ChannelPosition;

static class BlockPCE.1 {
    static final /* synthetic */ int[] $SwitchMap$org$jcodec$codecs$aac$ChannelPosition;

    static {
        $SwitchMap$org$jcodec$codecs$aac$ChannelPosition = new int[ChannelPosition.values().length];
        try {
            BlockPCE.1.$SwitchMap$org$jcodec$codecs$aac$ChannelPosition[ChannelPosition.AAC_CHANNEL_FRONT.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            BlockPCE.1.$SwitchMap$org$jcodec$codecs$aac$ChannelPosition[ChannelPosition.AAC_CHANNEL_BACK.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            BlockPCE.1.$SwitchMap$org$jcodec$codecs$aac$ChannelPosition[ChannelPosition.AAC_CHANNEL_SIDE.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            BlockPCE.1.$SwitchMap$org$jcodec$codecs$aac$ChannelPosition[ChannelPosition.AAC_CHANNEL_CC.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            BlockPCE.1.$SwitchMap$org$jcodec$codecs$aac$ChannelPosition[ChannelPosition.AAC_CHANNEL_LFE.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
