/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.ClearApertureBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;

public class ProductionApertureBox
extends ClearApertureBox {
    public static final String PROF = "prof";

    public static ProductionApertureBox createProductionApertureBox(int width, int height) {
        ProductionApertureBox prof = new ProductionApertureBox(new Header(PROF));
        prof.width = width;
        prof.height = height;
        return prof;
    }

    public ProductionApertureBox(Header atom) {
        super(atom);
    }
}
