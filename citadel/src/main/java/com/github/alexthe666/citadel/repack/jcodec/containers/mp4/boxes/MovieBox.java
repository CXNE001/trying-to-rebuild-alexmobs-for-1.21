/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Rational;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.ClearApertureBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Edit;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.EncodedPixelBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieHeaderBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.SampleDescriptionBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TrakBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.VideoSampleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MovieBox
extends NodeBox {
    public MovieBox(Header atom) {
        super(atom);
    }

    public static String fourcc() {
        return "moov";
    }

    public static MovieBox createMovieBox() {
        return new MovieBox(new Header(MovieBox.fourcc()));
    }

    public TrakBox[] getTracks() {
        return (TrakBox[])NodeBox.findAll((Box)this, TrakBox.class, (String)"trak");
    }

    public TrakBox getVideoTrack() {
        TrakBox[] tracks = this.getTracks();
        for (int i = 0; i < tracks.length; ++i) {
            TrakBox trakBox = tracks[i];
            if (!trakBox.isVideo()) continue;
            return trakBox;
        }
        return null;
    }

    public TrakBox getTimecodeTrack() {
        TrakBox[] tracks = this.getTracks();
        for (int i = 0; i < tracks.length; ++i) {
            TrakBox trakBox = tracks[i];
            if (!trakBox.isTimecode()) continue;
            return trakBox;
        }
        return null;
    }

    public int getTimescale() {
        return this.getMovieHeader().getTimescale();
    }

    public long rescale(long tv, long ts) {
        return tv * (long)this.getTimescale() / ts;
    }

    public void fixTimescale(int newTs) {
        int oldTs = this.getTimescale();
        this.setTimescale(newTs);
        TrakBox[] tracks = this.getTracks();
        for (int i = 0; i < tracks.length; ++i) {
            TrakBox trakBox = tracks[i];
            trakBox.setDuration(this.rescale(trakBox.getDuration(), oldTs));
            List<Edit> edits = trakBox.getEdits();
            if (edits == null) continue;
            ListIterator<Edit> lit = edits.listIterator();
            while (lit.hasNext()) {
                Edit edit = lit.next();
                lit.set(new Edit(this.rescale(edit.getDuration(), oldTs), edit.getMediaTime(), edit.getRate()));
            }
        }
        this.setDuration(this.rescale(this.getDuration(), oldTs));
    }

    private void setTimescale(int newTs) {
        NodeBox.findFirst(this, MovieHeaderBox.class, "mvhd").setTimescale(newTs);
    }

    public void setDuration(long movDuration) {
        this.getMovieHeader().setDuration(movDuration);
    }

    private MovieHeaderBox getMovieHeader() {
        return NodeBox.findFirst(this, MovieHeaderBox.class, "mvhd");
    }

    public List<TrakBox> getAudioTracks() {
        ArrayList<TrakBox> result = new ArrayList<TrakBox>();
        TrakBox[] tracks = this.getTracks();
        for (int i = 0; i < tracks.length; ++i) {
            TrakBox trakBox = tracks[i];
            if (!trakBox.isAudio()) continue;
            result.add(trakBox);
        }
        return result;
    }

    public long getDuration() {
        return this.getMovieHeader().getDuration();
    }

    public TrakBox importTrack(MovieBox movie, TrakBox track) {
        TrakBox newTrack = (TrakBox)NodeBox.cloneBox(track, 0x100000, this.factory);
        List<Edit> edits = newTrack.getEdits();
        ArrayList<Edit> result = new ArrayList<Edit>();
        if (edits != null) {
            for (Edit edit : edits) {
                result.add(new Edit(this.rescale(edit.getDuration(), movie.getTimescale()), edit.getMediaTime(), edit.getRate()));
            }
        }
        newTrack.setEdits(result);
        return newTrack;
    }

    public void appendTrack(TrakBox newTrack) {
        newTrack.getTrackHeader().setNo(this.getMovieHeader().getNextTrackId());
        this.getMovieHeader().setNextTrackId(this.getMovieHeader().getNextTrackId() + 1);
        this.boxes.add(newTrack);
    }

    public boolean isPureRefMovie() {
        boolean pureRef = true;
        TrakBox[] tracks = this.getTracks();
        for (int i = 0; i < tracks.length; ++i) {
            TrakBox trakBox = tracks[i];
            pureRef &= trakBox.isPureRef();
        }
        return pureRef;
    }

    public void updateDuration() {
        TrakBox[] tracks = this.getTracks();
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < tracks.length; ++i) {
            TrakBox trakBox = tracks[i];
            if (trakBox.getDuration() >= min) continue;
            min = trakBox.getDuration();
        }
        this.getMovieHeader().setDuration(min);
    }

    public Size getDisplaySize() {
        TrakBox videoTrack = this.getVideoTrack();
        if (videoTrack == null) {
            return null;
        }
        ClearApertureBox clef = NodeBox.findFirstPath(videoTrack, ClearApertureBox.class, Box.path("tapt.clef"));
        if (clef != null) {
            return this.applyMatrix(videoTrack, new Size((int)clef.getWidth(), (int)clef.getHeight()));
        }
        Box box = NodeBox.findFirstPath(videoTrack, SampleDescriptionBox.class, Box.path("mdia.minf.stbl.stsd")).getBoxes().get(0);
        if (box == null || !(box instanceof VideoSampleEntry)) {
            return null;
        }
        VideoSampleEntry vs = (VideoSampleEntry)box;
        Rational par = videoTrack.getPAR();
        return this.applyMatrix(videoTrack, new Size(vs.getWidth() * par.getNum() / par.getDen(), vs.getHeight()));
    }

    private Size applyMatrix(TrakBox videoTrack, Size size) {
        int[] matrix = videoTrack.getTrackHeader().getMatrix();
        return new Size((int)((double)size.getWidth() * (double)matrix[0] / 65536.0), (int)((double)size.getHeight() * (double)matrix[4] / 65536.0));
    }

    public Size getStoredSize() {
        TrakBox videoTrack = this.getVideoTrack();
        if (videoTrack == null) {
            return null;
        }
        EncodedPixelBox enof = NodeBox.findFirstPath(videoTrack, EncodedPixelBox.class, Box.path("tapt.enof"));
        if (enof != null) {
            return new Size((int)enof.getWidth(), (int)enof.getHeight());
        }
        Box box = NodeBox.findFirstPath(videoTrack, SampleDescriptionBox.class, Box.path("mdia.minf.stbl.stsd")).getBoxes().get(0);
        if (box == null || !(box instanceof VideoSampleEntry)) {
            return null;
        }
        VideoSampleEntry vs = (VideoSampleEntry)box;
        return new Size(vs.getWidth(), vs.getHeight());
    }
}
