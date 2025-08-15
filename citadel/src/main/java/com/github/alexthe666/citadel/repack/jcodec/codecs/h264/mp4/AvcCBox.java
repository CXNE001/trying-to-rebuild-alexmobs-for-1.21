/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.mp4;

import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class AvcCBox
extends Box {
    private int profile;
    private int profileCompat;
    private int level;
    private int nalLengthSize;
    private List<ByteBuffer> spsList = new ArrayList<ByteBuffer>();
    private List<ByteBuffer> ppsList = new ArrayList<ByteBuffer>();

    public AvcCBox(Header header) {
        super(header);
    }

    public static String fourcc() {
        return "avcC";
    }

    public static AvcCBox parseAvcCBox(ByteBuffer buf) {
        AvcCBox avcCBox = new AvcCBox(new Header(AvcCBox.fourcc()));
        avcCBox.parse(buf);
        return avcCBox;
    }

    public static AvcCBox createEmpty() {
        return new AvcCBox(new Header(AvcCBox.fourcc()));
    }

    public static AvcCBox createAvcCBox(int profile, int profileCompat, int level, int nalLengthSize, List<ByteBuffer> spsList, List<ByteBuffer> ppsList) {
        AvcCBox avcc = new AvcCBox(new Header(AvcCBox.fourcc()));
        avcc.profile = profile;
        avcc.profileCompat = profileCompat;
        avcc.level = level;
        avcc.nalLengthSize = nalLengthSize;
        avcc.spsList = spsList;
        avcc.ppsList = ppsList;
        return avcc;
    }

    @Override
    public void parse(ByteBuffer input) {
        NIOUtils.skip(input, 1);
        this.profile = input.get() & 0xFF;
        this.profileCompat = input.get() & 0xFF;
        this.level = input.get() & 0xFF;
        int flags = input.get() & 0xFF;
        this.nalLengthSize = (flags & 3) + 1;
        int nSPS = input.get() & 0x1F;
        for (int i = 0; i < nSPS; ++i) {
            short spsSize = input.getShort();
            Preconditions.checkState(39 == (input.get() & 0x3F));
            this.spsList.add(NIOUtils.read(input, spsSize - 1));
        }
        int nPPS = input.get() & 0xFF;
        for (int i = 0; i < nPPS; ++i) {
            short ppsSize = input.getShort();
            Preconditions.checkState(40 == (input.get() & 0x3F));
            this.ppsList.add(NIOUtils.read(input, ppsSize - 1));
        }
    }

    @Override
    public void doWrite(ByteBuffer out) {
        out.put((byte)1);
        out.put((byte)this.profile);
        out.put((byte)this.profileCompat);
        out.put((byte)this.level);
        out.put((byte)-1);
        out.put((byte)(this.spsList.size() | 0xE0));
        for (ByteBuffer sps : this.spsList) {
            out.putShort((short)(sps.remaining() + 1));
            out.put((byte)103);
            NIOUtils.write(out, sps);
        }
        out.put((byte)this.ppsList.size());
        for (ByteBuffer pps : this.ppsList) {
            out.putShort((byte)(pps.remaining() + 1));
            out.put((byte)104);
            NIOUtils.write(out, pps);
        }
    }

    @Override
    public int estimateSize() {
        int sz = 17;
        for (ByteBuffer sps : this.spsList) {
            sz += 3 + sps.remaining();
        }
        for (ByteBuffer pps : this.ppsList) {
            sz += 3 + pps.remaining();
        }
        return sz;
    }

    public int getProfile() {
        return this.profile;
    }

    public int getProfileCompat() {
        return this.profileCompat;
    }

    public int getLevel() {
        return this.level;
    }

    public List<ByteBuffer> getSpsList() {
        return this.spsList;
    }

    public List<ByteBuffer> getPpsList() {
        return this.ppsList;
    }

    public int getNalLengthSize() {
        return this.nalLengthSize;
    }
}
