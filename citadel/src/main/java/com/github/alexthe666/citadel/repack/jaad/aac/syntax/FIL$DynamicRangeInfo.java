/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.syntax;

public static class FIL.DynamicRangeInfo {
    private static final int MAX_NBR_BANDS = 7;
    private final boolean[] excludeMask = new boolean[7];
    private final boolean[] additionalExcludedChannels = new boolean[7];
    private boolean pceTagPresent;
    private int pceInstanceTag;
    private int tagReservedBits;
    private boolean excludedChannelsPresent;
    private boolean bandsPresent;
    private int bandsIncrement;
    private int interpolationScheme;
    private int[] bandTop;
    private boolean progRefLevelPresent;
    private int progRefLevel;
    private int progRefLevelReservedBits;
    private boolean[] dynRngSgn;
    private int[] dynRngCtl;
}
