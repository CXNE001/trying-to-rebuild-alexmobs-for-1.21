/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.IBoxFactory;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MetaBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.UdtaMetaBox;
import java.nio.ByteBuffer;
import java.util.List;

public class UdtaBox
extends NodeBox {
    private static final String FOURCC = "udta";

    public static UdtaBox createUdtaBox() {
        return new UdtaBox(Header.createHeader(UdtaBox.fourcc(), 0L));
    }

    @Override
    public void setFactory(final IBoxFactory _factory) {
        this.factory = new IBoxFactory(){

            @Override
            public Box newBox(Header header) {
                if (header.getFourcc().equals(UdtaMetaBox.fourcc())) {
                    UdtaMetaBox box = new UdtaMetaBox(header);
                    box.setFactory(_factory);
                    return box;
                }
                return _factory.newBox(header);
            }
        };
    }

    public UdtaBox(Header atom) {
        super(atom);
    }

    public MetaBox meta() {
        return NodeBox.findFirst(this, MetaBox.class, MetaBox.fourcc());
    }

    public static String fourcc() {
        return FOURCC;
    }

    public String latlng() {
        Box gps = UdtaBox.findGps(this);
        if (gps == null) {
            return null;
        }
        ByteBuffer data = UdtaBox.getData(gps);
        if (data == null) {
            return null;
        }
        if (data.remaining() < 4) {
            return null;
        }
        data.getInt();
        byte[] coordsBytes = new byte[data.remaining()];
        data.get(coordsBytes);
        String latlng = new String(coordsBytes);
        return latlng;
    }

    static Box findGps(UdtaBox udta) {
        List<Box> boxes1 = udta.getBoxes();
        for (Box box : boxes1) {
            if (!box.getFourcc().endsWith("xyz")) continue;
            return box;
        }
        return null;
    }

    static ByteBuffer getData(Box box) {
        if (box instanceof Box.LeafBox) {
            Box.LeafBox leaf = (Box.LeafBox)box;
            return leaf.getData();
        }
        return null;
    }
}
