/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi;

import java.nio.ByteBuffer;

public class PSISection {
    protected int tableId;
    protected int specificId;
    protected int versionNumber;
    protected int currentNextIndicator;
    protected int sectionNumber;
    protected int lastSectionNumber;

    public PSISection(int tableId, int specificId, int versionNumber, int currentNextIndicator, int sectionNumber, int lastSectionNumber) {
        this.tableId = tableId;
        this.specificId = specificId;
        this.versionNumber = versionNumber;
        this.currentNextIndicator = currentNextIndicator;
        this.sectionNumber = sectionNumber;
        this.lastSectionNumber = lastSectionNumber;
    }

    public static PSISection parsePSI(ByteBuffer data) {
        int tableId = data.get() & 0xFF;
        int w0 = data.getShort() & 0xFFFF;
        if ((w0 & 0xC000) != 32768) {
            throw new RuntimeException("Invalid section data");
        }
        int sectionLength = w0 & 0xFFF;
        data.limit(data.position() + sectionLength);
        int specificId = data.getShort() & 0xFFFF;
        int b0 = data.get() & 0xFF;
        int versionNumber = b0 >> 1 & 0x1F;
        int currentNextIndicator = b0 & 1;
        int sectionNumber = data.get() & 0xFF;
        int lastSectionNumber = data.get() & 0xFF;
        return new PSISection(tableId, specificId, versionNumber, currentNextIndicator, sectionNumber, lastSectionNumber);
    }

    public int getTableId() {
        return this.tableId;
    }

    public int getSpecificId() {
        return this.specificId;
    }

    public int getVersionNumber() {
        return this.versionNumber;
    }

    public int getCurrentNextIndicator() {
        return this.currentNextIndicator;
    }

    public int getSectionNumber() {
        return this.sectionNumber;
    }

    public int getLastSectionNumber() {
        return this.lastSectionNumber;
    }
}
