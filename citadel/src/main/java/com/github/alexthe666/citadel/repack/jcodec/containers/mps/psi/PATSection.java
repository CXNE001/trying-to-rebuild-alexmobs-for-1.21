/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi;

import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.IntIntMap;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi.PSISection;
import java.nio.ByteBuffer;

public class PATSection
extends PSISection {
    private int[] networkPids;
    private IntIntMap programs;

    public PATSection(PSISection psi, int[] networkPids, IntIntMap programs) {
        super(psi.tableId, psi.specificId, psi.versionNumber, psi.currentNextIndicator, psi.sectionNumber, psi.lastSectionNumber);
        this.networkPids = networkPids;
        this.programs = programs;
    }

    public int[] getNetworkPids() {
        return this.networkPids;
    }

    public IntIntMap getPrograms() {
        return this.programs;
    }

    public static PATSection parsePAT(ByteBuffer data) {
        PSISection psi = PSISection.parsePSI(data);
        IntArrayList networkPids = IntArrayList.createIntArrayList();
        IntIntMap programs = new IntIntMap();
        while (data.remaining() > 4) {
            int programNum = data.getShort() & 0xFFFF;
            short w = data.getShort();
            int pid = w & 0x1FFF;
            if (programNum == 0) {
                networkPids.add(pid);
                continue;
            }
            programs.put(programNum, pid);
        }
        return new PATSection(psi, networkPids.toArray(), programs);
    }
}
