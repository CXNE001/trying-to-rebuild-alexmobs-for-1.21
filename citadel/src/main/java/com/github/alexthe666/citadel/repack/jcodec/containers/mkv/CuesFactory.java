/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv;

import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.MKVType;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlMaster;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlUint;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.util.EbmlUtil;
import java.util.ArrayList;
import java.util.List;

public class CuesFactory {
    List<CuePointMock> a = new ArrayList<CuePointMock>();
    private final long offsetBase;
    private long currentDataOffset = 0L;
    private long videoTrackNr;

    public CuesFactory(long offset, long videoTrack) {
        this.offsetBase = offset;
        this.videoTrackNr = videoTrack;
        this.currentDataOffset += this.offsetBase;
    }

    public void addFixedSize(CuePointMock z) {
        z.elementOffset = this.currentDataOffset;
        z.cueClusterPositionSize = 8;
        this.currentDataOffset += z.size;
        this.a.add(z);
    }

    public void add(CuePointMock z) {
        z.elementOffset = this.currentDataOffset;
        z.cueClusterPositionSize = EbmlUint.calculatePayloadSize(z.elementOffset);
        this.currentDataOffset += z.size;
        this.a.add(z);
    }

    public EbmlMaster createCues() {
        int estimatedSize = this.computeCuesSize();
        EbmlMaster cues = (EbmlMaster)MKVType.createByType(MKVType.Cues);
        for (CuePointMock cpm : this.a) {
            EbmlMaster cuePoint = (EbmlMaster)MKVType.createByType(MKVType.CuePoint);
            EbmlUint cueTime = (EbmlUint)MKVType.createByType(MKVType.CueTime);
            cueTime.setUint(cpm.timecode);
            cuePoint.add(cueTime);
            EbmlMaster cueTrackPositions = (EbmlMaster)MKVType.createByType(MKVType.CueTrackPositions);
            EbmlUint cueTrack = (EbmlUint)MKVType.createByType(MKVType.CueTrack);
            cueTrack.setUint(this.videoTrackNr);
            cueTrackPositions.add(cueTrack);
            EbmlUint cueClusterPosition = (EbmlUint)MKVType.createByType(MKVType.CueClusterPosition);
            cueClusterPosition.setUint(cpm.elementOffset + (long)estimatedSize);
            if (cueClusterPosition.data.limit() != cpm.cueClusterPositionSize) {
                System.err.println("estimated size of CueClusterPosition differs from the one actually used. ElementId: " + EbmlUtil.toHexString(cpm.id) + " " + cueClusterPosition.getData().limit() + " vs " + cpm.cueClusterPositionSize);
            }
            cueTrackPositions.add(cueClusterPosition);
            cuePoint.add(cueTrackPositions);
            cues.add(cuePoint);
        }
        return cues;
    }

    public int computeCuesSize() {
        int cuesSize = this.estimateSize();
        boolean reindex = false;
        block0: do {
            reindex = false;
            for (CuePointMock z : this.a) {
                int minByteSize = EbmlUint.calculatePayloadSize(z.elementOffset + (long)cuesSize);
                if (minByteSize > z.cueClusterPositionSize) {
                    System.out.println(minByteSize + ">" + z.cueClusterPositionSize);
                    System.err.println("Size " + cuesSize + " seems too small for element " + EbmlUtil.toHexString(z.id) + " increasing size by one.");
                    ++z.cueClusterPositionSize;
                    ++cuesSize;
                    reindex = true;
                    continue block0;
                }
                if (minByteSize >= z.cueClusterPositionSize) continue;
                throw new RuntimeException("Downsizing the index is not well thought through");
            }
        } while (reindex);
        return cuesSize;
    }

    public int estimateFixedSize(int numberOfClusters) {
        int s = 34 * numberOfClusters;
        s += MKVType.Cues.id.length + EbmlUtil.ebmlLength(s);
        return s;
    }

    public int estimateSize() {
        int s = 0;
        for (CuePointMock cpm : this.a) {
            s += CuesFactory.estimateCuePointSize(EbmlUint.calculatePayloadSize(cpm.timecode), EbmlUint.calculatePayloadSize(this.videoTrackNr), EbmlUint.calculatePayloadSize(cpm.elementOffset));
        }
        s += MKVType.Cues.id.length + EbmlUtil.ebmlLength(s);
        return s;
    }

    public static int estimateCuePointSize(int timecodeSizeInBytes, int trackNrSizeInBytes, int clusterPositionSizeInBytes) {
        int cueTimeSize = MKVType.CueTime.id.length + EbmlUtil.ebmlLength(timecodeSizeInBytes) + timecodeSizeInBytes;
        int cueTrackPositionSize = MKVType.CueTrack.id.length + EbmlUtil.ebmlLength(trackNrSizeInBytes) + trackNrSizeInBytes + MKVType.CueClusterPosition.id.length + EbmlUtil.ebmlLength(clusterPositionSizeInBytes) + clusterPositionSizeInBytes;
        cueTrackPositionSize += MKVType.CueTrackPositions.id.length + EbmlUtil.ebmlLength(cueTrackPositionSize);
        int cuePointSize = MKVType.CuePoint.id.length + EbmlUtil.ebmlLength(cueTimeSize + cueTrackPositionSize) + cueTimeSize + cueTrackPositionSize;
        return cuePointSize;
    }

    public static class CuePointMock {
        public int cueClusterPositionSize;
        public long elementOffset;
        private long timecode;
        private long size;
        private byte[] id;

        public static CuePointMock make(EbmlMaster c) {
            MKVType[] path = new MKVType[]{MKVType.Cluster, MKVType.Timecode};
            EbmlUint tc = (EbmlUint)MKVType.findFirst(c, path);
            return CuePointMock.doMake(c.id, tc.getUint(), c.size());
        }

        public static CuePointMock doMake(byte[] id, long timecode, long size) {
            CuePointMock mock = new CuePointMock();
            mock.id = id;
            mock.timecode = timecode;
            mock.size = size;
            return mock;
        }
    }
}
