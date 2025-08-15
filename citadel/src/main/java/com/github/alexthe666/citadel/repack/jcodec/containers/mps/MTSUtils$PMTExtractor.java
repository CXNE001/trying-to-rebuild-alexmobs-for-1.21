/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MTSUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi.PMTSection;
import java.nio.ByteBuffer;

private static class MTSUtils.PMTExtractor
extends MTSUtils.TSReader {
    private int pmtGuid = -1;
    private PMTSection pmt;

    public MTSUtils.PMTExtractor() {
        super(false);
    }

    @Override
    public boolean onPkt(int guid, boolean payloadStart, ByteBuffer tsBuf, long filePos, boolean sectionSyntax, ByteBuffer fullPkt) {
        if (guid == 0) {
            this.pmtGuid = MTSUtils.parsePAT(tsBuf);
        } else if (this.pmtGuid != -1 && guid == this.pmtGuid) {
            this.pmt = MTSUtils.parsePMT(tsBuf);
            return false;
        }
        return true;
    }

    public PMTSection getPmt() {
        return this.pmt;
    }
}
