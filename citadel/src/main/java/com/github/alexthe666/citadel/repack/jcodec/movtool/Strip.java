/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.Chunk;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.ChunkReader;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.MP4Util;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.ChunkOffsets64Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.ChunkOffsetsBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Edit;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MediaHeaderBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.SampleSizesBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.SampleToChunkBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TimeToSampleBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TrakBox;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.File;
import java.io.IOException;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Strip {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void main1(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Syntax: strip <ref movie> <out movie>");
            System.exit(-1);
        }
        Channel input = null;
        Channel out = null;
        try {
            input = NIOUtils.readableChannel(new File(args[0]));
            File file = new File(args[1]);
            Platform.deleteFile(file);
            out = NIOUtils.writableChannel(file);
            MP4Util.Movie movie = MP4Util.createRefFullMovie((SeekableByteChannel)input, "file://" + new File(args[0]).getAbsolutePath());
            new Strip().strip(movie.getMoov());
            MP4Util.writeFullMovie((SeekableByteChannel)out, movie);
        }
        finally {
            if (input != null) {
                input.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    public void strip(MovieBox movie) throws IOException {
        TrakBox[] tracks = movie.getTracks();
        for (int i = 0; i < tracks.length; ++i) {
            TrakBox track = tracks[i];
            this.stripTrack(movie, track);
        }
    }

    public void stripTrack(MovieBox movie, TrakBox track) {
        Chunk chunk;
        ChunkReader chunks = new ChunkReader(track);
        List<Edit> edits = track.getEdits();
        List<Edit> oldEdits = this.deepCopy(edits);
        ArrayList<Chunk> result = new ArrayList<Chunk>();
        while ((chunk = chunks.next()) != null) {
            boolean intersects = false;
            for (Edit edit : oldEdits) {
                long chunkE;
                long chunkS;
                long editE;
                long editS;
                if (edit.getMediaTime() != -1L && (intersects = this.intersects(editS = edit.getMediaTime(), editE = edit.getMediaTime() + track.rescale(edit.getDuration(), movie.getTimescale()), chunkS = chunk.getStartTv(), chunkE = chunk.getStartTv() + (long)chunk.getDuration()))) break;
            }
            if (!intersects) {
                for (int i = 0; i < oldEdits.size(); ++i) {
                    if (oldEdits.get(i).getMediaTime() < chunk.getStartTv() + (long)chunk.getDuration()) continue;
                    edits.get(i).shift(-chunk.getDuration());
                }
                continue;
            }
            result.add(chunk);
        }
        NodeBox stbl = NodeBox.findFirstPath(track, NodeBox.class, Box.path("mdia.minf.stbl"));
        stbl.replace("stts", this.getTimeToSamples(result));
        stbl.replace("stsz", this.getSampleSizes(result));
        stbl.replace("stsc", this.getSamplesToChunk(result));
        stbl.removeChildren(new String[]{"stco", "co64"});
        stbl.add(this.getChunkOffsets(result));
        NodeBox.findFirstPath(track, MediaHeaderBox.class, Box.path("mdia.mdhd")).setDuration(this.totalDuration(result));
    }

    private long totalDuration(List<Chunk> result) {
        long duration = 0L;
        for (Chunk chunk : result) {
            duration += (long)chunk.getDuration();
        }
        return duration;
    }

    private List<Edit> deepCopy(List<Edit> edits) {
        ArrayList<Edit> newList = new ArrayList<Edit>();
        for (Edit edit : edits) {
            newList.add(Edit.createEdit(edit));
        }
        return newList;
    }

    public Box getChunkOffsets(List<Chunk> chunks) {
        long[] result = new long[chunks.size()];
        boolean longBox = false;
        int i = 0;
        for (Chunk chunk : chunks) {
            if (chunk.getOffset() >= 0x100000000L) {
                longBox = true;
            }
            result[i++] = chunk.getOffset();
        }
        return longBox ? ChunkOffsets64Box.createChunkOffsets64Box(result) : ChunkOffsetsBox.createChunkOffsetsBox(result);
    }

    public TimeToSampleBox getTimeToSamples(List<Chunk> chunks) {
        ArrayList<TimeToSampleBox.TimeToSampleEntry> tts = new ArrayList<TimeToSampleBox.TimeToSampleEntry>();
        int curTts = -1;
        int cnt = 0;
        for (Chunk chunk : chunks) {
            if (chunk.getSampleDur() > 0) {
                if (curTts == -1 || curTts != chunk.getSampleDur()) {
                    if (curTts != -1) {
                        tts.add(new TimeToSampleBox.TimeToSampleEntry(cnt, curTts));
                    }
                    cnt = 0;
                    curTts = chunk.getSampleDur();
                }
                cnt += chunk.getSampleCount();
                continue;
            }
            for (int dur : chunk.getSampleDurs()) {
                if (curTts == -1 || curTts != dur) {
                    if (curTts != -1) {
                        tts.add(new TimeToSampleBox.TimeToSampleEntry(cnt, curTts));
                    }
                    cnt = 0;
                    curTts = dur;
                }
                ++cnt;
            }
        }
        if (cnt > 0) {
            tts.add(new TimeToSampleBox.TimeToSampleEntry(cnt, curTts));
        }
        return TimeToSampleBox.createTimeToSampleBox(tts.toArray(new TimeToSampleBox.TimeToSampleEntry[0]));
    }

    public SampleSizesBox getSampleSizes(List<Chunk> chunks) {
        int nSamples = 0;
        int prevSize = chunks.get(0).getSampleSize();
        for (Chunk chunk : chunks) {
            nSamples += chunk.getSampleCount();
            if (prevSize != 0 || chunk.getSampleSize() == 0) continue;
            throw new RuntimeException("Mixed sample sizes not supported");
        }
        if (prevSize > 0) {
            return SampleSizesBox.createSampleSizesBox(prevSize, nSamples);
        }
        int[] sizes = new int[nSamples];
        int startSample = 0;
        for (Chunk chunk : chunks) {
            System.arraycopy(chunk.getSampleSizes(), 0, sizes, startSample, chunk.getSampleCount());
            startSample += chunk.getSampleCount();
        }
        return SampleSizesBox.createSampleSizesBox2(sizes);
    }

    public SampleToChunkBox getSamplesToChunk(List<Chunk> chunks) {
        ArrayList<SampleToChunkBox.SampleToChunkEntry> result = new ArrayList<SampleToChunkBox.SampleToChunkEntry>();
        Iterator<Chunk> it = chunks.iterator();
        Chunk chunk = it.next();
        int curSz = chunk.getSampleCount();
        int curEntry = chunk.getEntry();
        int first = 1;
        int cnt = 1;
        while (it.hasNext()) {
            chunk = it.next();
            int newSz = chunk.getSampleCount();
            int newEntry = chunk.getEntry();
            if (curSz != newSz || curEntry != newEntry) {
                result.add(new SampleToChunkBox.SampleToChunkEntry(first, curSz, curEntry));
                curSz = newSz;
                curEntry = newEntry;
                first += cnt;
                cnt = 0;
            }
            ++cnt;
        }
        if (cnt > 0) {
            result.add(new SampleToChunkBox.SampleToChunkEntry(first, curSz, curEntry));
        }
        return SampleToChunkBox.createSampleToChunkBox(result.toArray(new SampleToChunkBox.SampleToChunkEntry[0]));
    }

    private boolean intersects(long a, long b, long c, long d) {
        return a >= c && a < d || b >= c && b < d || c >= a && c < b || d >= a && d < b;
    }
}
