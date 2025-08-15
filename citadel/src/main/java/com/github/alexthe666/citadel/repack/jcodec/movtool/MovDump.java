/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

import com.github.alexthe666.citadel.repack.jcodec.common.io.FileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.IOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.MP4Util;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MovDump {
    public static void main1(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Syntax: movdump [options] <filename>");
            System.out.println("Options: \n\t-f <filename> save header to a file\n\t-a <atom name> dump only a specific atom\n");
            return;
        }
        int idx = 0;
        File headerFile = null;
        String atom = null;
        while (idx < args.length) {
            if ("-f".equals(args[idx])) {
                int n = ++idx;
                ++idx;
                headerFile = new File(args[n]);
                continue;
            }
            if (!"-a".equals(args[idx])) break;
            int n = ++idx;
            ++idx;
            atom = args[n];
        }
        File source = new File(args[idx]);
        if (headerFile != null) {
            MovDump.dumpHeader(headerFile, source);
        }
        if (atom == null) {
            System.out.println(MovDump.print(source));
        } else {
            String dump = MovDump.printAtom(source, atom);
            if (dump != null) {
                System.out.println(dump);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void dumpHeader(File headerFile, File source) throws IOException, FileNotFoundException {
        FileChannelWrapper raf = null;
        FileChannelWrapper daos = null;
        try {
            raf = NIOUtils.readableChannel(source);
            daos = NIOUtils.writableChannel(headerFile);
            for (MP4Util.Atom atom : MP4Util.getRootAtoms(raf)) {
                String fourcc = atom.getHeader().getFourcc();
                if (!"moov".equals(fourcc) && !"ftyp".equals(fourcc)) continue;
                atom.copy(raf, daos);
            }
        }
        finally {
            IOUtils.closeQuietly(raf);
            IOUtils.closeQuietly(daos);
        }
    }

    public static String print(File file) throws IOException {
        return MP4Util.parseMovie(file).toString();
    }

    private static Box findDeep(NodeBox root, String atom) {
        for (Box b : root.getBoxes()) {
            Box res;
            if (atom.equalsIgnoreCase(b.getFourcc())) {
                return b;
            }
            if (!(b instanceof NodeBox) || (res = MovDump.findDeep((NodeBox)b, atom)) == null) continue;
            return res;
        }
        return null;
    }

    public static String printAtom(File file, String atom) throws IOException {
        MovieBox mov = MP4Util.parseMovie(file);
        Box found = MovDump.findDeep(mov, atom);
        if (found == null) {
            System.out.println("Atom " + atom + " not found.");
            return null;
        }
        return found.toString();
    }
}
