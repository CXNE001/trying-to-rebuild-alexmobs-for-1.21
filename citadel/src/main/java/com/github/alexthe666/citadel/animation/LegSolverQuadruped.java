/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.animation;

import com.github.alexthe666.citadel.animation.LegSolver;

public final class LegSolverQuadruped
extends LegSolver {
    public final LegSolver.Leg backLeft;
    public final LegSolver.Leg backRight;
    public final LegSolver.Leg frontLeft;
    public final LegSolver.Leg frontRight;

    public LegSolverQuadruped(float forward, float side) {
        this(0.0f, forward, side, side, 1.0f);
    }

    public LegSolverQuadruped(float forwardCenter, float forward, float sideBack, float sideFront, float range) {
        super(new LegSolver.Leg(forwardCenter - forward, sideBack, range, false), new LegSolver.Leg(forwardCenter - forward, -sideBack, range, false), new LegSolver.Leg(forwardCenter + forward, sideFront, range, true), new LegSolver.Leg(forwardCenter + forward, -sideFront, range, true));
        this.backLeft = this.legs[0];
        this.backRight = this.legs[1];
        this.frontLeft = this.legs[2];
        this.frontRight = this.legs[3];
    }
}
