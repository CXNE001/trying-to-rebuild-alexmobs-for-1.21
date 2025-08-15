/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.logging;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.LogLevel;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.LogSink;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Message;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.OutLogSink;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Logger {
    private static List<LogSink> stageSinks = new LinkedList<LogSink>();
    private static List<LogSink> sinks;
    private static LogLevel globalLogLevel;

    public static void debug(String message) {
        Logger.message(LogLevel.DEBUG, message, null);
    }

    public static void debug(String message, Object ... args) {
        Logger.message(LogLevel.DEBUG, message, args);
    }

    public static void info(String message) {
        Logger.message(LogLevel.INFO, message, null);
    }

    public static void info(String message, Object ... args) {
        Logger.message(LogLevel.INFO, message, args);
    }

    public static void warn(String message) {
        Logger.message(LogLevel.WARN, message, null);
    }

    public static void warn(String message, Object ... args) {
        Logger.message(LogLevel.WARN, message, args);
    }

    public static void error(String message) {
        Logger.message(LogLevel.ERROR, message, null);
    }

    public static void error(String message, Object ... args) {
        Logger.message(LogLevel.ERROR, message, args);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private static void message(LogLevel level, String message, Object[] args) {
        Message msg;
        if (globalLogLevel.ordinal() >= level.ordinal()) {
            return;
        }
        if (sinks == null) {
            Class<Logger> clazz = Logger.class;
            // MONITORENTER : com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger.class
            if (sinks == null) {
                sinks = stageSinks;
                stageSinks = null;
                if (sinks.isEmpty()) {
                    sinks.add(OutLogSink.createOutLogSink());
                }
            }
            // MONITOREXIT : clazz
        }
        if (LogLevel.DEBUG.equals((Object)globalLogLevel)) {
            StackTraceElement tr = Thread.currentThread().getStackTrace()[3];
            msg = new Message(level, tr.getFileName(), tr.getClassName(), tr.getMethodName(), tr.getLineNumber(), message, args);
        } else {
            msg = new Message(level, "", "", "", 0, message, args);
        }
        Iterator<LogSink> iterator = sinks.iterator();
        while (iterator.hasNext()) {
            LogSink logSink = iterator.next();
            logSink.postMessage(msg);
        }
    }

    public static synchronized void setLevel(LogLevel level) {
        globalLogLevel = level;
    }

    public static synchronized LogLevel getLevel() {
        return globalLogLevel;
    }

    public static void addSink(LogSink sink) {
        if (stageSinks == null) {
            throw new IllegalStateException("Logger already started");
        }
        stageSinks.add(sink);
    }

    static {
        globalLogLevel = LogLevel.INFO;
    }
}
