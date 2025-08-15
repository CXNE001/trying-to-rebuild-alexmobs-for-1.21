/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.api;

import com.github.alexthe666.citadel.repack.jaad.mp4.api.AudioTrack;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.Track;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.VideoTrack;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.drm.ITunesProtection;
import com.github.alexthe666.citadel.repack.jaad.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jaad.mp4.boxes.impl.OriginalFormatBox;
import com.github.alexthe666.citadel.repack.jaad.mp4.boxes.impl.SchemeTypeBox;

public abstract class Protection {
    private final Track.Codec originalFormat;

    static Protection parse(Box sinf) {
        SchemeTypeBox schm;
        long l;
        Protection p = null;
        if (sinf.hasChild(1935894637L) && (l = (schm = (SchemeTypeBox)sinf.getChild(1935894637L)).getSchemeType()) == Scheme.ITUNES_FAIR_PLAY.type) {
            p = new ITunesProtection(sinf);
        }
        if (p == null) {
            p = new UnknownProtection(sinf);
        }
        return p;
    }

    protected Protection(Box sinf) {
        long type = ((OriginalFormatBox)sinf.getChild(1718775137L)).getOriginalFormat();
        Track.Codec c = AudioTrack.AudioCodec.forType(type);
        this.originalFormat = !c.equals(AudioTrack.AudioCodec.UNKNOWN_AUDIO_CODEC) ? c : (!(c = VideoTrack.VideoCodec.forType(type)).equals(VideoTrack.VideoCodec.UNKNOWN_VIDEO_CODEC) ? c : null);
    }

    Track.Codec getOriginalFormat() {
        return this.originalFormat;
    }

    public abstract Scheme getScheme();

    public static enum Scheme {
        ITUNES_FAIR_PLAY(1769239918L),
        UNKNOWN(-1L);

        private long type;

        private Scheme(long type) {
            this.type = type;
        }
    }

    private static class UnknownProtection
    extends Protection {
        UnknownProtection(Box sinf) {
            super(sinf);
        }

        @Override
        public Scheme getScheme() {
            return Scheme.UNKNOWN;
        }
    }
}
