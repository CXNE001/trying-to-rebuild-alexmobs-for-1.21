/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.avi;

import com.github.alexthe666.citadel.repack.jcodec.common.io.DataReader;
import com.github.alexthe666.citadel.repack.jcodec.containers.avi.AVIReader;
import java.io.IOException;

static class AVIReader.AVITag_WaveFormatEx
extends AVIReader.AVIChunk {
    public static final int SPEAKER_FRONT_LEFT = 1;
    public static final int SPEAKER_FRONT_RIGHT = 2;
    public static final int SPEAKER_FRONT_CENTER = 4;
    public static final int SPEAKER_LOW_FREQUENCY = 8;
    public static final int SPEAKER_BACK_LEFT = 16;
    public static final int SPEAKER_BACK_RIGHT = 32;
    public static final int SPEAKER_FRONT_LEFT_OF_CENTER = 64;
    public static final int SPEAKER_FRONT_RIGHT_OF_CENTER = 128;
    public static final int SPEAKER_BACK_CENTER = 256;
    public static final int SPEAKER_SIDE_LEFT = 512;
    public static final int SPEAKER_SIDE_RIGHT = 1024;
    public static final int SPEAKER_TOP_CENTER = 2048;
    public static final int SPEAKER_TOP_FRONT_LEFT = 4096;
    public static final int SPEAKER_TOP_FRONT_CENTER = 8192;
    public static final int SPEAKER_TOP_FRONT_RIGHT = 16384;
    public static final int SPEAKER_TOP_BACK_LEFT = 32768;
    public static final int SPEAKER_TOP_BACK_CENTER = 65536;
    public static final int SPEAKER_TOP_BACK_RIGHT = 131072;
    protected short wFormatTag;
    protected short channels;
    protected int nSamplesPerSec;
    protected int nAvgBytesPerSec;
    protected short nBlockAlign;
    protected short wBitsPerSample;
    protected short cbSize;
    protected short wValidBitsPerSample;
    protected short samplesValidBitsPerSample;
    protected short wReserved;
    protected int channelMask;
    protected int guid_data1;
    protected short guid_data2;
    protected short guid_data3;
    protected byte[] guid_data4 = new byte[8];
    protected boolean mp3Flag = false;
    protected short wID;
    protected int fdwFlags;
    protected short nBlockSize;
    protected short nFramesPerBlock;
    protected short nCodecDelay;
    private String audioFormat = "?";

    @Override
    public void read(int dwFourCC, DataReader raf) throws IOException {
        super.read(dwFourCC, raf);
        this.wFormatTag = raf.readShort();
        this.channels = raf.readShort();
        this.nSamplesPerSec = raf.readInt();
        this.nAvgBytesPerSec = raf.readInt();
        this.nBlockAlign = raf.readShort();
        switch (this.wFormatTag) {
            case 1: {
                this.wBitsPerSample = raf.readShort();
                if (this.dwChunkSize == 40) {
                    this.samplesValidBitsPerSample = this.wReserved = raf.readShort();
                    this.wValidBitsPerSample = this.wReserved;
                    this.cbSize = raf.readShort();
                    this.channelMask = raf.readInt();
                    this.guid_data1 = raf.readInt();
                    this.guid_data2 = raf.readShort();
                    this.guid_data3 = raf.readShort();
                    raf.readFully(this.guid_data4);
                }
                this.audioFormat = "PCM";
                break;
            }
            case 85: {
                this.wBitsPerSample = raf.readShort();
                this.cbSize = raf.readShort();
                this.wID = raf.readShort();
                this.fdwFlags = raf.readInt();
                this.nBlockSize = raf.readShort();
                this.nFramesPerBlock = raf.readShort();
                this.nCodecDelay = raf.readShort();
                this.mp3Flag = true;
                this.audioFormat = "MP3";
                break;
            }
            case 8192: {
                this.audioFormat = "AC3";
                break;
            }
            case 8193: {
                this.audioFormat = "DTS";
                break;
            }
            case 22127: {
                this.audioFormat = "VORBIS";
                break;
            }
            case 65534: {
                this.wBitsPerSample = raf.readShort();
                this.cbSize = raf.readShort();
                this.samplesValidBitsPerSample = this.wReserved = raf.readShort();
                this.wValidBitsPerSample = this.wReserved;
                this.channelMask = raf.readInt();
                this.guid_data1 = raf.readInt();
                this.guid_data2 = raf.readShort();
                this.guid_data3 = raf.readShort();
                raf.readFully(this.guid_data4);
                this.audioFormat = "EXTENSIBLE";
                break;
            }
            default: {
                this.audioFormat = "Unknown : " + Integer.toHexString(this.wFormatTag);
            }
        }
    }

    public boolean isMP3() {
        return this.mp3Flag;
    }

    public short getCbSize() {
        return this.cbSize;
    }

    @Override
    public String toString() {
        return String.format("\tCHUNK [%s], ChunkSize [%d], Format [%s], Channels [%d], Channel Mask [%s], MP3 [%b], SamplesPerSec [%d], nBlockAlign [%d]", AVIReader.toFourCC(this.dwFourCC), this.getChunkSize(), this.audioFormat, this.channels, Integer.toHexString(this.channelMask), this.mp3Flag, this.nSamplesPerSec, this.getStartOfChunk(), this.nBlockAlign);
    }
}
