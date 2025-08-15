/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.Tuple;
import com.github.alexthe666.citadel.repack.jcodec.common.io.FileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.BoxFactory;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.BoxUtil;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.MP4Util;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieFragmentBox;
import com.github.alexthe666.citadel.repack.jcodec.movtool.MP4Edit;
import java.io.File;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InplaceMP4Editor {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean modify(File file, MP4Edit edit) throws IOException {
        FileChannelWrapper fi = null;
        try {
            fi = NIOUtils.rwChannel(file);
            List<Tuple._2<MP4Util.Atom, ByteBuffer>> fragments = this.doTheFix(fi, edit);
            if (fragments == null) {
                boolean bl = false;
                return bl;
            }
            for (Tuple._2<MP4Util.Atom, ByteBuffer> fragment : fragments) {
                this.replaceBox(fi, (MP4Util.Atom)fragment.v0, (ByteBuffer)fragment.v1);
            }
            boolean bl = true;
            return bl;
        }
        finally {
            NIOUtils.closeQuietly(fi);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean copy(File src, File dst, MP4Edit edit) throws IOException {
        FileChannelWrapper fi = null;
        FileChannelWrapper fo = null;
        try {
            fi = NIOUtils.readableChannel(src);
            fo = NIOUtils.writableChannel(dst);
            List fragments = this.doTheFix(fi, edit);
            if (fragments == null) {
                boolean bl = false;
                return bl;
            }
            List fragOffsets = Tuple._2map0(fragments, new Tuple.Mapper<MP4Util.Atom, Long>(){

                @Override
                public Long map(MP4Util.Atom t) {
                    return t.getOffset();
                }
            });
            Map rewrite = Tuple.asMap(fragOffsets);
            for (MP4Util.Atom atom : MP4Util.getRootAtoms(fi)) {
                ByteBuffer byteBuffer = (ByteBuffer)rewrite.get(atom.getOffset());
                if (byteBuffer != null) {
                    fo.write(byteBuffer);
                    continue;
                }
                atom.copy(fi, fo);
            }
            boolean bl = true;
            return bl;
        }
        finally {
            NIOUtils.closeQuietly(fi);
            NIOUtils.closeQuietly(fo);
        }
    }

    public boolean replace(File src, MP4Edit edit) throws IOException {
        File tmp = new File(src.getParentFile(), "." + src.getName());
        if (this.copy(src, tmp, edit)) {
            tmp.renameTo(src);
            return true;
        }
        return false;
    }

    private List<Tuple._2<MP4Util.Atom, ByteBuffer>> doTheFix(SeekableByteChannel fi, MP4Edit edit) throws IOException {
        MP4Util.Atom moovAtom = this.getMoov(fi);
        Preconditions.checkNotNull(moovAtom);
        ByteBuffer moovBuffer = this.fetchBox(fi, moovAtom);
        MovieBox moovBox = (MovieBox)this.parseBox(moovBuffer);
        LinkedList<Tuple._2<MP4Util.Atom, ByteBuffer>> fragments = new LinkedList<Tuple._2<MP4Util.Atom, ByteBuffer>>();
        if (BoxUtil.containsBox(moovBox, "mvex")) {
            LinkedList temp = new LinkedList();
            for (MP4Util.Atom atom : this.getFragments(fi)) {
                ByteBuffer fragBuffer = this.fetchBox(fi, atom);
                fragments.add(Tuple.pair(atom, fragBuffer));
                MovieFragmentBox fragBox = (MovieFragmentBox)this.parseBox(fragBuffer);
                fragBox.setMovie(moovBox);
                temp.add(Tuple.pair(fragBuffer, fragBox));
            }
            edit.applyToFragment(moovBox, Tuple._2_project1(temp).toArray(new MovieFragmentBox[0]));
            for (Tuple._2 _22 : temp) {
                if (this.rewriteBox((ByteBuffer)_22.v0, (Box)_22.v1)) continue;
                return null;
            }
        } else {
            edit.apply(moovBox);
        }
        if (!this.rewriteBox(moovBuffer, moovBox)) {
            return null;
        }
        fragments.add(Tuple.pair(moovAtom, moovBuffer));
        return fragments;
    }

    private void replaceBox(SeekableByteChannel fi, MP4Util.Atom atom, ByteBuffer buffer) throws IOException {
        fi.setPosition(atom.getOffset());
        fi.write(buffer);
    }

    private boolean rewriteBox(ByteBuffer buffer, Box box) {
        try {
            buffer.clear();
            box.write(buffer);
            if (buffer.hasRemaining()) {
                if (buffer.remaining() < 8) {
                    return false;
                }
                buffer.putInt(buffer.remaining());
                buffer.put(new byte[]{102, 114, 101, 101});
            }
            buffer.flip();
            return true;
        }
        catch (BufferOverflowException e) {
            return false;
        }
    }

    private ByteBuffer fetchBox(SeekableByteChannel fi, MP4Util.Atom moov) throws IOException {
        fi.setPosition(moov.getOffset());
        ByteBuffer oldMov = NIOUtils.fetchFromChannel(fi, (int)moov.getHeader().getSize());
        return oldMov;
    }

    private Box parseBox(ByteBuffer oldMov) {
        Header header = Header.read(oldMov);
        Box box = BoxUtil.parseBox(oldMov, header, BoxFactory.getDefault());
        return box;
    }

    private MP4Util.Atom getMoov(SeekableByteChannel f) throws IOException {
        for (MP4Util.Atom atom : MP4Util.getRootAtoms(f)) {
            if (!"moov".equals(atom.getHeader().getFourcc())) continue;
            return atom;
        }
        return null;
    }

    private List<MP4Util.Atom> getFragments(SeekableByteChannel f) throws IOException {
        LinkedList<MP4Util.Atom> result = new LinkedList<MP4Util.Atom>();
        for (MP4Util.Atom atom : MP4Util.getRootAtoms(f)) {
            if (!"moof".equals(atom.getHeader().getFourcc())) continue;
            result.add(atom);
        }
        return result;
    }
}
