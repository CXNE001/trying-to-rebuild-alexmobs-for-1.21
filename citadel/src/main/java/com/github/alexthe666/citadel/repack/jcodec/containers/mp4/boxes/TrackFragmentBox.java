/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TrackFragmentHeaderBox;

public class TrackFragmentBox
extends NodeBox {
    public TrackFragmentBox(Header atom) {
        super(atom);
    }

    public static String fourcc() {
        return "traf";
    }

    public int getTrackId() {
        TrackFragmentHeaderBox tfhd = NodeBox.findFirst(this, TrackFragmentHeaderBox.class, TrackFragmentHeaderBox.fourcc());
        if (tfhd == null) {
            throw new RuntimeException("Corrupt track fragment, no header atom found");
        }
        return tfhd.getTrackId();
    }

    public static TrackFragmentBox createTrackFragmentBox() {
        return new TrackFragmentBox(new Header(TrackFragmentBox.fourcc()));
    }
}
