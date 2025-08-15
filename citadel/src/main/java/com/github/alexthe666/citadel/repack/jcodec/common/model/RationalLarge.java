/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.model;

import com.github.alexthe666.citadel.repack.jcodec.common.StringUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Rational;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;

public class RationalLarge {
    public static final RationalLarge ONE = new RationalLarge(1L, 1L);
    public static final RationalLarge HALF = new RationalLarge(1L, 2L);
    public static final RationalLarge ZERO = new RationalLarge(0L, 1L);
    final long num;
    final long den;

    public RationalLarge(long num, long den) {
        this.num = num;
        this.den = den;
    }

    public long getNum() {
        return this.num;
    }

    public long getDen() {
        return this.den;
    }

    public static RationalLarge parse(String string) {
        String[] split = StringUtils.splitS(string, ":");
        return split.length > 1 ? RationalLarge.R(Long.parseLong(split[0]), Long.parseLong(split[1])) : RationalLarge.R(Long.parseLong(string), 1L);
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (int)(this.den ^ this.den >>> 32);
        result = 31 * result + (int)(this.num ^ this.num >>> 32);
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        RationalLarge other = (RationalLarge)obj;
        if (this.den != other.den) {
            return false;
        }
        return this.num == other.num;
    }

    public long multiplyS(long scalar) {
        return this.num * scalar / this.den;
    }

    public long divideS(long scalar) {
        return this.den * scalar / this.num;
    }

    public long divideByS(long scalar) {
        return this.num / (this.den * scalar);
    }

    public RationalLarge flip() {
        return new RationalLarge(this.den, this.num);
    }

    public static RationalLarge R(long num, long den) {
        return new RationalLarge(num, den);
    }

    public static RationalLarge R1(long num) {
        return RationalLarge.R(num, 1L);
    }

    public boolean lessThen(RationalLarge sec) {
        return this.num * sec.den < sec.num * this.den;
    }

    public boolean greaterThen(RationalLarge sec) {
        return this.num * sec.den > sec.num * this.den;
    }

    public boolean smallerOrEqualTo(RationalLarge sec) {
        return this.num * sec.den <= sec.num * this.den;
    }

    public boolean greaterOrEqualTo(RationalLarge sec) {
        return this.num * sec.den >= sec.num * this.den;
    }

    public boolean equalsLarge(RationalLarge other) {
        return this.num * other.den == other.num * this.den;
    }

    public RationalLarge plus(RationalLarge other) {
        return RationalLarge.reduceLong(this.num * other.den + other.num * this.den, this.den * other.den);
    }

    public RationalLarge plusR(Rational other) {
        return RationalLarge.reduceLong(this.num * (long)other.den + (long)other.num * this.den, this.den * (long)other.den);
    }

    public RationalLarge minus(RationalLarge other) {
        return RationalLarge.reduceLong(this.num * other.den - other.num * this.den, this.den * other.den);
    }

    public RationalLarge minusR(Rational other) {
        return RationalLarge.reduceLong(this.num * (long)other.den - (long)other.num * this.den, this.den * (long)other.den);
    }

    public RationalLarge plusLong(long scalar) {
        return new RationalLarge(this.num + scalar * this.den, this.den);
    }

    public RationalLarge minusLong(long scalar) {
        return new RationalLarge(this.num - scalar * this.den, this.den);
    }

    public RationalLarge multiplyLong(long scalar) {
        return new RationalLarge(this.num * scalar, this.den);
    }

    public RationalLarge divideLong(long scalar) {
        return new RationalLarge(this.den * scalar, this.num);
    }

    public RationalLarge divideByLong(long scalar) {
        return new RationalLarge(this.num, this.den * scalar);
    }

    public RationalLarge multiply(RationalLarge other) {
        return RationalLarge.reduceLong(this.num * other.num, this.den * other.den);
    }

    public RationalLarge multiplyR(Rational other) {
        return RationalLarge.reduceLong(this.num * (long)other.num, this.den * (long)other.den);
    }

    public RationalLarge divideRL(RationalLarge other) {
        return RationalLarge.reduceLong(other.num * this.den, other.den * this.num);
    }

    public RationalLarge divideR(Rational other) {
        return RationalLarge.reduceLong((long)other.num * this.den, (long)other.den * this.num);
    }

    public RationalLarge divideBy(RationalLarge other) {
        return RationalLarge.reduceLong(this.num * other.den, this.den * other.num);
    }

    public RationalLarge divideByR(Rational other) {
        return RationalLarge.reduceLong(this.num * (long)other.den, this.den * (long)other.num);
    }

    public double scalar() {
        return (double)this.num / (double)this.den;
    }

    public long scalarClip() {
        return this.num / this.den;
    }

    public String toString() {
        return this.num + ":" + this.den;
    }

    public static RationalLarge reduceLong(long num, long den) {
        long gcd = MathUtil.gcdLong(num, den);
        return new RationalLarge(num / gcd, den / gcd);
    }
}
