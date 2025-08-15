/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.boxes.impl.samplegroupentries;

import com.github.alexthe666.citadel.repack.jaad.mp4.MP4InputStream;
import com.github.alexthe666.citadel.repack.jaad.mp4.boxes.impl.samplegroupentries.SampleGroupDescriptionEntry;
import java.io.IOException;

public class VisualSampleGroupEntry
extends SampleGroupDescriptionEntry {
    public VisualSampleGroupEntry() {
        super("Video Sample Group Entry");
    }

    @Override
    public void decode(MP4InputStream in) throws IOException {
    }
}
