/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.io;

import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.IOException;
import java.io.InputStream;

public abstract class StringReader {
    public static String readString(InputStream input, int len) throws IOException {
        byte[] bs = StringReader._sureRead(input, len);
        return bs == null ? null : Platform.stringFromBytes(bs);
    }

    public static byte[] _sureRead(InputStream input, int len) throws IOException {
        byte[] res = new byte[len];
        if (StringReader.sureRead(input, res, res.length) == len) {
            return res;
        }
        return null;
    }

    public static int sureRead(InputStream input, byte[] buf, int len) throws IOException {
        int read;
        int tmp;
        for (read = 0; read < len && (tmp = input.read(buf, read, len - read)) != -1; read += tmp) {
        }
        return read;
    }

    public static void sureSkip(InputStream is, long l) throws IOException {
        while (l > 0L) {
            l -= is.skip(l);
        }
    }
}
