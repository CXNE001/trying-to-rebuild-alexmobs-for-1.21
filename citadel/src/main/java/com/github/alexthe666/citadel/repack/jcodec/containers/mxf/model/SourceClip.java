/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.MXFStructuralComponent;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

public class SourceClip
extends MXFStructuralComponent {
    private long startPosition;
    private int sourceTrackId;
    private UL sourcePackageUid;

    public SourceClip(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        super.read(tags);
        Iterator<Map.Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        block5: while (it.hasNext()) {
            Map.Entry<Integer, ByteBuffer> entry = it.next();
            ByteBuffer _bb = entry.getValue();
            switch (entry.getKey()) {
                case 4609: {
                    this.startPosition = _bb.getLong();
                    break;
                }
                case 4353: {
                    this.sourcePackageUid = UL.read(_bb);
                    break;
                }
                case 4354: {
                    this.sourceTrackId = _bb.getInt();
                    break;
                }
                default: {
                    Logger.warn(String.format("Unknown tag [ " + this.ul + "]: %04x", entry.getKey()));
                    continue block5;
                }
            }
            it.remove();
        }
    }

    public UL getSourcePackageUid() {
        return this.sourcePackageUid;
    }

    public long getStartPosition() {
        return this.startPosition;
    }

    public int getSourceTrackId() {
        return this.sourceTrackId;
    }
}
