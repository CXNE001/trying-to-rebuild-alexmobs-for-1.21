/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf;

public static final class MXFDemuxer.OP {
    public static final MXFDemuxer.OP OP1a = new MXFDemuxer.OP(1, 1);
    public static final MXFDemuxer.OP OP1b = new MXFDemuxer.OP(1, 2);
    public static final MXFDemuxer.OP OP1c = new MXFDemuxer.OP(1, 3);
    public static final MXFDemuxer.OP OP2a = new MXFDemuxer.OP(2, 1);
    public static final MXFDemuxer.OP OP2b = new MXFDemuxer.OP(2, 2);
    public static final MXFDemuxer.OP OP2c = new MXFDemuxer.OP(2, 3);
    public static final MXFDemuxer.OP OP3a = new MXFDemuxer.OP(3, 1);
    public static final MXFDemuxer.OP OP3b = new MXFDemuxer.OP(3, 2);
    public static final MXFDemuxer.OP OP3c = new MXFDemuxer.OP(3, 3);
    public static final MXFDemuxer.OP OPAtom = new MXFDemuxer.OP(16, 0);
    private static final MXFDemuxer.OP[] _values = new MXFDemuxer.OP[]{OP1a, OP1b, OP1c, OP2a, OP2b, OP2c, OP3a, OP3b, OP3c, OPAtom};
    public int major;
    public int minor;

    private MXFDemuxer.OP(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }

    public static MXFDemuxer.OP[] values() {
        return _values;
    }
}
