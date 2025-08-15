/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;

public class MovieExtendsBox
extends NodeBox {
    public MovieExtendsBox(Header atom) {
        super(atom);
    }

    public static String fourcc() {
        return "mvex";
    }

    public static MovieExtendsBox createMovieExtendsBox() {
        return new MovieExtendsBox(new Header(MovieExtendsBox.fourcc()));
    }
}
