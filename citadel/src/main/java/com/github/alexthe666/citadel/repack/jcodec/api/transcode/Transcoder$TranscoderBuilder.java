/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.api.transcode;

import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Filter;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Sink;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Source;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Transcoder;
import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import java.util.ArrayList;
import java.util.List;

public static class Transcoder.TranscoderBuilder {
    private List<Source> source = new ArrayList<Source>();
    private List<Sink> sink = new ArrayList<Sink>();
    private List<List<Filter>> filters = new ArrayList<List<Filter>>();
    private IntArrayList seekFrames = new IntArrayList(20);
    private IntArrayList maxFrames = new IntArrayList(20);
    private List<Transcoder.Mapping> videoMappings = new ArrayList<Transcoder.Mapping>();
    private List<Transcoder.Mapping> audioMappings = new ArrayList<Transcoder.Mapping>();

    public Transcoder.TranscoderBuilder addFilter(int sink, Filter filter) {
        this.filters.get(sink).add(filter);
        return this;
    }

    public Transcoder.TranscoderBuilder setSeekFrames(int source, int seekFrames) {
        this.seekFrames.set(source, seekFrames);
        return this;
    }

    public Transcoder.TranscoderBuilder setMaxFrames(int source, int maxFrames) {
        this.maxFrames.set(source, maxFrames);
        return this;
    }

    public Transcoder.TranscoderBuilder addSource(Source source) {
        this.source.add(source);
        this.seekFrames.add(0);
        this.maxFrames.add(Integer.MAX_VALUE);
        return this;
    }

    public Transcoder.TranscoderBuilder addSink(Sink sink) {
        this.sink.add(sink);
        this.videoMappings.add(new Transcoder.Mapping(0, false));
        this.audioMappings.add(new Transcoder.Mapping(0, false));
        this.filters.add(new ArrayList());
        return this;
    }

    public Transcoder.TranscoderBuilder setVideoMapping(int src, int sink, boolean copy) {
        this.videoMappings.set(sink, new Transcoder.Mapping(src, copy));
        return this;
    }

    public Transcoder.TranscoderBuilder setAudioMapping(int src, int sink, boolean copy) {
        this.audioMappings.set(sink, new Transcoder.Mapping(src, copy));
        return this;
    }

    public Transcoder create() {
        return new Transcoder(this.source.toArray(new Source[0]), this.sink.toArray(new Sink[0]), this.videoMappings.toArray(new Transcoder.Mapping[0]), this.audioMappings.toArray(new Transcoder.Mapping[0]), this.filters.toArray(new List[0]), this.seekFrames.toArray(), this.maxFrames.toArray(), null);
    }
}
