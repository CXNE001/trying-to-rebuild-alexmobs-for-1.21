/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.IBoxFactory;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.nio.ByteBuffer;

public class BoxUtil {
    public static Box parseBox(ByteBuffer input, Header childAtom, IBoxFactory factory) {
        Box box = factory.newBox(childAtom);
        if (childAtom.getBodySize() < 0x8000000L) {
            box.parse(input);
            return box;
        }
        return new Box.LeafBox(Header.createHeader("free", 8L));
    }

    public static Box parseChildBox(ByteBuffer input, IBoxFactory factory) {
        ByteBuffer fork = input.duplicate();
        while (input.remaining() >= 4 && fork.getInt() == 0) {
            input.getInt();
        }
        if (input.remaining() < 4) {
            return null;
        }
        Header childAtom = Header.read(input);
        if (childAtom != null && (long)input.remaining() >= childAtom.getBodySize()) {
            return BoxUtil.parseBox(NIOUtils.read(input, (int)childAtom.getBodySize()), childAtom, factory);
        }
        return null;
    }

    public static <T extends Box> T as(Class<T> class1, Box.LeafBox box) {
        try {
            Box res = (Box)Platform.newInstance(class1, new Object[]{box.getHeader()});
            res.parse(box.getData().duplicate());
            return (T)res;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean containsBox(NodeBox box, String path) {
        Box b = NodeBox.findFirstPath(box, Box.class, new String[]{path});
        return b != null;
    }

    public static boolean containsBox2(NodeBox box, String path1, String path2) {
        Box b = NodeBox.findFirstPath(box, Box.class, new String[]{path1, path2});
        return b != null;
    }
}
