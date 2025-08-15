/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StringRepresentable
 */
package com.github.alexthe666.alexsmobs.block;

import java.util.Locale;
import net.minecraft.util.StringRepresentable;

public static enum BlockEndPirateAnchor.PieceType implements StringRepresentable
{
    ANCHOR,
    ANCHOR_SIDE,
    CHAIN;


    public String toString() {
        return this.m_7912_();
    }

    public String m_7912_() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
