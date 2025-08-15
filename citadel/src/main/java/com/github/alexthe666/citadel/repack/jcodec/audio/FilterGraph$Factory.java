/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.audio;

import com.github.alexthe666.citadel.repack.jcodec.audio.Audio;
import com.github.alexthe666.citadel.repack.jcodec.audio.AudioFilter;
import com.github.alexthe666.citadel.repack.jcodec.audio.FilterGraph;
import com.github.alexthe666.citadel.repack.jcodec.audio.FilterSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public static class FilterGraph.Factory {
    private List<FilterSocket> sockets = new ArrayList<FilterSocket>();

    protected FilterGraph.Factory(AudioFilter firstFilter) {
        if (firstFilter.getDelay() != 0) {
            this.sockets.add(FilterSocket.createFilterSocket(new Audio.DummyFilter[]{new Audio.DummyFilter(firstFilter.getNInputs())}));
            this.addLevel(new AudioFilter[]{firstFilter});
        } else {
            this.sockets.add(FilterSocket.createFilterSocket(new AudioFilter[]{firstFilter}));
        }
    }

    public FilterGraph.Factory addLevel(AudioFilter[] filters) {
        FilterSocket socket = FilterSocket.createFilterSocket(filters);
        socket.allocateBuffers(4096);
        this.sockets.add(socket);
        return this;
    }

    public FilterGraph.Factory addLevels(AudioFilter filter, int n) {
        Object[] filters = new AudioFilter[n];
        Arrays.fill(filters, filter);
        return this.addLevel((AudioFilter[])filters);
    }

    public FilterGraph.Factory addLevelSpan(AudioFilter filter) {
        int prevLevelOuts = this.sockets.get(this.sockets.size() - 1).getTotalOutputs();
        if (prevLevelOuts % filter.getNInputs() != 0) {
            throw new IllegalArgumentException("Can't fill " + prevLevelOuts + " with multiple of " + filter.getNInputs());
        }
        return this.addLevels(filter, prevLevelOuts / filter.getNInputs());
    }

    public FilterGraph create() {
        return new FilterGraph(this.sockets.toArray(new FilterSocket[0]), null);
    }
}
