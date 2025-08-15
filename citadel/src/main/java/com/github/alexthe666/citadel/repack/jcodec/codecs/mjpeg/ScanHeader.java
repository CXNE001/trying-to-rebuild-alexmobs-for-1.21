/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mjpeg;

import java.nio.ByteBuffer;

public class ScanHeader {
    int ls;
    int ns;
    Component[] components;
    int ss;
    int se;
    int ah;
    int al;

    public boolean isInterleaved() {
        return this.ns > 1;
    }

    public static ScanHeader read(ByteBuffer bb) {
        ScanHeader scan = new ScanHeader();
        scan.ls = bb.getShort() & 0xFFFF;
        scan.ns = bb.get() & 0xFF;
        scan.components = new Component[scan.ns];
        for (int i = 0; i < scan.components.length; ++i) {
            Component c = scan.components[i] = new Component();
            c.cs = bb.get() & 0xFF;
            int tdta = bb.get() & 0xFF;
            c.td = (tdta & 0xF0) >>> 4;
            c.ta = tdta & 0xF;
        }
        scan.ss = bb.get() & 0xFF;
        scan.se = bb.get() & 0xFF;
        int ahal = bb.get() & 0xFF;
        scan.ah = (ahal & 0xF0) >>> 4;
        scan.al = ahal & 0xF;
        return scan;
    }

    public static class Component {
        int cs;
        int td;
        int ta;
    }
}
