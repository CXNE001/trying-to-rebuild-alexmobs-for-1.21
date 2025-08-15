/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import java.util.concurrent.Callable;

public interface PriorityCallable<T>
extends Callable<T> {
    public int getPriority();
}
