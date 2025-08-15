/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.boxes;

import java.util.List;

public interface Box {
    public Box getParent();

    public long getSize();

    public long getType();

    public long getOffset();

    public String getName();

    public boolean hasChildren();

    public boolean hasChild(long var1);

    public List<Box> getChildren();

    public List<Box> getChildren(long var1);

    public Box getChild(long var1);
}
