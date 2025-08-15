/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.DeltaEntries;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.IndexEntries;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.MXFInterchangeObject;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

public class IndexSegment
extends MXFInterchangeObject {
    private IndexEntries ie;
    private int editUnitByteCount;
    private DeltaEntries deltaEntries;
    private int indexSID;
    private int bodySID;
    private int indexEditRateNum;
    private int indexEditRateDen;
    private long indexStartPosition;
    private long indexDuration;
    private UL instanceUID;
    private int sliceCount;
    private int posTableCount;

    public IndexSegment(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block13: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            ByteBuffer _bb = entry.getValue();
            switch (entry.getKey()) {
                case 15370: {
                    this.instanceUID = UL.read(_bb);
                    break;
                }
                case 16133: {
                    this.editUnitByteCount = _bb.getInt();
                    break;
                }
                case 16134: {
                    this.indexSID = _bb.getInt();
                    break;
                }
                case 16135: {
                    this.bodySID = _bb.getInt();
                    break;
                }
                case 16136: {
                    this.sliceCount = _bb.get() & 0xFF;
                    break;
                }
                case 16137: {
                    this.deltaEntries = DeltaEntries.read(_bb);
                    break;
                }
                case 16138: {
                    this.ie = IndexEntries.read(_bb);
                    break;
                }
                case 16139: {
                    this.indexEditRateNum = _bb.getInt();
                    this.indexEditRateDen = _bb.getInt();
                    break;
                }
                case 16140: {
                    this.indexStartPosition = _bb.getLong();
                    break;
                }
                case 16141: {
                    this.indexDuration = _bb.getLong();
                    break;
                }
                case 16142: {
                    this.posTableCount = _bb.get() & 0xFF;
                    break;
                }
                default: {
                    Logger.warn(String.format("Unknown tag [" + this.ul + "]: %04x", entry.getKey()));
                    continue block13;
                }
            }
            it.remove();
        }
    }

    public IndexEntries getIe() {
        return this.ie;
    }

    public int getEditUnitByteCount() {
        return this.editUnitByteCount;
    }

    public DeltaEntries getDeltaEntries() {
        return this.deltaEntries;
    }

    public int getIndexSID() {
        return this.indexSID;
    }

    public int getBodySID() {
        return this.bodySID;
    }

    public int getIndexEditRateNum() {
        return this.indexEditRateNum;
    }

    public int getIndexEditRateDen() {
        return this.indexEditRateDen;
    }

    public long getIndexStartPosition() {
        return this.indexStartPosition;
    }

    public long getIndexDuration() {
        return this.indexDuration;
    }

    public UL getInstanceUID() {
        return this.instanceUID;
    }

    public int getSliceCount() {
        return this.sliceCount;
    }

    public int getPosTableCount() {
        return this.posTableCount;
    }
}
