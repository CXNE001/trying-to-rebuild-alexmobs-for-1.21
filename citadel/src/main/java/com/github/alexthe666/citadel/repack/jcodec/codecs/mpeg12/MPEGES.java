/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.MPEGDecoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.SegmentReader;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.bitstream.PictureHeader;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPEGPacket;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

public class MPEGES
extends SegmentReader {
    private int frameNo;
    public long lastKnownDuration;

    public MPEGES(ReadableByteChannel channel, int fetchSize) throws IOException {
        super(channel, fetchSize);
    }

    public MPEGPacket frame(ByteBuffer buffer) throws IOException {
        MPEGPacket mPEGPacket;
        ByteBuffer dup = buffer.duplicate();
        while (this.curMarker != 256 && this.curMarker != 435 && this.skipToMarker()) {
        }
        while (this.curMarker != 256 && this.readToNextMarker(dup)) {
        }
        this.readToNextMarker(dup);
        while (this.curMarker != 256 && this.curMarker != 435 && this.readToNextMarker(dup)) {
        }
        dup.flip();
        PictureHeader ph = MPEGDecoder.getPictureHeader(dup.duplicate());
        if (dup.hasRemaining()) {
            long l = this.frameNo++;
            mPEGPacket = new MPEGPacket(dup, 0L, 90000, 0L, l, ph.picture_coding_type <= 1 ? Packet.FrameType.KEY : Packet.FrameType.INTER, null);
        } else {
            mPEGPacket = null;
        }
        return mPEGPacket;
    }

    public MPEGPacket getFrame() throws IOException {
        MPEGPacket mPEGPacket;
        while (this.curMarker != 256 && this.curMarker != 435 && this.skipToMarker()) {
        }
        ArrayList<ByteBuffer> buffers = new ArrayList<ByteBuffer>();
        while (this.curMarker != 256 && !this.done) {
            this.readToNextMarkerBuffers(buffers);
        }
        this.readToNextMarkerBuffers(buffers);
        while (this.curMarker != 256 && this.curMarker != 435 && !this.done) {
            this.readToNextMarkerBuffers(buffers);
        }
        ByteBuffer dup = NIOUtils.combineBuffers(buffers);
        PictureHeader ph = MPEGDecoder.getPictureHeader(dup.duplicate());
        if (dup.hasRemaining()) {
            long l = this.frameNo++;
            mPEGPacket = new MPEGPacket(dup, 0L, 90000, 0L, l, ph.picture_coding_type <= 1 ? Packet.FrameType.KEY : Packet.FrameType.INTER, null);
        } else {
            mPEGPacket = null;
        }
        return mPEGPacket;
    }
}
