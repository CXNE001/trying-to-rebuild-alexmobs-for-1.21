/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.api.NotSupportedException;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class AudioUtil {
    private static final float f24 = 8388607.0f;
    private static final float f16 = 32767.0f;
    public static final float r16 = 3.0517578E-5f;
    public static final float r24 = 1.1920929E-7f;

    public static void toFloat(AudioFormat format, ByteBuffer buf, FloatBuffer floatBuf) {
        if (!format.isSigned()) {
            throw new NotSupportedException("Unsigned PCM is not supported ( yet? ).");
        }
        if (format.getSampleSizeInBits() != 16 && format.getSampleSizeInBits() != 24) {
            throw new NotSupportedException(format.getSampleSizeInBits() + " bit PCM is not supported ( yet? ).");
        }
        if (format.isBigEndian()) {
            if (format.getSampleSizeInBits() == 16) {
                AudioUtil.toFloat16BE(buf, floatBuf);
            } else {
                AudioUtil.toFloat24BE(buf, floatBuf);
            }
        } else if (format.getSampleSizeInBits() == 16) {
            AudioUtil.toFloat16LE(buf, floatBuf);
        } else {
            AudioUtil.toFloat24LE(buf, floatBuf);
        }
    }

    public static void fromFloat(FloatBuffer floatBuf, AudioFormat format, ByteBuffer buf) {
        if (!format.isSigned()) {
            throw new NotSupportedException("Unsigned PCM is not supported ( yet? ).");
        }
        if (format.getSampleSizeInBits() != 16 && format.getSampleSizeInBits() != 24) {
            throw new NotSupportedException(format.getSampleSizeInBits() + " bit PCM is not supported ( yet? ).");
        }
        if (format.isBigEndian()) {
            if (format.getSampleSizeInBits() == 16) {
                AudioUtil.fromFloat16BE(buf, floatBuf);
            } else {
                AudioUtil.fromFloat24BE(buf, floatBuf);
            }
        } else if (format.getSampleSizeInBits() == 16) {
            AudioUtil.fromFloat16LE(buf, floatBuf);
        } else {
            AudioUtil.fromFloat24LE(buf, floatBuf);
        }
    }

    private static void toFloat24LE(ByteBuffer buf, FloatBuffer out) {
        while (buf.remaining() >= 3 && out.hasRemaining()) {
            out.put(1.1920929E-7f * (float)(((buf.get() & 0xFF) << 8 | (buf.get() & 0xFF) << 16 | (buf.get() & 0xFF) << 24) >> 8));
        }
    }

    private static void toFloat16LE(ByteBuffer buf, FloatBuffer out) {
        while (buf.remaining() >= 2 && out.hasRemaining()) {
            out.put(3.0517578E-5f * (float)((short)(buf.get() & 0xFF | (buf.get() & 0xFF) << 8)));
        }
    }

    private static void toFloat24BE(ByteBuffer buf, FloatBuffer out) {
        while (buf.remaining() >= 3 && out.hasRemaining()) {
            out.put(1.1920929E-7f * (float)(((buf.get() & 0xFF) << 24 | (buf.get() & 0xFF) << 16 | (buf.get() & 0xFF) << 8) >> 8));
        }
    }

    private static void toFloat16BE(ByteBuffer buf, FloatBuffer out) {
        while (buf.remaining() >= 2 && out.hasRemaining()) {
            out.put(3.0517578E-5f * (float)((short)((buf.get() & 0xFF) << 8 | buf.get() & 0xFF)));
        }
    }

    private static void fromFloat24LE(ByteBuffer buf, FloatBuffer _in) {
        while (buf.remaining() >= 3 && _in.hasRemaining()) {
            int val = MathUtil.clip((int)(_in.get() * 8388607.0f), -8388608, 0x7FFFFF) & 0xFFFFFF;
            buf.put((byte)val);
            buf.put((byte)(val >> 8));
            buf.put((byte)(val >> 16));
        }
    }

    private static void fromFloat16LE(ByteBuffer buf, FloatBuffer _in) {
        while (buf.remaining() >= 2 && _in.hasRemaining()) {
            int val = MathUtil.clip((int)(_in.get() * 32767.0f), Short.MIN_VALUE, Short.MAX_VALUE) & 0xFFFF;
            buf.put((byte)val);
            buf.put((byte)(val >> 8));
        }
    }

    private static void fromFloat24BE(ByteBuffer buf, FloatBuffer _in) {
        while (buf.remaining() >= 3 && _in.hasRemaining()) {
            int val = MathUtil.clip((int)(_in.get() * 8388607.0f), -8388608, 0x7FFFFF) & 0xFFFFFF;
            buf.put((byte)(val >> 16));
            buf.put((byte)(val >> 8));
            buf.put((byte)val);
        }
    }

    private static void fromFloat16BE(ByteBuffer buf, FloatBuffer _in) {
        while (buf.remaining() >= 2 && _in.hasRemaining()) {
            int val = MathUtil.clip((int)(_in.get() * 32767.0f), Short.MIN_VALUE, Short.MAX_VALUE) & 0xFFFF;
            buf.put((byte)(val >> 8));
            buf.put((byte)val);
        }
    }

    public static int fromInt(int[] data, int len, AudioFormat format, ByteBuffer buf) {
        if (!format.isSigned()) {
            throw new NotSupportedException("Unsigned PCM is not supported ( yet? ).");
        }
        if (format.getSampleSizeInBits() != 16 && format.getSampleSizeInBits() != 24) {
            throw new NotSupportedException(format.getSampleSizeInBits() + " bit PCM is not supported ( yet? ).");
        }
        if (format.isBigEndian()) {
            if (format.getSampleSizeInBits() == 16) {
                return AudioUtil.fromInt16BE(buf, data, len);
            }
            return AudioUtil.fromInt24BE(buf, data, len);
        }
        if (format.getSampleSizeInBits() == 16) {
            return AudioUtil.fromInt16LE(buf, data, len);
        }
        return AudioUtil.fromInt24LE(buf, data, len);
    }

    private static int fromInt24LE(ByteBuffer buf, int[] out, int len) {
        int samples = 0;
        while (buf.remaining() >= 3 && samples < len) {
            int val = out[samples++];
            buf.put((byte)val);
            buf.put((byte)(val >> 8));
            buf.put((byte)(val >> 16));
        }
        return samples;
    }

    private static int fromInt16LE(ByteBuffer buf, int[] out, int len) {
        int samples = 0;
        while (buf.remaining() >= 2 && samples < len) {
            int val = out[samples++];
            buf.put((byte)val);
            buf.put((byte)(val >> 8));
        }
        return samples;
    }

    private static int fromInt24BE(ByteBuffer buf, int[] out, int len) {
        int samples = 0;
        while (buf.remaining() >= 3 && samples < len) {
            int val = out[samples++];
            buf.put((byte)(val >> 16));
            buf.put((byte)(val >> 8));
            buf.put((byte)val);
        }
        return samples;
    }

    private static int fromInt16BE(ByteBuffer buf, int[] out, int len) {
        int samples = 0;
        while (buf.remaining() >= 2 && samples < len) {
            int val = out[samples++];
            buf.put((byte)(val >> 8));
            buf.put((byte)val);
        }
        return samples;
    }

    public static int toInt(AudioFormat format, ByteBuffer buf, int[] samples) {
        if (!format.isSigned()) {
            throw new NotSupportedException("Unsigned PCM is not supported ( yet? ).");
        }
        if (format.getSampleSizeInBits() != 16 && format.getSampleSizeInBits() != 24) {
            throw new NotSupportedException(format.getSampleSizeInBits() + " bit PCM is not supported ( yet? ).");
        }
        if (format.isBigEndian()) {
            if (format.getSampleSizeInBits() == 16) {
                return AudioUtil.toInt16BE(buf, samples);
            }
            return AudioUtil.toInt24BE(buf, samples);
        }
        if (format.getSampleSizeInBits() == 16) {
            return AudioUtil.toInt16LE(buf, samples);
        }
        return AudioUtil.toInt24LE(buf, samples);
    }

    private static int toInt24LE(ByteBuffer buf, int[] out) {
        int samples = 0;
        while (buf.remaining() >= 3 && samples < out.length) {
            out[samples++] = ((buf.get() & 0xFF) << 8 | (buf.get() & 0xFF) << 16 | (buf.get() & 0xFF) << 24) >> 8;
        }
        return samples;
    }

    private static int toInt16LE(ByteBuffer buf, int[] out) {
        int samples = 0;
        while (buf.remaining() >= 2 && samples < out.length) {
            out[samples++] = (short)(buf.get() & 0xFF | (buf.get() & 0xFF) << 8);
        }
        return samples;
    }

    private static int toInt24BE(ByteBuffer buf, int[] out) {
        int samples = 0;
        while (buf.remaining() >= 3 && samples < out.length) {
            out[samples++] = ((buf.get() & 0xFF) << 24 | (buf.get() & 0xFF) << 16 | (buf.get() & 0xFF) << 8) >> 8;
        }
        return samples;
    }

    private static int toInt16BE(ByteBuffer buf, int[] out) {
        int samples = 0;
        while (buf.remaining() >= 2 && samples < out.length) {
            out[samples++] = (short)((buf.get() & 0xFF) << 8 | buf.get() & 0xFF);
        }
        return samples;
    }

    public static void interleave(AudioFormat format, ByteBuffer[] ins, ByteBuffer outb) {
        int bytesPerSample = format.getSampleSizeInBits() >> 3;
        int bytesPerFrame = bytesPerSample * ins.length;
        int max = 0;
        for (int i = 0; i < ins.length; ++i) {
            if (ins[i].remaining() <= max) continue;
            max = ins[i].remaining();
        }
        for (int frames = 0; frames < max && outb.remaining() >= bytesPerFrame; ++frames) {
            for (int j = 0; j < ins.length; ++j) {
                int i;
                if (ins[j].remaining() < bytesPerSample) {
                    for (i = 0; i < bytesPerSample; ++i) {
                        outb.put((byte)0);
                    }
                    continue;
                }
                for (i = 0; i < bytesPerSample; ++i) {
                    outb.put(ins[j].get());
                }
            }
        }
    }

    public static void deinterleave(AudioFormat format, ByteBuffer inb, ByteBuffer[] outs) {
        int bytesPerSample = format.getSampleSizeInBits() >> 3;
        int bytesPerFrame = bytesPerSample * outs.length;
        while (inb.remaining() >= bytesPerFrame) {
            for (int j = 0; j < outs.length; ++j) {
                for (int i = 0; i < bytesPerSample; ++i) {
                    outs[j].put(inb.get());
                }
            }
        }
    }
}
