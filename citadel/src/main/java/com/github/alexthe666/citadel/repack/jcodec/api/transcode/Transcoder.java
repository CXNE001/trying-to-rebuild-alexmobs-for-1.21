/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.api.transcode;

import com.github.alexthe666.citadel.repack.jcodec.api.transcode.AudioFrameWithPacket;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Filter;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.PacketSink;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.PacketSource;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.PixelStore;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.PixelStoreImpl;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Sink;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Source;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.VideoFrameWithPacket;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.filters.ColorTransformFilter;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Transcoder {
    static final int REORDER_BUFFER_SIZE = 7;
    private Source[] sources;
    private Sink[] sinks;
    private List<Filter>[] extraFilters;
    private int[] seekFrames;
    private int[] maxFrames;
    private Mapping[] videoMappings;
    private Mapping[] audioMappings;

    private Transcoder(Source[] source, Sink[] sink, Mapping[] videoMappings, Mapping[] audioMappings, List<Filter>[] extraFilters, int[] seekFrames, int[] maxFrames) {
        this.extraFilters = extraFilters;
        this.videoMappings = videoMappings;
        this.audioMappings = audioMappings;
        this.seekFrames = seekFrames;
        this.maxFrames = maxFrames;
        this.sources = source;
        this.sinks = sink;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void transcode() throws IOException {
        int i;
        int s;
        PixelStoreImpl pixelStore = new PixelStoreImpl();
        List[] videoStreams = new List[this.sources.length];
        List[] audioStreams = new List[this.sources.length];
        boolean[] decodeVideo = new boolean[this.sources.length];
        boolean[] decodeAudio = new boolean[this.sources.length];
        boolean[] finishedVideo = new boolean[this.sources.length];
        boolean[] finishedAudio = new boolean[this.sources.length];
        Stream[] allStreams = new Stream[this.sinks.length];
        int[] videoFramesRead = new int[this.sources.length];
        for (s = 0; s < this.sources.length; ++s) {
            videoStreams[s] = new ArrayList();
            audioStreams[s] = new ArrayList();
        }
        for (i = 0; i < this.sinks.length; ++i) {
            this.sinks[i].init();
        }
        for (i = 0; i < this.sources.length; ++i) {
            this.sources[i].init(pixelStore);
            this.sources[i].seekFrames(this.seekFrames[i]);
        }
        for (s = 0; s < this.sinks.length; ++s) {
            Stream stream;
            allStreams[s] = stream = new Stream(this.sinks[s], this.videoMappings[s].copy, this.audioMappings[s].copy, this.extraFilters[s], pixelStore);
            if (this.sources[this.videoMappings[s].source].isVideo()) {
                videoStreams[this.videoMappings[s].source].add(stream);
                if (!this.videoMappings[s].copy) {
                    decodeVideo[((Mapping)this.videoMappings[s]).source] = true;
                }
            } else {
                finishedVideo[((Mapping)this.videoMappings[s]).source] = true;
            }
            if (this.sources[this.audioMappings[s].source].isAudio()) {
                audioStreams[this.audioMappings[s].source].add(stream);
                if (this.audioMappings[s].copy) continue;
                decodeAudio[((Mapping)this.audioMappings[s]).source] = true;
                continue;
            }
            finishedAudio[((Mapping)this.audioMappings[s]).source] = true;
        }
        try {
            boolean allFinished;
            do {
                for (s = 0; s < this.sources.length; ++s) {
                    Object packet;
                    Source source = this.sources[s];
                    boolean needsVideoFrame = !finishedVideo[s];
                    for (Stream stream : videoStreams[s]) {
                        needsVideoFrame &= stream.needsVideoFrame() || stream.hasLeadingAudio() || finishedAudio[s];
                    }
                    if (needsVideoFrame) {
                        VideoFrameWithPacket nextVideoFrame;
                        if (videoFramesRead[s] >= this.maxFrames[s]) {
                            nextVideoFrame = null;
                            finishedVideo[s] = true;
                        } else if (decodeVideo[s] || !(source instanceof PacketSource)) {
                            nextVideoFrame = source.getNextVideoFrame();
                            if (nextVideoFrame == null) {
                                finishedVideo[s] = true;
                            } else {
                                int n = s;
                                videoFramesRead[n] = videoFramesRead[n] + 1;
                                this.printLegend((int)nextVideoFrame.getPacket().getFrameNo(), 0, nextVideoFrame.getPacket());
                            }
                        } else {
                            packet = ((PacketSource)((Object)source)).inputVideoPacket();
                            if (packet == null) {
                                finishedVideo[s] = true;
                            } else {
                                int n = s;
                                videoFramesRead[n] = videoFramesRead[n] + 1;
                            }
                            nextVideoFrame = new VideoFrameWithPacket((Packet)packet, null);
                        }
                        if (finishedVideo[s]) {
                            for (Stream stream : videoStreams[s]) {
                                for (int ss = 0; ss < audioStreams.length; ++ss) {
                                    audioStreams[ss].remove(stream);
                                }
                            }
                            videoStreams[s].clear();
                        }
                        if (nextVideoFrame != null) {
                            for (Stream stream : videoStreams[s]) {
                                stream.addVideoPacket(nextVideoFrame, source.getVideoCodecMeta());
                            }
                            if (nextVideoFrame.getFrame() != null) {
                                pixelStore.putBack(nextVideoFrame.getFrame());
                            }
                        }
                    }
                    if (!audioStreams[s].isEmpty()) {
                        AudioFrameWithPacket nextAudioFrame;
                        if (decodeAudio[s] || !(source instanceof PacketSource)) {
                            nextAudioFrame = source.getNextAudioFrame();
                            if (nextAudioFrame == null) {
                                finishedAudio[s] = true;
                            }
                        } else {
                            packet = ((PacketSource)((Object)source)).inputAudioPacket();
                            if (packet == null) {
                                finishedAudio[s] = true;
                                nextAudioFrame = null;
                            } else {
                                nextAudioFrame = new AudioFrameWithPacket(null, (Packet)packet);
                            }
                        }
                        if (nextAudioFrame == null) continue;
                        for (Stream stream : audioStreams[s]) {
                            stream.addAudioPacket(nextAudioFrame, source.getAudioCodecMeta());
                        }
                        continue;
                    }
                    finishedAudio[s] = true;
                }
                for (s = 0; s < allStreams.length; ++s) {
                    allStreams[s].tryFlushQueues();
                }
                allFinished = true;
                for (int s2 = 0; s2 < this.sources.length; ++s2) {
                    allFinished &= finishedVideo[s2] & finishedAudio[s2];
                }
            } while (!allFinished);
            for (s = 0; s < allStreams.length; ++s) {
                allStreams[s].finalFlushQueues();
            }
        }
        finally {
            for (i = 0; i < this.sources.length; ++i) {
                this.sources[0].finish();
            }
            for (i = 0; i < this.sinks.length; ++i) {
                this.sinks[i].finish();
            }
        }
    }

    private void printLegend(int frameNo, int maxFrames, Packet inVideoPacket) {
        if (frameNo % 100 == 0) {
            System.out.print(String.format("[%6d]\r", frameNo));
        }
    }

    public static TranscoderBuilder newTranscoder() {
        return new TranscoderBuilder();
    }

    public static class TranscoderBuilder {
        private List<Source> source = new ArrayList<Source>();
        private List<Sink> sink = new ArrayList<Sink>();
        private List<List<Filter>> filters = new ArrayList<List<Filter>>();
        private IntArrayList seekFrames = new IntArrayList(20);
        private IntArrayList maxFrames = new IntArrayList(20);
        private List<Mapping> videoMappings = new ArrayList<Mapping>();
        private List<Mapping> audioMappings = new ArrayList<Mapping>();

        public TranscoderBuilder addFilter(int sink, Filter filter) {
            this.filters.get(sink).add(filter);
            return this;
        }

        public TranscoderBuilder setSeekFrames(int source, int seekFrames) {
            this.seekFrames.set(source, seekFrames);
            return this;
        }

        public TranscoderBuilder setMaxFrames(int source, int maxFrames) {
            this.maxFrames.set(source, maxFrames);
            return this;
        }

        public TranscoderBuilder addSource(Source source) {
            this.source.add(source);
            this.seekFrames.add(0);
            this.maxFrames.add(Integer.MAX_VALUE);
            return this;
        }

        public TranscoderBuilder addSink(Sink sink) {
            this.sink.add(sink);
            this.videoMappings.add(new Mapping(0, false));
            this.audioMappings.add(new Mapping(0, false));
            this.filters.add(new ArrayList());
            return this;
        }

        public TranscoderBuilder setVideoMapping(int src, int sink, boolean copy) {
            this.videoMappings.set(sink, new Mapping(src, copy));
            return this;
        }

        public TranscoderBuilder setAudioMapping(int src, int sink, boolean copy) {
            this.audioMappings.set(sink, new Mapping(src, copy));
            return this;
        }

        public Transcoder create() {
            return new Transcoder(this.source.toArray(new Source[0]), this.sink.toArray(new Sink[0]), this.videoMappings.toArray(new Mapping[0]), this.audioMappings.toArray(new Mapping[0]), this.filters.toArray(new List[0]), this.seekFrames.toArray(), this.maxFrames.toArray());
        }
    }

    private static class Stream {
        private static final double AUDIO_LEADING_TIME = 0.2;
        private LinkedList<VideoFrameWithPacket> videoQueue;
        private LinkedList<AudioFrameWithPacket> audioQueue;
        private List<Filter> filters;
        private List<Filter> extraFilters;
        private Sink sink;
        private boolean videoCopy;
        private boolean audioCopy;
        private PixelStore pixelStore;
        private VideoCodecMeta videoCodecMeta;
        private AudioCodecMeta audioCodecMeta;
        private static final int REORDER_LENGTH = 5;

        public Stream(Sink sink, boolean videoCopy, boolean audioCopy, List<Filter> extraFilters, PixelStore pixelStore) {
            this.sink = sink;
            this.videoCopy = videoCopy;
            this.audioCopy = audioCopy;
            this.extraFilters = extraFilters;
            this.pixelStore = pixelStore;
            this.videoQueue = new LinkedList();
            this.audioQueue = new LinkedList();
        }

        private List<Filter> initColorTransform(ColorSpace sourceColor, List<Filter> extraFilters, Sink sink) {
            ArrayList<Filter> filters = new ArrayList<Filter>();
            for (Filter filter : extraFilters) {
                ColorSpace inputColor = filter.getInputColor();
                if (!sourceColor.matches(inputColor)) {
                    filters.add(new ColorTransformFilter(inputColor));
                }
                filters.add(filter);
                if (filter.getOutputColor() == ColorSpace.SAME) continue;
                sourceColor = filter.getOutputColor();
            }
            ColorSpace inputColor = sink.getInputColor();
            if (inputColor != null && inputColor != sourceColor) {
                filters.add(new ColorTransformFilter(inputColor));
            }
            return filters;
        }

        public void tryFlushQueues() throws IOException {
            AudioFrameWithPacket audioFrame;
            if (this.videoQueue.size() <= 0) {
                return;
            }
            if (this.videoCopy && this.videoQueue.size() < 5) {
                return;
            }
            if (!this.hasLeadingAudio()) {
                return;
            }
            VideoFrameWithPacket firstVideoFrame = this.videoQueue.get(0);
            if (this.videoCopy) {
                for (VideoFrameWithPacket videoFrame : this.videoQueue) {
                    if (videoFrame.getPacket().getFrameNo() >= firstVideoFrame.getPacket().getFrameNo()) continue;
                    firstVideoFrame = videoFrame;
                }
            }
            int aqSize = this.audioQueue.size();
            for (int af = 0; af < aqSize && !((audioFrame = this.audioQueue.get(0)).getPacket().getPtsD() >= firstVideoFrame.getPacket().getPtsD() + 0.2); ++af) {
                this.audioQueue.remove(0);
                if (this.audioCopy && this.sink instanceof PacketSink) {
                    ((PacketSink)((Object)this.sink)).outputAudioPacket(audioFrame.getPacket(), this.audioCodecMeta);
                    continue;
                }
                this.sink.outputAudioFrame(audioFrame);
            }
            this.videoQueue.remove(firstVideoFrame);
            if (this.videoCopy && this.sink instanceof PacketSink) {
                ((PacketSink)((Object)this.sink)).outputVideoPacket(firstVideoFrame.getPacket(), this.videoCodecMeta);
            } else {
                PixelStore.LoanerPicture frame = this.filterFrame(firstVideoFrame);
                this.sink.outputVideoFrame(new VideoFrameWithPacket(firstVideoFrame.getPacket(), frame));
                this.pixelStore.putBack(frame);
            }
        }

        private PixelStore.LoanerPicture filterFrame(VideoFrameWithPacket firstVideoFrame) {
            PixelStore.LoanerPicture frame = firstVideoFrame.getFrame();
            for (Filter filter : this.filters) {
                PixelStore.LoanerPicture old = frame;
                if ((frame = filter.filter(frame.getPicture(), this.pixelStore)) == null) {
                    frame = old;
                    continue;
                }
                this.pixelStore.putBack(old);
            }
            return frame;
        }

        public void finalFlushQueues() throws IOException {
            VideoFrameWithPacket lastVideoFrame = null;
            for (VideoFrameWithPacket videoFrame : this.videoQueue) {
                if (lastVideoFrame != null && !(videoFrame.getPacket().getPtsD() >= lastVideoFrame.getPacket().getPtsD())) continue;
                lastVideoFrame = videoFrame;
            }
            if (lastVideoFrame != null) {
                AudioFrameWithPacket audioFrame;
                Iterator iterator = this.audioQueue.iterator();
                while (iterator.hasNext() && !((audioFrame = (AudioFrameWithPacket)iterator.next()).getPacket().getPtsD() > lastVideoFrame.getPacket().getPtsD())) {
                    if (this.audioCopy && this.sink instanceof PacketSink) {
                        ((PacketSink)((Object)this.sink)).outputAudioPacket(audioFrame.getPacket(), this.audioCodecMeta);
                        continue;
                    }
                    this.sink.outputAudioFrame(audioFrame);
                }
                for (VideoFrameWithPacket videoFrame : this.videoQueue) {
                    if (videoFrame == null) continue;
                    if (this.videoCopy && this.sink instanceof PacketSink) {
                        ((PacketSink)((Object)this.sink)).outputVideoPacket(videoFrame.getPacket(), this.videoCodecMeta);
                        continue;
                    }
                    PixelStore.LoanerPicture frame = this.filterFrame(videoFrame);
                    this.sink.outputVideoFrame(new VideoFrameWithPacket(videoFrame.getPacket(), frame));
                    this.pixelStore.putBack(frame);
                }
            } else {
                for (AudioFrameWithPacket audioFrame : this.audioQueue) {
                    if (this.audioCopy && this.sink instanceof PacketSink) {
                        ((PacketSink)((Object)this.sink)).outputAudioPacket(audioFrame.getPacket(), this.audioCodecMeta);
                        continue;
                    }
                    this.sink.outputAudioFrame(audioFrame);
                }
            }
        }

        public void addVideoPacket(VideoFrameWithPacket videoFrame, VideoCodecMeta meta) {
            if (videoFrame.getFrame() != null) {
                this.pixelStore.retake(videoFrame.getFrame());
            }
            this.videoQueue.add(videoFrame);
            this.videoCodecMeta = meta;
            if (this.filters == null) {
                this.filters = this.initColorTransform(this.videoCodecMeta.getColor(), this.extraFilters, this.sink);
            }
        }

        public void addAudioPacket(AudioFrameWithPacket videoFrame, AudioCodecMeta meta) {
            this.audioQueue.add(videoFrame);
            this.audioCodecMeta = meta;
        }

        public boolean needsVideoFrame() {
            if (this.videoQueue.size() <= 0) {
                return true;
            }
            return this.videoCopy && this.videoQueue.size() < 5;
        }

        public boolean hasLeadingAudio() {
            VideoFrameWithPacket firstVideoFrame = this.videoQueue.get(0);
            for (AudioFrameWithPacket audioFrame : this.audioQueue) {
                if (!(audioFrame.getPacket().getPtsD() >= firstVideoFrame.getPacket().getPtsD() + 0.2)) continue;
                return true;
            }
            return false;
        }
    }

    private static class Mapping {
        private int source;
        private boolean copy;

        public Mapping(int source, boolean copy) {
            this.source = source;
            this.copy = copy;
        }
    }
}
