/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.FileTypeBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieBox;

public static class MP4Util.Movie {
    private FileTypeBox ftyp;
    private MovieBox moov;

    public MP4Util.Movie(FileTypeBox ftyp, MovieBox moov) {
        this.ftyp = ftyp;
        this.moov = moov;
    }

    public FileTypeBox getFtyp() {
        return this.ftyp;
    }

    public MovieBox getMoov() {
        return this.moov;
    }

    static /* synthetic */ MovieBox access$000(MP4Util.Movie x0) {
        return x0.moov;
    }
}
