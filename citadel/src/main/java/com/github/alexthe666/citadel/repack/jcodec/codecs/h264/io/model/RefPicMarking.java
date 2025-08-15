/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model;

import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;

public class RefPicMarking {
    private Instruction[] instructions;

    public RefPicMarking(Instruction[] instructions) {
        this.instructions = instructions;
    }

    public Instruction[] getInstructions() {
        return this.instructions;
    }

    public String toString() {
        return Platform.toJSON(this);
    }

    public static class Instruction {
        private InstrType type;
        private int arg1;
        private int arg2;

        public Instruction(InstrType type, int arg1, int arg2) {
            this.type = type;
            this.arg1 = arg1;
            this.arg2 = arg2;
        }

        public InstrType getType() {
            return this.type;
        }

        public int getArg1() {
            return this.arg1;
        }

        public int getArg2() {
            return this.arg2;
        }
    }

    public static enum InstrType {
        REMOVE_SHORT,
        REMOVE_LONG,
        CONVERT_INTO_LONG,
        TRUNK_LONG,
        CLEAR,
        MARK_LONG;

    }
}
