/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Label;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.FullBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import java.nio.ByteBuffer;

public class ChannelBox
extends FullBox {
    private int channelLayout;
    private int channelBitmap;
    private ChannelDescription[] descriptions;

    public ChannelBox(Header atom) {
        super(atom);
    }

    public static String fourcc() {
        return "chan";
    }

    public static ChannelBox createChannelBox() {
        return new ChannelBox(new Header(ChannelBox.fourcc()));
    }

    @Override
    public void parse(ByteBuffer input) {
        super.parse(input);
        this.channelLayout = input.getInt();
        this.channelBitmap = input.getInt();
        int numDescriptions = input.getInt();
        this.descriptions = new ChannelDescription[numDescriptions];
        for (int i = 0; i < numDescriptions; ++i) {
            this.descriptions[i] = new ChannelDescription(input.getInt(), input.getInt(), new float[]{Float.intBitsToFloat(input.getInt()), Float.intBitsToFloat(input.getInt()), Float.intBitsToFloat(input.getInt())});
        }
    }

    @Override
    protected void doWrite(ByteBuffer out) {
        super.doWrite(out);
        out.putInt(this.channelLayout);
        out.putInt(this.channelBitmap);
        out.putInt(this.descriptions.length);
        for (int i = 0; i < this.descriptions.length; ++i) {
            ChannelDescription channelDescription = this.descriptions[i];
            out.putInt(channelDescription.getChannelLabel());
            out.putInt(channelDescription.getChannelFlags());
            out.putFloat(channelDescription.getCoordinates()[0]);
            out.putFloat(channelDescription.getCoordinates()[1]);
            out.putFloat(channelDescription.getCoordinates()[2]);
        }
    }

    @Override
    public int estimateSize() {
        return 24 + this.descriptions.length * 20;
    }

    public int getChannelLayout() {
        return this.channelLayout;
    }

    public int getChannelBitmap() {
        return this.channelBitmap;
    }

    public ChannelDescription[] getDescriptions() {
        return this.descriptions;
    }

    public void setChannelLayout(int channelLayout) {
        this.channelLayout = channelLayout;
    }

    public void setDescriptions(ChannelDescription[] descriptions) {
        this.descriptions = descriptions;
    }

    public static class ChannelDescription {
        private int channelLabel;
        private int channelFlags;
        private float[] coordinates = new float[3];

        public ChannelDescription(int channelLabel, int channelFlags, float[] coordinates) {
            this.channelLabel = channelLabel;
            this.channelFlags = channelFlags;
            this.coordinates = coordinates;
        }

        public int getChannelLabel() {
            return this.channelLabel;
        }

        public int getChannelFlags() {
            return this.channelFlags;
        }

        public float[] getCoordinates() {
            return this.coordinates;
        }

        public Label getLabel() {
            return Label.getByVal(this.channelLabel);
        }
    }
}
