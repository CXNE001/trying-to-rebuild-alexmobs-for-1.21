/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 */
package com.github.alexthe666.alexsmobs.tileentity;

import com.github.alexthe666.alexsmobs.entity.EntityTerrapin;
import com.github.alexthe666.alexsmobs.entity.util.TerrapinTypes;
import com.github.alexthe666.alexsmobs.tileentity.AMTileEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityTerrapinEgg
extends BlockEntity {
    public ParentData parent1;
    public ParentData parent2;

    public TileEntityTerrapinEgg(BlockPos pos, BlockState state) {
        super((BlockEntityType)AMTileEntityRegistry.TERRAPIN_EGG.get(), pos, state);
    }

    public void addAttributesToOffspring(EntityTerrapin baby, RandomSource random) {
        if (this.parent1 != null && this.parent2 != null) {
            baby.setTurtleType(random.m_188499_() ? this.parent1.type : this.parent2.type);
            baby.setShellType(random.m_188499_() ? this.parent1.shellType : this.parent2.shellType);
            baby.setSkinType(random.m_188499_() ? this.parent1.skinType : this.parent2.skinType);
            baby.setTurtleColor((this.parent1.turtleColor + this.parent2.turtleColor) / 2);
            baby.setShellColor((this.parent1.shellColor + this.parent2.shellColor) / 2);
            baby.setSkinColor((this.parent1.skinColor + this.parent2.skinColor) / 2);
            if (random.m_188501_() < 0.15f) {
                baby.setTurtleType(TerrapinTypes.OVERLAY);
                switch (random.m_188503_(2)) {
                    case 0: {
                        baby.setTurtleColor((int)(1.6777215E7f * random.m_188501_()));
                        break;
                    }
                    case 1: {
                        baby.setShellColor((int)(1.6777215E7f * random.m_188501_()));
                        break;
                    }
                    case 2: {
                        baby.setSkinColor((int)(1.6777215E7f * random.m_188501_()));
                    }
                }
            }
        }
    }

    public void m_142466_(CompoundTag compound) {
        super.m_142466_(compound);
        if (compound.m_128441_("Parent1Data")) {
            this.parent1 = new ParentData(compound.m_128469_("Parent1Data"));
        }
        if (compound.m_128441_("Parent2Data")) {
            this.parent2 = new ParentData(compound.m_128469_("Parent2Data"));
        }
    }

    protected void m_183515_(CompoundTag compound) {
        CompoundTag tag;
        super.m_183515_(compound);
        if (this.parent1 != null) {
            tag = new CompoundTag();
            this.parent1.writeToNBT(tag);
            compound.m_128365_("Parent1Data", (Tag)tag);
        }
        if (this.parent2 != null) {
            tag = new CompoundTag();
            this.parent2.writeToNBT(tag);
            compound.m_128365_("Parent2Data", (Tag)tag);
        }
    }

    public static class ParentData {
        public TerrapinTypes type;
        public int shellType;
        public int skinType;
        public int turtleColor;
        public int shellColor;
        public int skinColor;

        public ParentData(TerrapinTypes type, int shellType, int skinType, int turtleColor, int shellColor, int skinColor) {
            this.type = type;
            this.shellType = shellType;
            this.skinType = skinType;
            this.turtleColor = turtleColor;
            this.shellColor = shellColor;
            this.skinColor = skinColor;
        }

        public ParentData(CompoundTag tag) {
            this(TerrapinTypes.values()[Mth.m_14045_((int)tag.m_128451_("TerrapinType"), (int)0, (int)(TerrapinTypes.values().length - 1))], tag.m_128451_("ShellType"), tag.m_128451_("SkinType"), tag.m_128451_("TurtleColor"), tag.m_128451_("ShellColor"), tag.m_128451_("SkinColor"));
        }

        public boolean canMerge(ParentData other) {
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
}
