/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.tools;

import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;

public static class MainUtils.Flag {
    private String longName;
    private String shortName;
    private String description;
    private MainUtils.FlagType type;

    public MainUtils.Flag(String longName, String shortName, String description, MainUtils.FlagType type) {
        this.longName = longName;
        this.shortName = shortName;
        this.description = description;
        this.type = type;
    }

    public static MainUtils.Flag flag(String longName, String shortName, String description) {
        return new MainUtils.Flag(longName, shortName, description, MainUtils.FlagType.ANY);
    }

    public String getLongName() {
        return this.longName;
    }

    public String getDescription() {
        return this.description;
    }

    public String getShortName() {
        return this.shortName;
    }

    public MainUtils.FlagType getType() {
        return this.type;
    }
}
