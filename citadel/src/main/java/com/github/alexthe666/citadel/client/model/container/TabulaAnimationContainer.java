/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.client.model.container;

import com.github.alexthe666.citadel.client.model.container.TabulaAnimationComponentContainer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated(since="2.6.2")
public class TabulaAnimationContainer {
    private String name;
    private String identifier;
    private boolean loops;
    private final Map<String, List<TabulaAnimationComponentContainer>> sets = new HashMap<String, List<TabulaAnimationComponentContainer>>();

    public String getName() {
        return this.name;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public boolean doesLoop() {
        return this.loops;
    }

    public Map<String, List<TabulaAnimationComponentContainer>> getComponents() {
        return this.sets;
    }
}
