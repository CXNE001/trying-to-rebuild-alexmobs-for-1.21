/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mjpeg;

import java.nio.ByteBuffer;

public class FrameHeader {
    int headerLength;
    int bitsPerSample;
    int height;
    int width;
    int nComp;
    Component[] components;

    public int getHmax() {
        int max = 0;
        for (int i = 0; i < this.components.length; ++i) {
            Component c = this.components[i];
            max = Math.max(max, c.subH);
        }
        return max;
    }

    public int getVmax() {
        int max = 0;
        for (int i = 0; i < this.components.length; ++i) {
            Component c = this.components[i];
            max = Math.max(max, c.subV);
        }
        return max;
    }

    public static FrameHeader read(ByteBuffer is) {
        FrameHeader frame = new FrameHeader();
        frame.headerLength = is.getShort() & 0xFFFF;
        frame.bitsPerSample = is.get() & 0xFF;
        frame.height = is.getShort() & 0xFFFF;
        frame.width = is.getShort() & 0xFFFF;
        frame.nComp = is.get() & 0xFF;
        frame.components = new Component[frame.nComp];
        for (int i = 0; i < frame.components.length; ++i) {
            Component c = frame.components[i] = new Component();
            c.index = is.get() & 0xFF;
            int hv = is.get() & 0xFF;
            c.subH = (hv & 0xF0) >>> 4;
            c.subV = hv & 0xF;
            c.quantTable = is.get() & 0xFF;
        }
        return frame;
    }

    public static class Component {
        int index;
        int subH;
        int subV;
        int quantTable;
    }
}
