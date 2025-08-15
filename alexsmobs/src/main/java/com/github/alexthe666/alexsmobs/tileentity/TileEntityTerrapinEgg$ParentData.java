/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.util.Mth
 */
package com.github.alexthe666.alexsmobs.tileentity;

import com.github.alexthe666.alexsmobs.entity.util.TerrapinTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;

public static class TileEntityTerrapinEgg.ParentData {
    public TerrapinTypes type;
    public int shellType;
    public int skinType;
    public int turtleColor;
    public int shellColor;
    public int skinColor;

    public TileEntityTerrapinEgg.ParentData(TerrapinTypes type, int shellType, int skinType, int turtleColor, int shellColor, int skinColor) {
        this.type = type;
        this.shellType = shellType;
        this.skinType = skinType;
        this.turtleColor = turtleColor;
        this.shellColor = shellColor;
        this.skinColor = skinColor;
    }

    public TileEntityTerrapinEgg.ParentData(CompoundTag tag) {
        this(TerrapinTypes.values()[Mth.m_14045_((int)tag.m_128451_("TerrapinType"), (int)0, (int)(TerrapinTypes.values().length - 1))], tag.m_128451_("ShellType"), tag.m_128451_("SkinType"), tag.m_128451_("TurtleColor"), tag.m_128451_("ShellColor"), tag.m_128451_("SkinColor"));
    }

    public boolean canMerge(TileEntityTerrapinEgg.ParentData other) {
        if (this.type == TerrapinTypes.OVERLAY && other.type == TerrapinTypes.OVERLAY) {
            return this.turtleColor == other.turtleColor && this.shellType == other.shellType && this.skinType == other.skinType && this.shellColor == other.shellColor && this.skinColor == other.skinColor;
        }
        return other.type == this.type;
    }

    public void writeToNBT(CompoundTag tag) {
        tag.m_128405_("TerrapinType", this.type.ordinal());
        tag.m_128405_("ShellType", this.shellType);
        tag.m_128405_("SkinType", this.skinType);
        tag.m_128405_("TurtleColor", this.turtleColor);
        tag.m_128405_("ShellColor", this.shellColor);
        tag.m_128405_("SkinColor", this.skinColor);
    }
}
