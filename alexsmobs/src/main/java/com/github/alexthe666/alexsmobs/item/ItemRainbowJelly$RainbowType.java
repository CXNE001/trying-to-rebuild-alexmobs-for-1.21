/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.alexsmobs.item;

public static enum ItemRainbowJelly.RainbowType {
    RAINBOW,
    TRANS,
    NONBI,
    BI,
    ACE,
    WEEZER,
    BRAZIL;


    public static ItemRainbowJelly.RainbowType getFromString(String name) {
        if (name.contains("nonbi") || name.contains("non-bi")) {
            return NONBI;
        }
        if (name.contains("trans")) {
            return TRANS;
        }
        if (name.contains("bi")) {
            return BI;
        }
        if (name.contains("asexual") || name.contains("ace")) {
            return ACE;
        }
        if (name.contains("weezer")) {
            return WEEZER;
        }
        if (name.contains("brazil")) {
            return BRAZIL;
        }
        return RAINBOW;
    }
}
