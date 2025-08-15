/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.MXFDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.KLV;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.MXFPartition;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public static class MXFDemuxer.Fast
extends MXFDemuxer {
    public MXFDemuxer.Fast(SeekableByteChannel ch) throws IOException {
        super(ch);
    }

    @Override
    public void parseHeader(SeekableByteChannel ff) throws IOException {
        this.partitions = new ArrayList();
        this.metadata = new ArrayList();
        this.header = MXFDemuxer.Fast.readHeaderPartition(ff);
        this.metadata.addAll(MXFDemuxer.Fast.readPartitionMeta(ff, this.header));
        this.partitions.add(this.header);
        ff.setPosition(this.header.getPack().getFooterPartition());
        KLV kl = KLV.readKL(ff);
        if (kl != null) {
            ByteBuffer fetchFrom = NIOUtils.fetchFromChannel(ff, (int)kl.len);
            MXFPartition footer = MXFPartition.read(kl.key, fetchFrom, ff.position() - kl.offset, ff.size());
            this.metadata.addAll(MXFDemuxer.Fast.readPartitionMeta(ff, footer));
        }
    }
}
