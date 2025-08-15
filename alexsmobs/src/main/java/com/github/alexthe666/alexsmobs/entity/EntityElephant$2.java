/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.Container
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ChestMenu
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;

class EntityElephant.2
implements MenuProvider {
    EntityElephant.2() {
    }

    public AbstractContainerMenu m_7208_(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
        return ChestMenu.m_39246_((int)p_createMenu_1_, (Inventory)p_createMenu_2_, (Container)EntityElephant.this.elephantInventory);
    }

    public Component m_5446_() {
        return Component.m_237115_((String)"entity.alexsmobs.elephant.chest");
    }
}
