/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 */
package com.github.alexthe666.citadel.server.entity;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.message.DanceJukeboxMessage;
import net.minecraft.core.BlockPos;

public interface IDancesToJukebox {
    public void setDancing(boolean var1);

    public void setJukeboxPos(BlockPos var1);

    default public void onClientPlayMusicDisc(int entityId, BlockPos pos, boolean dancing) {
        Citadel.sendMSGToServer(new DanceJukeboxMessage(entityId, dancing, pos));
        this.setDancing(dancing);
        if (dancing) {
            this.setJukeboxPos(pos);
        } else {
            this.setJukeboxPos(null);
        }
    }
}
