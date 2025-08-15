/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.IBoxFactory;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieHeaderBox;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class NodeBox
extends Box {
    protected List<Box> boxes = new LinkedList<Box>();
    protected IBoxFactory factory;

    public NodeBox(Header atom) {
        super(atom);
    }

    public void setFactory(IBoxFactory factory) {
        this.factory = factory;
    }

    @Override
    public void parse(ByteBuffer input) {
        while (input.remaining() >= 8) {
            Box child = NodeBox.parseChildBox(input, this.factory);
            if (child == null) continue;
            this.boxes.add(child);
        }
    }

    public static Box parseChildBox(ByteBuffer input, IBoxFactory factory) {
        ByteBuffer fork = input.duplicate();
        while (input.remaining() >= 4 && fork.getInt() == 0) {
            input.getInt();
        }
        if (input.remaining() < 4) {
            return null;
        }
        Box ret = null;
        Header childAtom = Header.read(input);
        if (childAtom != null && (long)input.remaining() >= childAtom.getBodySize()) {
            ret = Box.parseBox(NIOUtils.read(input, (int)childAtom.getBodySize()), childAtom, factory);
        }
        return ret;
    }

    public List<Box> getBoxes() {
        return this.boxes;
    }

    public void add(Box box) {
        this.boxes.add(box);
    }

    @Override
    protected void doWrite(ByteBuffer out) {
        for (Box box : this.boxes) {
            box.write(out);
        }
    }

    @Override
    public int estimateSize() {
        int total = 0;
        for (Box box : this.boxes) {
            total += box.estimateSize();
        }
        return total + Header.estimateHeaderSize(total);
    }

    public void addFirst(MovieHeaderBox box) {
        this.boxes.add(0, box);
    }

    public void replace(String fourcc, Box box) {
        this.removeChildren(new String[]{fourcc});
        this.add(box);
    }

    public void replaceBox(Box box) {
        this.removeChildren(new String[]{box.getFourcc()});
        this.add(box);
    }

    @Override
    protected void dump(StringBuilder sb) {
        sb.append("{\"tag\":\"" + this.header.getFourcc() + "\",");
        sb.append("\"boxes\": [");
        this.dumpBoxes(sb);
        sb.append("]");
        sb.append("}");
    }

    protected void dumpBoxes(StringBuilder sb) {
        for (int i = 0; i < this.boxes.size(); ++i) {
            this.boxes.get(i).dump(sb);
            if (i >= this.boxes.size() - 1) continue;
            sb.append(",");
        }
    }

    public void removeChildren(String[] fourcc) {
        Iterator<Box> it = this.boxes.iterator();
        block0: while (it.hasNext()) {
            Box box = it.next();
            String fcc = box.getFourcc();
            for (int i = 0; i < fourcc.length; ++i) {
                String cand = fourcc[i];
                if (!cand.equals(fcc)) continue;
                it.remove();
                continue block0;
            }
        }
    }

    public static Box doCloneBox(Box box, int approxSize, IBoxFactory bf) {
        ByteBuffer buf = ByteBuffer.allocate(approxSize);
        box.write(buf);
        buf.flip();
        return NodeBox.parseChildBox(buf, bf);
    }

    public static Box cloneBox(Box box, int approxSize, IBoxFactory bf) {
        return NodeBox.doCloneBox(box, approxSize, bf);
    }

    public static <T extends Box> T[] findDeep(Box box, Class<T> class1, String name) {
        ArrayList storage = new ArrayList();
        NodeBox.findDeepInner(box, class1, name, storage);
        return storage.toArray((Box[])Array.newInstance(class1, 0));
    }

    public static <T extends Box> void findDeepInner(Box box, Class<T> class1, String name, List<T> storage) {
        if (box == null) {
            return;
        }
        if (name.equals(box.getHeader().getFourcc())) {
            storage.add(box);
            return;
        }
        if (box instanceof NodeBox) {
            NodeBox nb = (NodeBox)box;
            for (Box candidate : nb.getBoxes()) {
                NodeBox.findDeepInner(candidate, class1, name, storage);
            }
        }
    }

    public static <T extends Box> T[] findAll(Box box, Class<T> class1, String path) {
        return NodeBox.findAllPath((Box)box, class1, (String[])new String[]{path});
    }

    public static <T extends Box> T findFirst(NodeBox box, Class<T> clazz, String path) {
        return NodeBox.findFirstPath(box, clazz, new String[]{path});
    }

    public static <T extends Box> T findFirstPath(NodeBox box, Class<T> clazz, String[] path) {
        Box[] result = NodeBox.findAllPath((Box)box, clazz, (String[])path);
        return (T)(result.length > 0 ? result[0] : null);
    }

    public static <T extends Box> T[] findAllPath(Box box, Class<T> class1, String[] path) {
        LinkedList<Box> result = new LinkedList<Box>();
        NodeBox.findBox(box, new ArrayList<String>(Arrays.asList(path)), result);
        ListIterator<T> it = result.listIterator();
        while (it.hasNext()) {
            Box next = (Box)it.next();
            if (next == null) {
                it.remove();
                continue;
            }
            if (Platform.isAssignableFrom(class1, next.getClass())) continue;
            try {
                it.set(Box.asBox(class1, next));
            }
            catch (Exception e) {
                Logger.warn("Failed to reinterpret box: " + next.getFourcc() + " as: " + class1.getName() + "." + e.getMessage());
                it.remove();
            }
        }
        return result.toArray((Box[])Array.newInstance(class1, 0));
    }

    public static void findBox(Box root, List<String> path, Collection<Box> result) {
        if (path.size() > 0) {
            String head = path.remove(0);
            if (root instanceof NodeBox) {
                NodeBox nb = (NodeBox)root;
                for (Box candidate : nb.getBoxes()) {
                    if (head != null && !head.equals(candidate.header.getFourcc())) continue;
                    NodeBox.findBox(candidate, path, result);
                }
            }
            path.add(0, head);
        } else {
            result.add(root);
        }
    }
}
