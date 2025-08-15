/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.logging;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.LogLevel;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.LogSink;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Message;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class OutLogSink
implements LogSink {
    private static String empty = "                                                                                                                                                                                                                                                ";
    public static SimpleFormat DEFAULT_FORMAT = new SimpleFormat(MainUtils.colorString("[#level]", "#color_code") + MainUtils.bold("\t#class.#method (#file:#line):") + "\t#message");
    private PrintStream out;
    private MessageFormat fmt;
    private LogLevel minLevel;

    public static OutLogSink createOutLogSink() {
        return new OutLogSink(System.out, DEFAULT_FORMAT, LogLevel.INFO);
    }

    public OutLogSink(PrintStream out, MessageFormat fmt, LogLevel minLevel) {
        this.out = out;
        this.fmt = fmt;
        this.minLevel = minLevel;
    }

    @Override
    public void postMessage(Message msg) {
        if (msg.getLevel().ordinal() < this.minLevel.ordinal()) {
            return;
        }
        String str = this.fmt.formatMessage(msg);
        this.out.println(str);
    }

    public static interface MessageFormat {
        public String formatMessage(Message var1);
    }

    public static class SimpleFormat
    implements MessageFormat {
        private String fmt;
        private static Map<LogLevel, MainUtils.ANSIColor> colorMap = new HashMap<LogLevel, MainUtils.ANSIColor>();

        public SimpleFormat(String fmt) {
            this.fmt = fmt;
        }

        @Override
        public String formatMessage(Message msg) {
            String str = this.fmt.replace("#level", String.valueOf((Object)msg.getLevel())).replace("#color_code", String.valueOf(30 + colorMap.get((Object)msg.getLevel()).ordinal())).replace("#class", msg.getClassName()).replace("#method", msg.getMethodName()).replace("#file", msg.getFileName()).replace("#line", String.valueOf(msg.getLineNumber())).replace("#message", msg.getMessage());
            return str;
        }

        static {
            colorMap.put(LogLevel.DEBUG, MainUtils.ANSIColor.BROWN);
            colorMap.put(LogLevel.INFO, MainUtils.ANSIColor.GREEN);
            colorMap.put(LogLevel.WARN, MainUtils.ANSIColor.MAGENTA);
            colorMap.put(LogLevel.ERROR, MainUtils.ANSIColor.RED);
        }
    }
}
