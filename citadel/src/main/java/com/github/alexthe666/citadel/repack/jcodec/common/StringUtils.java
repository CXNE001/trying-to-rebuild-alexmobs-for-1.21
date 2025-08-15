/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import java.util.ArrayList;

public class StringUtils {
    public static final String[] zeroPad00 = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09"};

    private static String[] splitWorker4(String str, String separatorChars, int max, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return new String[0];
        }
        ArrayList<String> list = new ArrayList<String>();
        int sizePlus1 = 1;
        int i = 0;
        int start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                ++i;
            }
        } else if (separatorChars.length() == 1) {
            char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                ++i;
            }
        } else {
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                ++i;
            }
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return new String[0];
        }
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        int start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match || preserveAllTokens) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }
            lastMatch = false;
            match = true;
            ++i;
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    public static String[] split(String str) {
        return StringUtils.splitS3(str, null, -1);
    }

    public static String[] splitS(String str, String separatorChars) {
        return StringUtils.splitWorker4(str, separatorChars, -1, false);
    }

    public static String[] splitS3(String str, String separatorChars, int max) {
        return StringUtils.splitWorker4(str, separatorChars, max, false);
    }

    public static String[] splitC(String str, char separatorChar) {
        return StringUtils.splitWorker(str, separatorChar, false);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private static boolean isDelimiter(char ch, char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        }
        int isize = delimiters.length;
        for (int i = 0; i < isize; ++i) {
            if (ch != delimiters[i]) continue;
            return true;
        }
        return false;
    }

    public static String capitaliseAllWords(String str) {
        return StringUtils.capitalize(str);
    }

    public static String capitalize(String str) {
        return StringUtils.capitalizeD(str, null);
    }

    public static String capitalizeD(String str, char[] delimiters) {
        int delimLen;
        int n = delimLen = delimiters == null ? -1 : delimiters.length;
        if (str == null || str.length() == 0 || delimLen == 0) {
            return str;
        }
        int strLen = str.length();
        StringBuilder buffer = new StringBuilder(strLen);
        boolean capitalizeNext = true;
        for (int i = 0; i < strLen; ++i) {
            char ch = str.charAt(i);
            if (StringUtils.isDelimiter(ch, delimiters)) {
                buffer.append(ch);
                capitalizeNext = true;
                continue;
            }
            if (capitalizeNext) {
                buffer.append(Character.toTitleCase(ch));
                capitalizeNext = false;
                continue;
            }
            buffer.append(ch);
        }
        return buffer.toString();
    }

    public static String join(Object[] array) {
        return StringUtils.joinS(array, null);
    }

    public static String join2(Object[] array, char separator) {
        if (array == null) {
            return null;
        }
        return StringUtils.join4(array, separator, 0, array.length);
    }

    public static String join4(Object[] array, char separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        int bufSize = endIndex - startIndex;
        if (bufSize <= 0) {
            return "";
        }
        StringBuilder buf = new StringBuilder(bufSize *= (array[startIndex] == null ? 16 : array[startIndex].toString().length()) + 1);
        for (int i = startIndex; i < endIndex; ++i) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] == null) continue;
            buf.append(array[i]);
        }
        return buf.toString();
    }

    public static String joinS(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return StringUtils.joinS4(array, separator, 0, array.length);
    }

    public static String joinS4(Object[] array, String separator, int startIndex, int endIndex) {
        int bufSize;
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }
        if ((bufSize = endIndex - startIndex) <= 0) {
            return "";
        }
        StringBuilder buf = new StringBuilder(bufSize *= (array[startIndex] == null ? 16 : array[startIndex].toString().length()) + separator.length());
        for (int i = startIndex; i < endIndex; ++i) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] == null) continue;
            buf.append(array[i]);
        }
        return buf.toString();
    }

    public static String zeroPad2(int x) {
        if (x >= 0 && x < 10) {
            return zeroPad00[x];
        }
        return "" + x;
    }

    public static String zeroPad3(int n) {
        String s1 = StringUtils.zeroPad2(n);
        return s1.length() == 2 ? "0" + s1 : s1;
    }
}
