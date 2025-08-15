/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.tools;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String md5sumBytes(byte[] bytes) {
        MessageDigest md5 = MD5.getDigest();
        md5.update(bytes);
        return MD5.digestToString(md5.digest());
    }

    private static String digestToString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; ++i) {
            byte item = digest[i];
            int b = item & 0xFF;
            if (b < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(b));
        }
        return sb.toString();
    }

    public static String md5sum(ByteBuffer bytes) {
        MessageDigest md5 = MD5.getDigest();
        md5.update(bytes);
        byte[] digest = md5.digest();
        return MD5.digestToString(digest);
    }

    public static MessageDigest getDigest() {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return md5;
    }
}
