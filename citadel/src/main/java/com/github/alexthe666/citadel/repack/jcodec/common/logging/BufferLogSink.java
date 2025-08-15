/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.logging;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.LogSink;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Message;
import java.util.LinkedList;
import java.util.List;

public class BufferLogSink
implements LogSink {
    private List<Message> messages = new LinkedList<Message>();

    @Override
    public void postMessage(Message msg) {
        this.messages.add(msg);
    }

    public List<Message> getMessages() {
        return this.messages;
    }
}
