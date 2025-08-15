/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.FixTimestamp;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MTSUtils;
import java.nio.ByteBuffer;

class FixTimestamp.1
extends MTSUtils.TSReader {
    final /* synthetic */ FixTimestamp val$self;

    FixTimestamp.1(boolean flush, FixTimestamp fixTimestamp) {
        this.val$self = fixTimestamp;
        super(flush);
    }

    @Override
    public boolean onPkt(int guid, boolean payloadStart, ByteBuffer bb, long filePos, boolean sectionSyntax, ByteBuffer fullPkt) {
        return this.val$self.processPacket(payloadStart, bb, sectionSyntax, fullPkt);
    }
}
