/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.io;

import com.github.alexthe666.citadel.repack.jcodec.common.ArrayUtil;
import com.github.alexthe666.citadel.repack.jcodec.common.AutoFileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.FileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class NIOUtils {
    public static ByteBuffer search(ByteBuffer buffer, int n, byte[] param) {
        ByteBuffer result = buffer.duplicate();
        int step = 0;
        int rem = buffer.position();
        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            if (b == param[step]) {
                if (++step != param.length) continue;
                if (n == 0) {
                    buffer.position(rem);
                    result.limit(buffer.position());
                    break;
                }
                --n;
                step = 0;
                continue;
            }
            if (step != 0) {
                step = 0;
                buffer.position(++rem);
                continue;
            }
            rem = buffer.position();
        }
        return result;
    }

    public static final ByteBuffer read(ByteBuffer buffer, int count) {
        ByteBuffer slice = buffer.duplicate();
        int limit = buffer.position() + count;
        slice.limit(limit);
        buffer.position(limit);
        return slice;
    }

    public static ByteBuffer fetchFromFile(File file) throws IOException {
        return NIOUtils.fetchFromFileL(file, (int)file.length());
    }

    public static ByteBuffer fetchFromChannel(ReadableByteChannel ch, int size) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(size);
        NIOUtils.readFromChannel(ch, buf);
        buf.flip();
        return buf;
    }

    public static ByteBuffer fetchAllFromChannel(SeekableByteChannel ch) throws IOException {
        ByteBuffer buf;
        ArrayList<ByteBuffer> buffers = new ArrayList<ByteBuffer>();
        do {
            buf = NIOUtils.fetchFromChannel(ch, 0x100000);
            buffers.add(buf);
        } while (buf.hasRemaining());
        return NIOUtils.combineBuffers(buffers);
    }

    public static ByteBuffer fetchFrom(ByteBuffer buf, ReadableByteChannel ch, int size) throws IOException {
        ByteBuffer result = buf.duplicate();
        result.limit(size);
        NIOUtils.readFromChannel(ch, result);
        result.flip();
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ByteBuffer fetchFromFileL(File file, int length) throws IOException {
        ByteBuffer byteBuffer;
        FileChannel is = null;
        try {
            is = new FileInputStream(file).getChannel();
            byteBuffer = NIOUtils.fetchFromChannel(is, length);
        }
        catch (Throwable throwable) {
            NIOUtils.closeQuietly(is);
            throw throwable;
        }
        NIOUtils.closeQuietly(is);
        return byteBuffer;
    }

    public static void writeTo(ByteBuffer buffer, File file) throws IOException {
        FileChannel out = null;
        try {
            out = new FileOutputStream(file).getChannel();
            out.write(buffer);
        }
        catch (Throwable throwable) {
            NIOUtils.closeQuietly(out);
            throw throwable;
        }
        NIOUtils.closeQuietly(out);
    }

    public static byte[] toArray(ByteBuffer buffer) {
        byte[] result = new byte[buffer.remaining()];
        buffer.duplicate().get(result);
        return result;
    }

    public static byte[] toArrayL(ByteBuffer buffer, int count) {
        byte[] result = new byte[Math.min(buffer.remaining(), count)];
        buffer.duplicate().get(result);
        return result;
    }

    public static int readL(ReadableByteChannel channel, ByteBuffer buffer, int length) throws IOException {
        ByteBuffer fork = buffer.duplicate();
        fork.limit(Math.min(fork.position() + length, fork.limit()));
        while (channel.read(fork) != -1 && fork.hasRemaining()) {
        }
        buffer.position(fork.position());
        return buffer.position() == 0 ? -1 : buffer.position();
    }

    public static int readFromChannel(ReadableByteChannel channel, ByteBuffer buffer) throws IOException {
        int rem = buffer.position();
        while (channel.read(buffer) != -1 && buffer.hasRemaining()) {
        }
        return buffer.position() - rem;
    }

    public static void write(ByteBuffer to, ByteBuffer from) {
        if (from.hasArray()) {
            to.put(from.array(), from.arrayOffset() + from.position(), Math.min(to.remaining(), from.remaining()));
        } else {
            to.put(NIOUtils.toArrayL(from, to.remaining()));
        }
    }

    public static void writeL(ByteBuffer to, ByteBuffer from, int count) {
        if (from.hasArray()) {
            to.put(from.array(), from.arrayOffset() + from.position(), Math.min(from.remaining(), count));
        } else {
            to.put(NIOUtils.toArrayL(from, count));
        }
    }

    public static void fill(ByteBuffer buffer, byte val) {
        while (buffer.hasRemaining()) {
            buffer.put(val);
        }
    }

    public static final MappedByteBuffer map(String fileName) throws IOException {
        return NIOUtils.mapFile(new File(fileName));
    }

    public static final MappedByteBuffer mapFile(File file) throws IOException {
        FileInputStream is = new FileInputStream(file);
        MappedByteBuffer map = is.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
        is.close();
        return map;
    }

    public static int skip(ByteBuffer buffer, int count) {
        int toSkip = Math.min(buffer.remaining(), count);
        buffer.position(buffer.position() + toSkip);
        return toSkip;
    }

    public static ByteBuffer from(ByteBuffer buffer, int offset) {
        ByteBuffer dup = buffer.duplicate();
        dup.position(dup.position() + offset);
        return dup;
    }

    public static ByteBuffer combineBuffers(Iterable<ByteBuffer> picture) {
        int size = 0;
        for (ByteBuffer byteBuffer : picture) {
            size += byteBuffer.remaining();
        }
        ByteBuffer result = ByteBuffer.allocate(size);
        for (ByteBuffer byteBuffer : picture) {
            NIOUtils.write(result, byteBuffer);
        }
        result.flip();
        return result;
    }

    public static boolean combineBuffersInto(ByteBuffer dup, List<ByteBuffer> buffers) {
        throw new RuntimeException("Stan");
    }

    public static String readString(ByteBuffer buffer, int len) {
        return Platform.stringFromBytes(NIOUtils.toArray(NIOUtils.read(buffer, len)));
    }

    public static String readPascalStringL(ByteBuffer buffer, int maxLen) {
        ByteBuffer sub = NIOUtils.read(buffer, maxLen + 1);
        return Platform.stringFromBytes(NIOUtils.toArray(NIOUtils.read(sub, Math.min(sub.get() & 0xFF, maxLen))));
    }

    public static void writePascalStringL(ByteBuffer buffer, String string, int maxLen) {
        buffer.put((byte)string.length());
        buffer.put(NIOUtils.asciiString(string));
        NIOUtils.skip(buffer, maxLen - string.length());
    }

    public static byte[] asciiString(String fourcc) {
        return Platform.getBytes(fourcc);
    }

    public static void writePascalString(ByteBuffer buffer, String name) {
        buffer.put((byte)name.length());
        buffer.put(NIOUtils.asciiString(name));
    }

    public static String readPascalString(ByteBuffer buffer) {
        return NIOUtils.readString(buffer, buffer.get() & 0xFF);
    }

    public static String readNullTermString(ByteBuffer buffer) {
        return NIOUtils.readNullTermStringCharset(buffer, "UTF-8");
    }

    public static String readNullTermStringCharset(ByteBuffer buffer, String charset) {
        ByteBuffer fork = buffer.duplicate();
        while (buffer.hasRemaining() && buffer.get() != 0) {
        }
        if (buffer.hasRemaining()) {
            fork.limit(buffer.position() - 1);
        }
        return Platform.stringFromCharset(NIOUtils.toArray(fork), charset);
    }

    public static ByteBuffer readBuf(ByteBuffer buffer) {
        ByteBuffer result = buffer.duplicate();
        buffer.position(buffer.limit());
        return result;
    }

    public static void copy(ReadableByteChannel _in, WritableByteChannel out, long amount) throws IOException {
        int read;
        ByteBuffer buf = ByteBuffer.allocate(65536);
        do {
            buf.position(0);
            buf.limit((int)Math.min(amount, (long)buf.capacity()));
            read = _in.read(buf);
            if (read == -1) continue;
            buf.flip();
            out.write(buf);
            amount -= (long)read;
        } while (read != -1 && amount > 0L);
    }

    public static void closeQuietly(Closeable channel) {
        if (channel == null) {
            return;
        }
        try {
            channel.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public static byte readByte(ReadableByteChannel channel) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1);
        channel.read(buf);
        buf.flip();
        return buf.get();
    }

    public static byte[] readNByte(ReadableByteChannel channel, int n) throws IOException {
        byte[] result = new byte[n];
        channel.read(ByteBuffer.wrap(result));
        return result;
    }

    public static int readInt(ReadableByteChannel channel) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(4);
        channel.read(buf);
        buf.flip();
        return buf.getInt();
    }

    public static int readIntOrder(ReadableByteChannel channel, ByteOrder order) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(4).order(order);
        channel.read(buf);
        buf.flip();
        return buf.getInt();
    }

    public static void writeByte(WritableByteChannel channel, byte value) throws IOException {
        channel.write((ByteBuffer)ByteBuffer.allocate(1).put(value).flip());
    }

    public static void writeIntOrder(WritableByteChannel channel, int value, ByteOrder order) throws IOException {
        ByteBuffer order2 = ByteBuffer.allocate(4).order(order);
        channel.write((ByteBuffer)order2.putInt(value).flip());
    }

    public static void writeIntLE(WritableByteChannel channel, int value) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        channel.write((ByteBuffer)allocate.putInt(value).flip());
    }

    public static void writeInt(WritableByteChannel channel, int value) throws IOException {
        channel.write((ByteBuffer)ByteBuffer.allocate(4).putInt(value).flip());
    }

    public static void writeLong(WritableByteChannel channel, long value) throws IOException {
        channel.write((ByteBuffer)ByteBuffer.allocate(8).putLong(value).flip());
    }

    public static FileChannelWrapper readableChannel(File file) throws FileNotFoundException {
        return new FileChannelWrapper(new FileInputStream(file).getChannel());
    }

    public static FileChannelWrapper writableChannel(File file) throws FileNotFoundException {
        return new FileChannelWrapper(new FileOutputStream(file).getChannel());
    }

    public static FileChannelWrapper rwChannel(File file) throws FileNotFoundException {
        return new FileChannelWrapper(new RandomAccessFile(file, "rw").getChannel());
    }

    public static FileChannelWrapper readableFileChannel(String file) throws FileNotFoundException {
        return new FileChannelWrapper(new FileInputStream(file).getChannel());
    }

    public static FileChannelWrapper writableFileChannel(String file) throws FileNotFoundException {
        return new FileChannelWrapper(new FileOutputStream(file).getChannel());
    }

    public static FileChannelWrapper rwFileChannel(String file) throws FileNotFoundException {
        return new FileChannelWrapper(new RandomAccessFile(file, "rw").getChannel());
    }

    public static AutoFileChannelWrapper autoChannel(File file) throws IOException {
        return new AutoFileChannelWrapper(file);
    }

    public static ByteBuffer duplicate(ByteBuffer bb) {
        ByteBuffer out = ByteBuffer.allocate(bb.remaining());
        out.put(bb.duplicate());
        out.flip();
        return out;
    }

    public static int find(List<ByteBuffer> catalog, ByteBuffer key) {
        byte[] keyA = NIOUtils.toArray(key);
        for (int i = 0; i < catalog.size(); ++i) {
            if (!Platform.arrayEqualsByte(NIOUtils.toArray(catalog.get(i)), keyA)) continue;
            return i;
        }
        return -1;
    }

    public static byte getRel(ByteBuffer bb, int rel) {
        return bb.get(bb.position() + rel);
    }

    public static ByteBuffer cloneBuffer(ByteBuffer pesBuffer) {
        ByteBuffer res = ByteBuffer.allocate(pesBuffer.remaining());
        res.put(pesBuffer.duplicate());
        res.clear();
        return res;
    }

    public static ByteBuffer clone(ByteBuffer byteBuffer) {
        ByteBuffer result = ByteBuffer.allocate(byteBuffer.remaining());
        result.put(byteBuffer.duplicate());
        result.flip();
        return result;
    }

    public static ByteBuffer asByteBuffer(byte[] bytes) {
        return ByteBuffer.wrap(bytes);
    }

    public static ByteBuffer asByteBufferInt(int[] ints) {
        return NIOUtils.asByteBuffer(ArrayUtil.toByteArray(ints));
    }

    public static void relocateLeftover(ByteBuffer bb) {
        int pos = 0;
        while (bb.hasRemaining()) {
            bb.put(pos, bb.get());
            ++pos;
        }
        bb.position(pos);
        bb.limit(bb.capacity());
    }

    public static abstract class FileReader {
        private int oldPd;

        protected abstract void data(ByteBuffer var1, long var2);

        protected abstract void done();

        public void readChannel(SeekableByteChannel ch, int bufferSize, FileReaderListener listener) throws IOException {
            ByteBuffer buf = ByteBuffer.allocate(bufferSize);
            long size = ch.size();
            long pos = ch.position();
            while (ch.read(buf) != -1) {
                buf.flip();
                this.data(buf, pos);
                buf.flip();
                if (listener != null) {
                    int newPd = (int)(100L * pos / size);
                    if (newPd != this.oldPd) {
                        listener.progress(newPd);
                    }
                    this.oldPd = newPd;
                }
                pos = ch.position();
            }
            this.done();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public void readFile(File source, int bufferSize, FileReaderListener listener) throws IOException {
            FileChannelWrapper ch = null;
            try {
                ch = NIOUtils.readableChannel(source);
                this.readChannel(ch, bufferSize, listener);
            }
            finally {
                NIOUtils.closeQuietly(ch);
            }
        }
    }

    public static interface FileReaderListener {
        public void progress(int var1);
    }
}
