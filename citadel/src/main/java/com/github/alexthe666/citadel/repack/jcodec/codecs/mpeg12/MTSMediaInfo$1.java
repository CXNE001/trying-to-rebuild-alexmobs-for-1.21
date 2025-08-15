/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.MPSMediaInfo;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MTSUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi.PMTSection;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

class MTSMediaInfo.1
extends MTSUtils.TSReader {
    private ByteBuffer pmtBuffer;
    private int pmtPid;
    private boolean pmtDone;
    final /* synthetic */ List val$pmtSections;
    final /* synthetic */ Map val$pids;
    final /* synthetic */ List val$result;

    MTSMediaInfo.1(boolean flush, List list, Map map, List list2) {
        this.val$pmtSections = list;
        this.val$pids = map;
        this.val$result = list2;
        super(flush);
        this.pmtPid = -1;
    }

    @Override
    protected boolean onPkt(int guid, boolean payloadStart, ByteBuffer tsBuf, long filePos, boolean sectionSyntax, ByteBuffer fullPkt) {
        block12: {
            if (guid == 0) {
                this.pmtPid = MTSUtils.parsePAT(tsBuf);
            } else if (guid == this.pmtPid && !this.pmtDone) {
                if (this.pmtBuffer == null) {
                    this.pmtBuffer = ByteBuffer.allocate((tsBuf.duplicate().getInt() >> 8 & 0x3FF) + 3);
                } else if (this.pmtBuffer.hasRemaining()) {
                    NIOUtils.writeL(this.pmtBuffer, tsBuf, Math.min(this.pmtBuffer.remaining(), tsBuf.remaining()));
                }
                if (!this.pmtBuffer.hasRemaining()) {
                    this.pmtBuffer.flip();
                    PMTSection pmt = MTSUtils.parsePMT(this.pmtBuffer);
                    this.val$pmtSections.add(pmt);
                    PMTSection.PMTStream[] streams = pmt.getStreams();
                    for (int i = 0; i < streams.length; ++i) {
                        PMTSection.PMTStream stream = streams[i];
                        if (this.val$pids.containsKey(stream.getPid())) continue;
                        this.val$pids.put(stream.getPid(), new MPSMediaInfo());
                    }
                    this.pmtDone = pmt.getSectionNumber() == pmt.getLastSectionNumber();
                    this.pmtBuffer = null;
                }
            } else if (this.val$pids.containsKey(guid)) {
                try {
                    ((MPSMediaInfo)this.val$pids.get(guid)).analyseBuffer(tsBuf, filePos);
                }
                catch (MPSMediaInfo.MediaInfoDone e) {
                    this.val$result.addAll(((MPSMediaInfo)this.val$pids.get(guid)).getInfos());
                    this.val$pids.remove(guid);
                    if (this.val$pids.size() != 0) break block12;
                    return false;
                }
            }
        }
        return true;
    }
}
