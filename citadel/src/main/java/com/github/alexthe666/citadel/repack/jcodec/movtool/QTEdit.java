/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieBox;
import com.github.alexthe666.citadel.repack.jcodec.movtool.CompoundMP4Edit;
import com.github.alexthe666.citadel.repack.jcodec.movtool.Flatten;
import com.github.alexthe666.citadel.repack.jcodec.movtool.MP4Edit;
import com.github.alexthe666.citadel.repack.jcodec.movtool.ReplaceMP4Editor;
import java.io.File;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QTEdit {
    protected final EditFactory[] factories;
    private final List<Flatten.ProgressListener> listeners = new ArrayList<Flatten.ProgressListener>();

    public QTEdit(EditFactory[] editFactories) {
        this.factories = editFactories;
    }

    public void addProgressListener(Flatten.ProgressListener listener) {
        this.listeners.add(listener);
    }

    public void execute(String[] args) throws Exception {
        File input;
        LinkedList<String> aa = new LinkedList<String>(Arrays.asList(args));
        LinkedList<MP4Edit> commands = new LinkedList<MP4Edit>();
        while (aa.size() > 0) {
            int i;
            for (i = 0; i < this.factories.length; ++i) {
                if (!aa.get(0).equals(this.factories[i].getName())) continue;
                aa.remove(0);
                try {
                    commands.add(this.factories[i].parseArgs(aa));
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
        if (commands.size() == 0) {
            System.err.println("ERROR: At least one command should be specified");
            this.help();
        }
        if (!(input = new File(aa.remove(0))).exists()) {
            System.err.println("ERROR: Input file '" + input.getAbsolutePath() + "' doesn't exist");
            this.help();
        }
        new ReplaceMP4Editor().replace(input, new CompoundMP4Edit(commands));
    }

    protected void help() {
        System.out.println("Quicktime movie editor");
        System.out.println("Syntax: qtedit <command1> <options> ... <commandN> <options> <movie>");
        System.out.println("Where options:");
        for (EditFactory commandFactory : this.factories) {
            System.out.println("\t" + commandFactory.getHelp());
        }
        System.exit(-1);
    }

    public static abstract class BaseCommand
    implements MP4Edit {
        public void applyRefs(MovieBox movie, FileChannel[][] refs) {
            this.apply(movie);
        }

        @Override
        public abstract void apply(MovieBox var1);
    }

    public static interface EditFactory {
        public String getName();

        public MP4Edit parseArgs(List<String> var1);

        public String getHelp();
    }
}
