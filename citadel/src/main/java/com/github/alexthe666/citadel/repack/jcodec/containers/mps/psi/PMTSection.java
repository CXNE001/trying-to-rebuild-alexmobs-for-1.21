/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MTSStreamType;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi.PSISection;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class PMTSection
extends PSISection {
    private int pcrPid;
    private Tag[] tags;
    private PMTStream[] streams;

    public PMTSection(PSISection psi, int pcrPid, Tag[] tags, PMTStream[] streams) {
        super(psi.tableId, psi.specificId, psi.versionNumber, psi.currentNextIndicator, psi.sectionNumber, psi.lastSectionNumber);
        this.pcrPid = pcrPid;
        this.tags = tags;
        this.streams = streams;
    }

    public int getPcrPid() {
        return this.pcrPid;
    }

    public Tag[] getTags() {
        return this.tags;
    }

    public PMTStream[] getStreams() {
        return this.streams;
    }

    public static PMTSection parsePMT(ByteBuffer data) {
        PSISection psi = PSISection.parsePSI(data);
        int w1 = data.getShort() & 0xFFFF;
        int pcrPid = w1 & 0x1FFF;
        int w2 = data.getShort() & 0xFFFF;
        int programInfoLength = w2 & 0xFFF;
        List<Tag> tags = PMTSection.parseTags(NIOUtils.read(data, programInfoLength));
        ArrayList<PMTStream> streams = new ArrayList<PMTStream>();
        while (data.remaining() > 4) {
            int streamType = data.get() & 0xFF;
            int wn = data.getShort() & 0xFFFF;
            int elementaryPid = wn & 0x1FFF;
            int wn1 = data.getShort() & 0xFFFF;
            int esInfoLength = wn1 & 0xFFF;
            ByteBuffer read = NIOUtils.read(data, esInfoLength);
            streams.add(new PMTStream(streamType, elementaryPid, MPSUtils.parseDescriptors(read)));
        }
        return new PMTSection(psi, pcrPid, tags.toArray(new Tag[0]), streams.toArray(new PMTStream[0]));
    }

    static List<Tag> parseTags(ByteBuffer bb) {
        ArrayList<Tag> tags = new ArrayList<Tag>();
        while (bb.hasRemaining()) {
            byte tag = bb.get();
            byte tagLen = bb.get();
            tags.add(new Tag(tag, NIOUtils.read(bb, tagLen)));
        }
        return tags;
    }

    public static class PMTStream {
        private int streamTypeTag;
        private int pid;
        private List<MPSUtils.MPEGMediaDescriptor> descriptors;
        private MTSStreamType streamType;

        public PMTStream(int streamTypeTag, int pid, List<MPSUtils.MPEGMediaDescriptor> descriptors) {
            this.streamTypeTag = streamTypeTag;
            this.pid = pid;
            this.descriptors = descriptors;
            this.streamType = MTSStreamType.fromTag(streamTypeTag);
        }

        public int getStreamTypeTag() {
            return this.streamTypeTag;
        }

        public MTSStreamType getStreamType() {
            return this.streamType;
        }

        public int getPid() {
            return this.pid;
        }

        public List<MPSUtils.MPEGMediaDescriptor> getDesctiptors() {
            return this.descriptors;
        }
    }

    public static class Tag {
        private int tag;
        private ByteBuffer content;

        public Tag(int tag, ByteBuffer content) {
            this.tag = tag;
            this.content = content;
        }

        public int getTag() {
            return this.tag;
        }

        public ByteBuffer getContent() {
            return this.content;
        }
    }
}
