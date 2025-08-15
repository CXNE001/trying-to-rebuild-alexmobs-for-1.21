/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.syntax;

import com.github.alexthe666.citadel.repack.jaad.aac.AACException;

public interface IBitStream {
    public void destroy();

    public void setData(byte[] var1);

    public void byteAlign() throws AACException;

    public void reset();

    public int getPosition();

    public int getBitsLeft();

    public int readBits(int var1) throws AACException;

    public int readBit() throws AACException;

    public boolean readBool() throws AACException;

    public int peekBits(int var1) throws AACException;

    public int peekBit() throws AACException;

    public void skipBits(int var1) throws AACException;

    public void skipBit() throws AACException;

    public int maskBits(int var1);
}
