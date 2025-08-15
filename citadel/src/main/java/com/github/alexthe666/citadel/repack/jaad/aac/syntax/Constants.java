/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.syntax;

import java.util.logging.Logger;

public interface Constants {
    public static final Logger LOGGER = Logger.getLogger("jaad");
    public static final int MAX_ELEMENTS = 16;
    public static final int BYTE_MASK = 255;
    public static final int MIN_INPUT_SIZE = 768;
    public static final int WINDOW_LEN_LONG = 1024;
    public static final int WINDOW_LEN_SHORT = 128;
    public static final int WINDOW_SMALL_LEN_LONG = 960;
    public static final int WINDOW_SMALL_LEN_SHORT = 120;
    public static final int ELEMENT_SCE = 0;
    public static final int ELEMENT_CPE = 1;
    public static final int ELEMENT_CCE = 2;
    public static final int ELEMENT_LFE = 3;
    public static final int ELEMENT_DSE = 4;
    public static final int ELEMENT_PCE = 5;
    public static final int ELEMENT_FIL = 6;
    public static final int ELEMENT_END = 7;
    public static final int MAX_WINDOW_COUNT = 8;
    public static final int MAX_WINDOW_GROUP_COUNT = 8;
    public static final int MAX_LTP_SFB = 40;
    public static final int MAX_SECTIONS = 120;
    public static final int MAX_MS_MASK = 128;
    public static final float SQRT2 = 1.4142135f;
}
