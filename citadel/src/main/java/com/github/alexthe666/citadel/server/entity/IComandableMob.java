/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.player.Player
 */
package com.github.alexthe666.citadel.server.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;

public interface IComandableMob {
    public int getCommand();

    public void setCommand(int var1);

    default public InteractionResult playerSetCommand(Player owner, Animal ourselves) {
        if (!owner.m_9236_().f_46443_) {
            int command = (this.getCommand() + 1) % 3;
            this.setCommand(command);
            this.sendCommandMessage(owner, command, ourselves.m_7755_());
            if (ourselves instanceof TamableAnimal) {
                ((TamableAnimal)ourselves).m_21839_(command == 1);
            }
        }
        return InteractionResult.PASS;
    }

    default public void sendCommandMessage(Player owner, int command, Component name) {
    }
}
