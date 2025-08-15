/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.api.transcode;

import com.github.alexthe666.citadel.repack.jcodec.api.transcode.AudioFrameWithPacket;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Filter;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.PacketSink;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.PixelStore;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Sink;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.VideoFrameWithPacket;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.filters.ColorTransformFilter;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

private static class Transcoder.Stream {
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

    public Transcoder.Stream(Sink sink, boolean videoCopy, boolean audioCopy, List<Filter> extraFilters, PixelStore pixelStore) {
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
