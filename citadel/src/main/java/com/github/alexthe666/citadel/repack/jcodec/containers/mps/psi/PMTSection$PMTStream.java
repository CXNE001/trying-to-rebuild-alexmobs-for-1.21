/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi;

import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MTSStreamType;
import java.util.List;

public static class PMTSection.PMTStream {
    private int streamTypeTag;
    private int pid;
    private List<MPSUtils.MPEGMediaDescriptor> descriptors;
    private MTSStreamType streamType;

    public PMTSection.PMTStream(int streamTypeTag, int pid, List<MPSUtils.MPEGMediaDescriptor> descriptors) {
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
