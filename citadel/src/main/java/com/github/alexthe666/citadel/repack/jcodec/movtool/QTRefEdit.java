/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.MP4Util;
import com.github.alexthe666.citadel.repack.jcodec.movtool.CompoundMP4Edit;
import com.github.alexthe666.citadel.repack.jcodec.movtool.MP4Edit;
import com.github.alexthe666.citadel.repack.jcodec.movtool.QTEdit;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

public class QTRefEdit {
    protected final QTEdit.EditFactory[] factories;

    public QTRefEdit(QTEdit.EditFactory[] editFactories) {
        this.factories = editFactories;
    }

    public void execute(String[] args) throws Exception {
        LinkedList<String> aa = new LinkedList<String>(Arrays.asList(args));
        LinkedList<MP4Edit> edits = new LinkedList<MP4Edit>();
        while (aa.size() > 0) {
            int i;
            for (i = 0; i < this.factories.length; ++i) {
                if (!aa.get(0).equals(this.factories[i].getName())) continue;
                aa.remove(0);
                try {
                    edits.add(this.factories[i].parseArgs(aa));
                    break;
                }
                catch (Exception e) {
                    System.err.println("ERROR: " + e.getMessage());
                    return;
                }
            }
            if (i != this.factories.length) continue;
            break;
        }
        if (aa.size() == 0) {
            System.err.println("ERROR: A movie file should be specified");
            this.help();
        }
        if (edits.size() == 0) {
            System.err.println("ERROR: At least one command should be specified");
            this.help();
        }
        File input = new File(aa.remove(0));
        if (aa.size() == 0) {
            System.err.println("ERROR: A movie output file should be specified");
            this.help();
        }
        File output = new File(aa.remove(0));
        if (!input.exists()) {
            System.err.println("ERROR: Input file '" + input.getAbsolutePath() + "' doesn't exist");
            this.help();
        }
        if (output.exists()) {
            System.err.println("WARNING: Output file '" + output.getAbsolutePath() + "' exist, overwritting");
        }
        MP4Util.Movie ref = MP4Util.createRefFullMovieFromFile(input);
        new CompoundMP4Edit(edits).apply(ref.getMoov());
        MP4Util.writeFullMovieToFile(output, ref);
        System.out.println("INFO: Created reference file: " + output.getAbsolutePath());
    }

    protected void help() {
        System.out.println("Quicktime movie editor");
        System.out.println("Syntax: qtedit <command1> <options> ... <commandN> <options> <movie> <output>");
        System.out.println("Where options:");
        for (QTEdit.EditFactory commandFactory : this.factories) {
            System.out.println("\t" + commandFactory.getHelp());
        }
        System.exit(-1);
    }
}
