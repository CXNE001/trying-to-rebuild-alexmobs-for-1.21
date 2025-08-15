/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.logging;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.LogLevel;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Message;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.OutLogSink;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import java.util.HashMap;
import java.util.Map;

public static class OutLogSink.SimpleFormat
implements OutLogSink.MessageFormat {
    private String fmt;
    private static Map<LogLevel, MainUtils.ANSIColor> colorMap = new HashMap<LogLevel, MainUtils.ANSIColor>();

    public OutLogSink.SimpleFormat(String fmt) {
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
