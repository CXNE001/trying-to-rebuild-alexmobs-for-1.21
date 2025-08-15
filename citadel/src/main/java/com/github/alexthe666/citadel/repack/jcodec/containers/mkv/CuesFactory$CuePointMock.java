/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv;

import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.MKVType;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlMaster;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlUint;

public static class CuesFactory.CuePointMock {
    public int cueClusterPositionSize;
    public long elementOffset;
    private long timecode;
    private long size;
    private byte[] id;

    public static CuesFactory.CuePointMock make(EbmlMaster c) {
        MKVType[] path = new MKVType[]{MKVType.Cluster, MKVType.Timecode};
        EbmlUint tc = (EbmlUint)MKVType.findFirst(c, path);
        return CuesFactory.CuePointMock.doMake(c.id, tc.getUint(), c.size());
    }

    public static CuesFactory.CuePointMock doMake(byte[] id, long timecode, long size) {
        CuesFactory.CuePointMock mock = new CuesFactory.CuePointMock();
        mock.id = id;
        mock.timecode = timecode;
        mock.size = size;
        return mock;
    }

    static /* synthetic */ long access$000(CuesFactory.CuePointMock x0) {
        return x0.size;
    }

    static /* synthetic */ long access$100(CuesFactory.CuePointMock x0) {
        return x0.timecode;
    }

    static /* synthetic */ byte[] access$200(CuesFactory.CuePointMock x0) {
        return x0.id;
    }
}
