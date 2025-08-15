/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import java.nio.ByteBuffer;

public class FielExtension
extends Box {
    private int type;
    private int order;

    public FielExtension(Header header) {
        super(header);
    }

    public static String fourcc() {
        return "fiel";
    }

    public boolean isInterlaced() {
        return this.type == 2;
    }

    public boolean topFieldFirst() {
        return this.order == 1 || this.order == 6;
    }

    public String getOrderInterpretation() {
        if (this.isInterlaced()) {
            switch (this.order) {
                case 1: {
                    return "top";
                }
                case 6: {
                    return "bottom";
                }
                case 9: {
                    return "bottomtop";
                }
                case 14: {
                    return "topbottom";
                }
            }
        }
        return "";
    }

    @Override
    public void parse(ByteBuffer input) {
        this.type = input.get() & 0xFF;
        if (this.isInterlaced()) {
            this.order = input.get() & 0xFF;
        }
    }

    @Override
    public void doWrite(ByteBuffer out) {
        out.put((byte)this.type);
        out.put((byte)this.order);
    }

    @Override
    public int estimateSize() {
        return 10;
    }
}
