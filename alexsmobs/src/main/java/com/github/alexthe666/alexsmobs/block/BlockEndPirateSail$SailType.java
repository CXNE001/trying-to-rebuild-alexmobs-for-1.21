/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StringRepresentable
 */
package com.github.alexthe666.alexsmobs.block;

import java.util.Locale;
import net.minecraft.util.StringRepresentable;

private static enum BlockEndPirateSail.SailType implements StringRepresentable
{
    SINGLE,
    TOP,
    MIDDLE,
    BOTTOM;


    public String toString() {
        return this.m_7912_();
    }

    public String m_7912_() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
