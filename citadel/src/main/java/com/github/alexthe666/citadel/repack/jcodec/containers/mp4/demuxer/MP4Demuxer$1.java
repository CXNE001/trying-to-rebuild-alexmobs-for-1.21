/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.demuxer;

import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TrakBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.demuxer.AbstractMP4DemuxerTrack;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.demuxer.MP4Demuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.demuxer.MP4DemuxerTrack;

static final class MP4Demuxer.1
extends MP4Demuxer {
    MP4Demuxer.1(SeekableByteChannel input) {
        super(input);
    }

    @Override
    protected AbstractMP4DemuxerTrack newTrack(TrakBox trak) {
        return new MP4DemuxerTrack(this.movie, trak, this.input);
    }
}
