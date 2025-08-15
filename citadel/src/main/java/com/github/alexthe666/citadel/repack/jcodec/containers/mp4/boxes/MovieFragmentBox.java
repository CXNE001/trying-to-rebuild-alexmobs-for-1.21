/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieFragmentHeaderBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TrackFragmentBox;

public class MovieFragmentBox
extends NodeBox {
    private MovieBox moov;

    public MovieFragmentBox(Header atom) {
        super(atom);
    }

    public static String fourcc() {
        return "moof";
    }

    public MovieBox getMovie() {
        return this.moov;
    }

    public void setMovie(MovieBox moov) {
        this.moov = moov;
    }

    public TrackFragmentBox[] getTracks() {
        return (TrackFragmentBox[])NodeBox.findAll((Box)this, TrackFragmentBox.class, (String)TrackFragmentBox.fourcc());
    }

    public int getSequenceNumber() {
        MovieFragmentHeaderBox mfhd = NodeBox.findFirst(this, MovieFragmentHeaderBox.class, MovieFragmentHeaderBox.fourcc());
        if (mfhd == null) {
            throw new RuntimeException("Corrupt movie fragment, no header atom found");
        }
        return mfhd.getSequenceNumber();
    }

    public static MovieFragmentBox createMovieFragmentBox() {
        return new MovieFragmentBox(new Header(MovieFragmentBox.fourcc()));
    }
}
