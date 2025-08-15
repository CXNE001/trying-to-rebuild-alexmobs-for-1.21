/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv;

import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.MKVType;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlBase;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlBin;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlMaster;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlVoid;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.util.EbmlUtil;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MKVParser {
    private SeekableByteChannel channel;
    private LinkedList<EbmlMaster> trace;

    public MKVParser(SeekableByteChannel channel) {
        this.channel = channel;
        this.trace = new LinkedList();
    }

    public List<EbmlMaster> parse() throws IOException {
        ArrayList<EbmlMaster> tree = new ArrayList<EbmlMaster>();
        EbmlBase e = null;
        while ((e = this.nextElement()) != null) {
            if (!this.isKnownType(e.id)) {
                System.err.println("Unspecified header: " + EbmlUtil.toHexString(e.id) + " at " + e.offset);
            }
            while (!this.possibleChild(this.trace.peekFirst(), e)) {
                this.closeElem(this.trace.removeFirst(), tree);
            }
            this.openElem(e);
            if (e instanceof EbmlMaster) {
                this.trace.push((EbmlMaster)e);
                continue;
            }
            if (e instanceof EbmlBin) {
                EbmlBin bin = (EbmlBin)e;
                EbmlMaster traceTop = this.trace.peekFirst();
                if (traceTop.dataOffset + (long)traceTop.dataLen < e.dataOffset + (long)e.dataLen) {
                    this.channel.setPosition(traceTop.dataOffset + (long)traceTop.dataLen);
                } else {
                    try {
                        bin.readChannel(this.channel);
                    }
                    catch (OutOfMemoryError oome) {
                        throw new RuntimeException(e.type + " 0x" + EbmlUtil.toHexString(bin.id) + " size: " + Long.toHexString(bin.dataLen) + " offset: 0x" + Long.toHexString(e.offset), oome);
                    }
                }
                this.trace.peekFirst().add(e);
                continue;
            }
            if (e instanceof EbmlVoid) {
                ((EbmlVoid)e).skip(this.channel);
                continue;
            }
            throw new RuntimeException("Currently there are no elements that are neither Master nor Binary, should never actually get here");
        }
        while (this.trace.peekFirst() != null) {
            this.closeElem(this.trace.removeFirst(), tree);
        }
        return tree;
    }

    private boolean possibleChild(EbmlMaster parent, EbmlBase child) {
        if (!(parent == null || !MKVType.Cluster.equals(parent.type) || child == null || MKVType.Cluster.equals(child.type) || MKVType.Info.equals(child.type) || MKVType.SeekHead.equals(child.type) || MKVType.Tracks.equals(child.type) || MKVType.Cues.equals(child.type) || MKVType.Attachments.equals(child.type) || MKVType.Tags.equals(child.type) || MKVType.Chapters.equals(child.type))) {
            return true;
        }
        return MKVType.possibleChild(parent, child);
    }

    private void openElem(EbmlBase e) {
    }

    private void closeElem(EbmlMaster e, List<EbmlMaster> tree) {
        if (this.trace.peekFirst() == null) {
            tree.add(e);
        } else {
            this.trace.peekFirst().add(e);
        }
    }

    private EbmlBase nextElement() throws IOException {
        long offset = this.channel.position();
        if (offset >= this.channel.size()) {
            return null;
        }
        byte[] typeId = MKVParser.readEbmlId(this.channel);
        while (typeId == null && !this.isKnownType(typeId) && offset < this.channel.size()) {
            this.channel.setPosition(++offset);
            typeId = MKVParser.readEbmlId(this.channel);
        }
        long dataLen = MKVParser.readEbmlInt(this.channel);
        Object elem = MKVType.createById(typeId, offset);
        ((EbmlBase)elem).offset = offset;
        ((EbmlBase)elem).typeSizeLength = (int)(this.channel.position() - offset);
        ((EbmlBase)elem).dataOffset = this.channel.position();
        ((EbmlBase)elem).dataLen = (int)dataLen;
        return elem;
    }

    public boolean isKnownType(byte[] b) {
        if (!this.trace.isEmpty() && MKVType.Cluster.equals(this.trace.peekFirst().type)) {
            return true;
        }
        return MKVType.isSpecifiedHeader(b);
    }

    public static byte[] readEbmlId(SeekableByteChannel source) throws IOException {
        if (source.position() == source.size()) {
            return null;
        }
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.limit(1);
        source.read(buffer);
        buffer.flip();
        byte firstByte = buffer.get();
        int numBytes = EbmlUtil.computeLength(firstByte);
        if (numBytes == 0) {
            return null;
        }
        if (numBytes > 1) {
            buffer.limit(numBytes);
            source.read(buffer);
        }
        buffer.flip();
        ByteBuffer val = ByteBuffer.allocate(buffer.remaining());
        val.put(buffer);
        return val.array();
    }

    public static long readEbmlInt(SeekableByteChannel source) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.limit(1);
        source.read(buffer);
        buffer.flip();
        byte firstByte = buffer.get();
        int length = EbmlUtil.computeLength(firstByte);
        if (length == 0) {
            throw new RuntimeException("Invalid ebml integer size.");
        }
        buffer.limit(length);
        source.read(buffer);
        buffer.position(1);
        long value = firstByte & 255 >>> length;
        --length;
        while (length > 0) {
            value = value << 8 | (long)(buffer.get() & 0xFF);
            --length;
        }
        return value;
    }
}
