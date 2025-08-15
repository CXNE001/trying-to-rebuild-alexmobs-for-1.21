/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv;

import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.MKVType;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlBase;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlBin;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlMaster;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlUint;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.util.EbmlUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class SeekHeadFactory {
    List<SeekMock> a = new ArrayList<SeekMock>();
    long currentDataOffset = 0L;

    public void add(EbmlBase e) {
        SeekMock z = SeekMock.make(e);
        z.dataOffset = this.currentDataOffset;
        z.seekPointerSize = EbmlUint.calculatePayloadSize(z.dataOffset);
        this.currentDataOffset += (long)z.size;
        this.a.add(z);
    }

    public EbmlMaster indexSeekHead() {
        int seekHeadSize = this.computeSeekHeadSize();
        EbmlMaster seekHead = (EbmlMaster)MKVType.createByType(MKVType.SeekHead);
        for (SeekMock z : this.a) {
            EbmlMaster seek = (EbmlMaster)MKVType.createByType(MKVType.Seek);
            EbmlBin seekId = (EbmlBin)MKVType.createByType(MKVType.SeekID);
            seekId.setBuf(ByteBuffer.wrap(z.id));
            seek.add(seekId);
            EbmlUint seekPosition = (EbmlUint)MKVType.createByType(MKVType.SeekPosition);
            seekPosition.setUint(z.dataOffset + (long)seekHeadSize);
            if (seekPosition.data.limit() != z.seekPointerSize) {
                System.err.println("estimated size of seekPosition differs from the one actually used. ElementId: " + EbmlUtil.toHexString(z.id) + " " + seekPosition.getData().limit() + " vs " + z.seekPointerSize);
            }
            seek.add(seekPosition);
            seekHead.add(seek);
        }
        ByteBuffer mux = seekHead.getData();
        if (mux.limit() != seekHeadSize) {
            System.err.println("estimated size of seekHead differs from the one actually used. " + mux.limit() + " vs " + seekHeadSize);
        }
        return seekHead;
    }

    public int computeSeekHeadSize() {
        int seekHeadSize = this.estimateSize();
        boolean reindex = false;
        block0: do {
            reindex = false;
            for (SeekMock z : this.a) {
                int minSize = EbmlUint.calculatePayloadSize(z.dataOffset + (long)seekHeadSize);
                if (minSize > z.seekPointerSize) {
                    System.out.println("Size " + seekHeadSize + " seems too small for element " + EbmlUtil.toHexString(z.id) + " increasing size by one.");
                    ++z.seekPointerSize;
                    ++seekHeadSize;
                    reindex = true;
                    continue block0;
                }
                if (minSize >= z.seekPointerSize) continue;
                throw new RuntimeException("Downsizing the index is not well thought through.");
            }
        } while (reindex);
        return seekHeadSize;
    }

    int estimateSize() {
        int s = MKVType.SeekHead.id.length + 1;
        s += SeekHeadFactory.estimeteSeekSize(this.a.get((int)0).id.length, 1);
        for (int i = 1; i < this.a.size(); ++i) {
            s += SeekHeadFactory.estimeteSeekSize(this.a.get((int)i).id.length, this.a.get((int)i).seekPointerSize);
        }
        return s;
    }

    public static int estimeteSeekSize(int idLength, int offsetSizeInBytes) {
        int seekIdSize = MKVType.SeekID.id.length + EbmlUtil.ebmlLength(idLength) + idLength;
        int seekPositionSize = MKVType.SeekPosition.id.length + EbmlUtil.ebmlLength(offsetSizeInBytes) + offsetSizeInBytes;
        int seekSize = MKVType.Seek.id.length + EbmlUtil.ebmlLength(seekIdSize + seekPositionSize) + seekIdSize + seekPositionSize;
        return seekSize;
    }

    public static class SeekMock {
        public long dataOffset;
        byte[] id;
        int size;
        int seekPointerSize;

        public static SeekMock make(EbmlBase e) {
            SeekMock z = new SeekMock();
            z.id = e.id;
            z.size = (int)e.size();
            return z;
        }
    }
}
