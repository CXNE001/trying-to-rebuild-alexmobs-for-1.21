/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.tools;

import com.github.alexthe666.citadel.repack.jcodec.common.StringUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.util.EnumSet;
import java.util.Map;

public static class MainUtils.Cmd {
    public Map<String, String> longFlags;
    public Map<String, String> shortFlags;
    public String[] args;
    private Map<String, String>[] longArgFlags;
    private Map<String, String>[] shortArgFlags;

    public MainUtils.Cmd(Map<String, String> longFlags, Map<String, String> shortFlags, String[] args, Map<String, String>[] longArgFlags, Map<String, String>[] shortArgFlags) {
        this.args = args;
        this.longFlags = longFlags;
        this.shortFlags = shortFlags;
        this.longArgFlags = longArgFlags;
        this.shortArgFlags = shortArgFlags;
    }

    private Long getLongFlagInternal(Map<String, String> longFlags, Map<String, String> shortFlags, MainUtils.Flag flag, Long defaultValue) {
        return longFlags.containsKey(flag.getLongName()) ? new Long(longFlags.get(flag.getLongName())) : (shortFlags.containsKey(flag.getShortName()) ? new Long(shortFlags.get(flag.getShortName())) : defaultValue);
    }

    private Integer getIntegerFlagInternal(Map<String, String> longFlags, Map<String, String> shortFlags, MainUtils.Flag flag, Integer defaultValue) {
        return longFlags.containsKey(flag.getLongName()) ? new Integer(longFlags.get(flag.getLongName())) : (shortFlags.containsKey(flag.getShortName()) ? new Integer(shortFlags.get(flag.getShortName())) : defaultValue);
    }

    private Boolean getBooleanFlagInternal(Map<String, String> longFlags, Map<String, String> shortFlags, MainUtils.Flag flag, Boolean defaultValue) {
        return longFlags.containsKey(flag.getLongName()) ? !"false".equalsIgnoreCase(longFlags.get(flag.getLongName())) : (shortFlags.containsKey(flag.getShortName()) ? !"false".equalsIgnoreCase(shortFlags.get(flag.getShortName())) : defaultValue);
    }

    private Double getDoubleFlagInternal(Map<String, String> longFlags, Map<String, String> shortFlags, MainUtils.Flag flag, Double defaultValue) {
        return longFlags.containsKey(flag.getLongName()) ? new Double(longFlags.get(flag.getLongName())) : (shortFlags.containsKey(flag.getShortName()) ? new Double(shortFlags.get(flag.getShortName())) : defaultValue);
    }

    private String getStringFlagInternal(Map<String, String> longFlags, Map<String, String> shortFlags, MainUtils.Flag flag, String defaultValue) {
        return longFlags.containsKey(flag.getLongName()) ? longFlags.get(flag.getLongName()) : (shortFlags.containsKey(flag.getShortName()) ? shortFlags.get(flag.getShortName()) : defaultValue);
    }

    private int[] getMultiIntegerFlagInternal(Map<String, String> longFlags, Map<String, String> shortFlags, MainUtils.Flag flag, int[] defaultValue) {
        String flagValue;
        if (longFlags.containsKey(flag.getLongName())) {
            flagValue = longFlags.get(flag.getLongName());
        } else if (shortFlags.containsKey(flag.getShortName())) {
            flagValue = shortFlags.get(flag.getShortName());
        } else {
            return defaultValue;
        }
        String[] split = StringUtils.splitS(flagValue, ",");
        int[] result = new int[split.length];
        for (int i = 0; i < split.length; ++i) {
            result[i] = Integer.parseInt(split[i]);
        }
        return result;
    }

    private <T extends Enum<T>> T getEnumFlagInternal(Map<String, String> longFlags, Map<String, String> shortFlags, MainUtils.Flag flag, T defaultValue, Class<T> class1) {
        String flagValue;
        if (longFlags.containsKey(flag.getLongName())) {
            flagValue = longFlags.get(flag.getLongName());
        } else if (shortFlags.containsKey(flag.getShortName())) {
            flagValue = shortFlags.get(flag.getShortName());
        } else {
            return defaultValue;
        }
        String strVal = flagValue.toLowerCase();
        EnumSet<Enum> allOf = EnumSet.allOf(class1);
        for (Enum val : allOf) {
            if (!val.name().toLowerCase().equals(strVal)) continue;
            return (T)val;
        }
        return null;
    }

    public Long getLongFlagD(MainUtils.Flag flagName, Long defaultValue) {
        return this.getLongFlagInternal(this.longFlags, this.shortFlags, flagName, defaultValue);
    }

    public Long getLongFlag(MainUtils.Flag flagName) {
        return this.getLongFlagInternal(this.longFlags, this.shortFlags, flagName, null);
    }

    public Long getLongFlagID(int arg, MainUtils.Flag flagName, Long defaultValue) {
        return this.getLongFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, defaultValue);
    }

    public Long getLongFlagI(int arg, MainUtils.Flag flagName) {
        return this.getLongFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, null);
    }

    public Integer getIntegerFlagD(MainUtils.Flag flagName, Integer defaultValue) {
        return this.getIntegerFlagInternal(this.longFlags, this.shortFlags, flagName, defaultValue);
    }

    public Integer getIntegerFlag(MainUtils.Flag flagName) {
        return this.getIntegerFlagInternal(this.longFlags, this.shortFlags, flagName, null);
    }

    public Integer getIntegerFlagID(int arg, MainUtils.Flag flagName, Integer defaultValue) {
        return this.getIntegerFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, defaultValue);
    }

    public Integer getIntegerFlagI(int arg, MainUtils.Flag flagName) {
        return this.getIntegerFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, null);
    }

    public Boolean getBooleanFlagD(MainUtils.Flag flagName, Boolean defaultValue) {
        return this.getBooleanFlagInternal(this.longFlags, this.shortFlags, flagName, defaultValue);
    }

    public Boolean getBooleanFlag(MainUtils.Flag flagName) {
        return this.getBooleanFlagInternal(this.longFlags, this.shortFlags, flagName, false);
    }

    public Boolean getBooleanFlagID(int arg, MainUtils.Flag flagName, Boolean defaultValue) {
        return this.getBooleanFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, defaultValue);
    }

    public Boolean getBooleanFlagI(int arg, MainUtils.Flag flagName) {
        return this.getBooleanFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, false);
    }

    public Double getDoubleFlagD(MainUtils.Flag flagName, Double defaultValue) {
        return this.getDoubleFlagInternal(this.longFlags, this.shortFlags, flagName, defaultValue);
    }

    public Double getDoubleFlag(MainUtils.Flag flagName) {
        return this.getDoubleFlagInternal(this.longFlags, this.shortFlags, flagName, null);
    }

    public Double getDoubleFlagID(int arg, MainUtils.Flag flagName, Double defaultValue) {
        return this.getDoubleFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, defaultValue);
    }

    public Double getDoubleFlagI(int arg, MainUtils.Flag flagName) {
        return this.getDoubleFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, null);
    }

    public String getStringFlagD(MainUtils.Flag flagName, String defaultValue) {
        return this.getStringFlagInternal(this.longFlags, this.shortFlags, flagName, defaultValue);
    }

    public String getStringFlag(MainUtils.Flag flagName) {
        return this.getStringFlagInternal(this.longFlags, this.shortFlags, flagName, null);
    }

    public String getStringFlagID(int arg, MainUtils.Flag flagName, String defaultValue) {
        return this.getStringFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, defaultValue);
    }

    public String getStringFlagI(int arg, MainUtils.Flag flagName) {
        return this.getStringFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, null);
    }

    public int[] getMultiIntegerFlagD(MainUtils.Flag flagName, int[] defaultValue) {
        return this.getMultiIntegerFlagInternal(this.longFlags, this.shortFlags, flagName, defaultValue);
    }

    public int[] getMultiIntegerFlag(MainUtils.Flag flagName) {
        return this.getMultiIntegerFlagInternal(this.longFlags, this.shortFlags, flagName, new int[0]);
    }

    public int[] getMultiIntegerFlagID(int arg, MainUtils.Flag flagName, int[] defaultValue) {
        return this.getMultiIntegerFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, defaultValue);
    }

    public int[] getMultiIntegerFlagI(int arg, MainUtils.Flag flagName) {
        return this.getMultiIntegerFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, new int[0]);
    }

    public <T extends Enum<T>> T getEnumFlagD(MainUtils.Flag flagName, T defaultValue, Class<T> class1) {
        return this.getEnumFlagInternal(this.longFlags, this.shortFlags, flagName, defaultValue, class1);
    }

    public <T extends Enum<T>> T getEnumFlag(MainUtils.Flag flagName, Class<T> class1) {
        return this.getEnumFlagInternal(this.longFlags, this.shortFlags, flagName, null, class1);
    }

    public <T extends Enum<T>> T getEnumFlagID(int arg, MainUtils.Flag flagName, T defaultValue, Class<T> class1) {
        return this.getEnumFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, defaultValue, class1);
    }

    public <T extends Enum<T>> T getEnumFlagI(int arg, MainUtils.Flag flagName, Class<T> class1) {
        return this.getEnumFlagInternal(this.longArgFlags[arg], this.shortArgFlags[arg], flagName, null, class1);
    }

    public String getArg(int i) {
        return i < this.args.length ? this.args[i] : null;
    }

    public int argsLength() {
        return this.args.length;
    }

    public void popArg() {
        this.args = Platform.copyOfRangeO(this.args, 1, this.args.length);
    }
}
