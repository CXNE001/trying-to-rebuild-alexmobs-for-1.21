/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.scale.highbd;

import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.model.PictureHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.RgbToYuv420jHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.RgbToYuv420pHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.RgbToYuv422pHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.TransformHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv420jToRgbHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv420jToYuv420HiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv420pToRgbHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv420pToYuv422pHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv422jToRgbHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv422jToYuv420pHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv422pToRgbHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv422pToYuv420jHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv422pToYuv420pHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv444jToRgbHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv444jToYuv420pHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv444pToRgb;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv444pToYuv420pHiBD;
import java.util.HashMap;
import java.util.Map;

public class ColorUtilHiBD {
    private static Map<ColorSpace, Map<ColorSpace, TransformHiBD>> map = new HashMap<ColorSpace, Map<ColorSpace, TransformHiBD>>();

    public static TransformHiBD getTransform(ColorSpace from, ColorSpace to) {
        Map<ColorSpace, TransformHiBD> map2 = map.get(from);
        return map2 == null ? null : map2.get(to);
    }

    static {
        HashMap<ColorSpace, TransformHiBD> rgb = new HashMap<ColorSpace, TransformHiBD>();
        rgb.put(ColorSpace.RGB, new Idential());
        rgb.put(ColorSpace.YUV420, new RgbToYuv420pHiBD(0, 0));
        rgb.put(ColorSpace.YUV420J, new RgbToYuv420jHiBD());
        rgb.put(ColorSpace.YUV422, new RgbToYuv422pHiBD(0, 0));
        rgb.put(ColorSpace.YUV422_10, new RgbToYuv422pHiBD(2, 0));
        map.put(ColorSpace.RGB, rgb);
        HashMap<ColorSpace, TransformHiBD> yuv420 = new HashMap<ColorSpace, TransformHiBD>();
        yuv420.put(ColorSpace.YUV420, new Idential());
        yuv420.put(ColorSpace.RGB, new Yuv420pToRgbHiBD(0, 0));
        yuv420.put(ColorSpace.YUV422, new Yuv420pToYuv422pHiBD(0, 0));
        yuv420.put(ColorSpace.YUV422_10, new Yuv420pToYuv422pHiBD(0, 2));
        map.put(ColorSpace.YUV420, yuv420);
        HashMap<ColorSpace, TransformHiBD> yuv422 = new HashMap<ColorSpace, TransformHiBD>();
        yuv422.put(ColorSpace.YUV422, new Idential());
        yuv422.put(ColorSpace.RGB, new Yuv422pToRgbHiBD(0, 0));
        yuv422.put(ColorSpace.YUV420, new Yuv422pToYuv420pHiBD(0, 0));
        yuv422.put(ColorSpace.YUV420J, new Yuv422pToYuv420jHiBD(0, 0));
        map.put(ColorSpace.YUV422, yuv422);
        HashMap<ColorSpace, TransformHiBD> yuv422_10 = new HashMap<ColorSpace, TransformHiBD>();
        yuv422_10.put(ColorSpace.YUV422_10, new Idential());
        yuv422_10.put(ColorSpace.RGB, new Yuv422pToRgbHiBD(2, 0));
        yuv422_10.put(ColorSpace.YUV420, new Yuv422pToYuv420pHiBD(0, 2));
        yuv422_10.put(ColorSpace.YUV420J, new Yuv422pToYuv420jHiBD(0, 2));
        map.put(ColorSpace.YUV422_10, yuv422_10);
        HashMap<ColorSpace, TransformHiBD> yuv444 = new HashMap<ColorSpace, TransformHiBD>();
        yuv444.put(ColorSpace.YUV444, new Idential());
        yuv444.put(ColorSpace.RGB, new Yuv444pToRgb(0, 0));
        yuv444.put(ColorSpace.YUV420, new Yuv444pToYuv420pHiBD(0, 0));
        map.put(ColorSpace.YUV444, yuv444);
        HashMap<ColorSpace, TransformHiBD> yuv444_10 = new HashMap<ColorSpace, TransformHiBD>();
        yuv444_10.put(ColorSpace.YUV444_10, new Idential());
        yuv444_10.put(ColorSpace.RGB, new Yuv444pToRgb(2, 0));
        yuv444_10.put(ColorSpace.YUV420, new Yuv444pToYuv420pHiBD(0, 2));
        map.put(ColorSpace.YUV444_10, yuv444_10);
        HashMap<ColorSpace, TransformHiBD> yuv420j = new HashMap<ColorSpace, TransformHiBD>();
        yuv420j.put(ColorSpace.YUV420J, new Idential());
        yuv420j.put(ColorSpace.RGB, new Yuv420jToRgbHiBD());
        yuv420j.put(ColorSpace.YUV420, new Yuv420jToYuv420HiBD());
        map.put(ColorSpace.YUV420J, yuv420j);
        HashMap<ColorSpace, TransformHiBD> yuv422j = new HashMap<ColorSpace, TransformHiBD>();
        yuv422j.put(ColorSpace.YUV422J, new Idential());
        yuv422j.put(ColorSpace.RGB, new Yuv422jToRgbHiBD());
        yuv422j.put(ColorSpace.YUV420, new Yuv422jToYuv420pHiBD());
        yuv422j.put(ColorSpace.YUV420J, new Yuv422pToYuv420pHiBD(0, 0));
        map.put(ColorSpace.YUV422J, yuv422j);
        HashMap<ColorSpace, TransformHiBD> yuv444j = new HashMap<ColorSpace, TransformHiBD>();
        yuv444j.put(ColorSpace.YUV444J, new Idential());
        yuv444j.put(ColorSpace.RGB, new Yuv444jToRgbHiBD());
        yuv444j.put(ColorSpace.YUV420, new Yuv444jToYuv420pHiBD());
        yuv444j.put(ColorSpace.YUV420J, new Yuv444pToYuv420pHiBD(0, 0));
        map.put(ColorSpace.YUV444J, yuv444j);
    }

    public static class Idential
    implements TransformHiBD {
        @Override
        public void transform(PictureHiBD src, PictureHiBD dst) {
            for (int i = 0; i < 3; ++i) {
                System.arraycopy(src.getPlaneData(i), 0, dst.getPlaneData(i), 0, Math.min(src.getPlaneWidth(i) * src.getPlaneHeight(i), dst.getPlaneWidth(i) * dst.getPlaneHeight(i)));
            }
        }
    }
}
