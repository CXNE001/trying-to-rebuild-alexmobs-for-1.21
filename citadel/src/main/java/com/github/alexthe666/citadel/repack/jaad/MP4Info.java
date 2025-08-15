/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad;

import com.github.alexthe666.citadel.repack.jaad.mp4.MP4Container;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.MetaData;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.Movie;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.Protection;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.Track;
import com.github.alexthe666.citadel.repack.jaad.mp4.boxes.Box;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Map;

public class MP4Info {
    private static final String USAGE = "usage:\nnet.sourceforge.jaad.MP4Info [options] <infile>\n\n\t-b\talso print all boxes";

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                MP4Info.printUsage();
            } else {
                List<Protection> protections;
                String file;
                boolean boxes = false;
                if (args.length > 1) {
                    if (args[0].equals("-b")) {
                        boxes = true;
                    } else {
                        MP4Info.printUsage();
                    }
                    file = args[1];
                } else {
                    file = args[0];
                }
                MP4Container cont = new MP4Container(new RandomAccessFile(file, "r"));
                Movie movie = cont.getMovie();
                System.out.println("Movie:");
                List<Track> tracks = movie.getTracks();
                for (int i = 0; i < tracks.size(); ++i) {
                    Track t = tracks.get(i);
                    System.out.println("\tTrack " + i + ": " + t.getCodec() + " (language: " + t.getLanguage() + ", created: " + t.getCreationTime() + ")");
                    Protection p = t.getProtection();
                    if (p == null) continue;
                    System.out.println("\t\tprotection: " + p.getScheme());
                }
                if (movie.containsMetaData()) {
                    System.out.println("\tMetadata:");
                    Map<MetaData.Field<?>, Object> data = movie.getMetaData().getAll();
                    for (MetaData.Field field : data.keySet()) {
                        if (field.equals(MetaData.Field.COVER_ARTWORKS)) {
                            List l = (List)data.get(MetaData.Field.COVER_ARTWORKS);
                            System.out.println("\t\t" + l.size() + " Cover Artworks present");
                            continue;
                        }
                        System.out.println("\t\t" + field.getName() + " = " + data.get(field));
                    }
                }
                if ((protections = movie.getProtections()).size() > 0) {
                    System.out.println("\tprotections:");
                    for (Protection protection : protections) {
                        System.out.println("\t\t" + protection.getScheme());
                    }
                }
                if (boxes) {
                    System.out.println("================================");
                    for (Box box : cont.getBoxes()) {
                        MP4Info.printBox(box, 0);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("error while reading file: " + e.toString());
        }
    }

    private static void printUsage() {
        System.out.println(USAGE);
        System.exit(1);
    }

    private static void printBox(Box box, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; ++i) {
            sb.append("  ");
        }
        sb.append(box.toString());
        System.out.println(sb.toString());
        for (Box child : box.getChildren()) {
            MP4Info.printBox(child, level + 1);
        }
    }
}
