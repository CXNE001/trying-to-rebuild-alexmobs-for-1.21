/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4;

import com.github.alexthe666.citadel.repack.jcodec.common.AutoFileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.io.FileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.IOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.BoxFactory;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.BoxUtil;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.FileTypeBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieFragmentBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TrakBox;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MP4Util {
    private static Map<Codec, String> codecMapping = new HashMap<Codec, String>();

    public static MovieBox createRefMovie(SeekableByteChannel input, String url) throws IOException {
        MovieBox movie = MP4Util.parseMovieChannel(input);
        TrakBox[] tracks = movie.getTracks();
        for (int i = 0; i < tracks.length; ++i) {
            TrakBox trakBox = tracks[i];
            trakBox.setDataRef(url);
        }
        return movie;
    }

    public static MovieBox parseMovieChannel(SeekableByteChannel input) throws IOException {
        for (Atom atom : MP4Util.getRootAtoms(input)) {
            if (!"moov".equals(atom.getHeader().getFourcc())) continue;
            return (MovieBox)atom.parseBox(input);
        }
        return null;
    }

    public static Movie createRefFullMovie(SeekableByteChannel input, String url) throws IOException {
        Movie movie = MP4Util.parseFullMovieChannel(input);
        TrakBox[] tracks = movie.moov.getTracks();
        for (int i = 0; i < tracks.length; ++i) {
            TrakBox trakBox = tracks[i];
            trakBox.setDataRef(url);
        }
        return movie;
    }

    public static Movie parseFullMovieChannel(SeekableByteChannel input) throws IOException {
        FileTypeBox ftyp = null;
        for (Atom atom : MP4Util.getRootAtoms(input)) {
            if ("ftyp".equals(atom.getHeader().getFourcc())) {
                ftyp = (FileTypeBox)atom.parseBox(input);
                continue;
            }
            if (!"moov".equals(atom.getHeader().getFourcc())) continue;
            return new Movie(ftyp, (MovieBox)atom.parseBox(input));
        }
        return null;
    }

    public static List<MovieFragmentBox> parseMovieFragments(SeekableByteChannel input) throws IOException {
        MovieBox moov = null;
        LinkedList<MovieFragmentBox> fragments = new LinkedList<MovieFragmentBox>();
        for (Atom atom : MP4Util.getRootAtoms(input)) {
            if ("moov".equals(atom.getHeader().getFourcc())) {
                moov = (MovieBox)atom.parseBox(input);
                continue;
            }
            if (!"moof".equalsIgnoreCase(atom.getHeader().getFourcc())) continue;
            fragments.add((MovieFragmentBox)atom.parseBox(input));
        }
        for (MovieFragmentBox fragment : fragments) {
            fragment.setMovie(moov);
        }
        return fragments;
    }

    public static List<Atom> getRootAtoms(SeekableByteChannel input) throws IOException {
        Header atom;
        input.setPosition(0L);
        ArrayList<Atom> result = new ArrayList<Atom>();
        for (long off = 0L; off < input.size(); off += atom.getSize()) {
            input.setPosition(off);
            atom = Header.read(NIOUtils.fetchFromChannel(input, 16));
            if (atom == null) break;
            result.add(new Atom(atom, off));
        }
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Atom findFirstAtomInFile(String fourcc, File input) throws IOException {
        AutoFileChannelWrapper c = new AutoFileChannelWrapper(input);
        try {
            Atom atom = MP4Util.findFirstAtom(fourcc, c);
            return atom;
        }
        finally {
            IOUtils.closeQuietly(c);
        }
    }

    public static Atom findFirstAtom(String fourcc, SeekableByteChannel input) throws IOException {
        List<Atom> rootAtoms = MP4Util.getRootAtoms(input);
        for (Atom atom : rootAtoms) {
            if (!fourcc.equals(atom.getHeader().getFourcc())) continue;
            return atom;
        }
        return null;
    }

    public static Atom atom(SeekableByteChannel input) throws IOException {
        long off = input.position();
        Header atom = Header.read(NIOUtils.fetchFromChannel(input, 16));
        return atom == null ? null : new Atom(atom, off);
    }

    public static MovieBox parseMovie(File source) throws IOException {
        FileChannelWrapper input = null;
        try {
            input = NIOUtils.readableChannel(source);
            MovieBox movieBox = MP4Util.parseMovieChannel(input);
            return movieBox;
        }
        finally {
            if (input != null) {
                input.close();
            }
        }
    }

    public static MovieBox createRefMovieFromFile(File source) throws IOException {
        FileChannelWrapper input = null;
        try {
            input = NIOUtils.readableChannel(source);
            MovieBox movieBox = MP4Util.createRefMovie(input, "file://" + source.getCanonicalPath());
            return movieBox;
        }
        finally {
            if (input != null) {
                input.close();
            }
        }
    }

    public static void writeMovieToFile(File f, MovieBox movie) throws IOException {
        FileChannelWrapper out = null;
        try {
            out = NIOUtils.writableChannel(f);
            MP4Util.writeMovie(out, movie);
        }
        finally {
            IOUtils.closeQuietly(out);
        }
    }

    public static void writeMovie(SeekableByteChannel out, MovieBox movie) throws IOException {
        MP4Util.doWriteMovieToChannel(out, movie, 0);
    }

    public static void doWriteMovieToChannel(SeekableByteChannel out, MovieBox movie, int additionalSize) throws IOException {
        int sizeHint = MP4Util.estimateMoovBoxSize(movie) + additionalSize;
        Logger.debug("Using " + sizeHint + " bytes for MOOV box");
        ByteBuffer buf = ByteBuffer.allocate(sizeHint * 4);
        movie.write(buf);
        buf.flip();
        out.write(buf);
    }

    public static Movie parseFullMovie(File source) throws IOException {
        FileChannelWrapper input = null;
        try {
            input = NIOUtils.readableChannel(source);
            Movie movie = MP4Util.parseFullMovieChannel(input);
            return movie;
        }
        finally {
            if (input != null) {
                input.close();
            }
        }
    }

    public static Movie createRefFullMovieFromFile(File source) throws IOException {
        FileChannelWrapper input = null;
        try {
            input = NIOUtils.readableChannel(source);
            Movie movie = MP4Util.createRefFullMovie(input, "file://" + source.getCanonicalPath());
            return movie;
        }
        finally {
            if (input != null) {
                input.close();
            }
        }
    }

    public static void writeFullMovieToFile(File f, Movie movie) throws IOException {
        FileChannelWrapper out = null;
        try {
            out = NIOUtils.writableChannel(f);
            MP4Util.writeFullMovie(out, movie);
        }
        finally {
            IOUtils.closeQuietly(out);
        }
    }

    public static void writeFullMovie(SeekableByteChannel out, Movie movie) throws IOException {
        MP4Util.doWriteFullMovieToChannel(out, movie, 0);
    }

    public static void doWriteFullMovieToChannel(SeekableByteChannel out, Movie movie, int additionalSize) throws IOException {
        int sizeHint = MP4Util.estimateMoovBoxSize(movie.getMoov()) + additionalSize;
        Logger.debug("Using " + sizeHint + " bytes for MOOV box");
        ByteBuffer buf = ByteBuffer.allocate(sizeHint + 128);
        movie.getFtyp().write(buf);
        movie.getMoov().write(buf);
        buf.flip();
        out.write(buf);
    }

    public static int estimateMoovBoxSize(MovieBox movie) {
        return movie.estimateSize() + 4096;
    }

    public static String getFourcc(Codec codec) {
        return codecMapping.get(codec);
    }

    public static ByteBuffer writeBox(Box box, int approxSize) {
        ByteBuffer buf = ByteBuffer.allocate(approxSize);
        box.write(buf);
        buf.flip();
        return buf;
    }

    static {
        codecMapping.put(Codec.MPEG2, "m2v1");
        codecMapping.put(Codec.H264, "avc1");
        codecMapping.put(Codec.J2K, "mjp2");
    }

    public static class Atom {
        private long offset;
        private Header header;

        public Atom(Header header, long offset) {
            this.header = header;
            this.offset = offset;
        }

        public long getOffset() {
            return this.offset;
        }

        public Header getHeader() {
            return this.header;
        }

        public Box parseBox(SeekableByteChannel input) throws IOException {
            input.setPosition(this.offset + this.header.headerSize());
            return BoxUtil.parseBox(NIOUtils.fetchFromChannel(input, (int)this.header.getBodySize()), this.header, BoxFactory.getDefault());
        }

        public void copy(SeekableByteChannel input, WritableByteChannel out) throws IOException {
            input.setPosition(this.offset);
            NIOUtils.copy(input, out, this.header.getSize());
        }
    }

    public static class Movie {
        private FileTypeBox ftyp;
        private MovieBox moov;

        public Movie(FileTypeBox ftyp, MovieBox moov) {
            this.ftyp = ftyp;
            this.moov = moov;
        }

        public FileTypeBox getFtyp() {
            return this.ftyp;
        }

        public MovieBox getMoov() {
            return this.moov;
        }
    }
}
