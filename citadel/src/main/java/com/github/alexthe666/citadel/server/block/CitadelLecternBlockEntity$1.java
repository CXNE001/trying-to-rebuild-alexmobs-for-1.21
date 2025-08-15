/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.citadel.server.block;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

class CitadelLecternBlockEntity.1
implements Container {
    CitadelLecternBlockEntity.1() {
    }

    public int m_6643_() {
        return 1;
    }

    public boolean m_7983_() {
        return CitadelLecternBlockEntity.this.book.m_41619_();
    }

    public ItemStack m_8020_(int i) {
        return i == 0 ? CitadelLecternBlockEntity.this.book : ItemStack.f_41583_;
    }

    public ItemStack m_7407_(int i, int j) {
        if (i == 0) {
            ItemStack itemstack = CitadelLecternBlockEntity.this.book.m_41620_(j);
            if (CitadelLecternBlockEntity.this.book.m_41619_()) {
                CitadelLecternBlockEntity.this.onBookItemRemove();
            }
            return itemstack;
        }
        return ItemStack.f_41583_;
    }

    public ItemStack m_8016_(int i) {
        if (i == 0) {
            ItemStack itemstack = CitadelLecternBlockEntity.this.book;
            CitadelLecternBlockEntity.this.book = ItemStack.f_41583_;
            CitadelLecternBlockEntity.this.onBookItemRemove();
            return itemstack;
        }
        return ItemStack.f_41583_;
    }

    public void m_6836_(int i, ItemStack stack) {
    }

    public int m_6893_() {
        return 1;
    }

    public void m_6596_() {
        CitadelLecternBlockEntity.this.m_6596_();
    }

    public boolean m_6542_(Player p_59588_) {
        if (CitadelLecternBlockEntity.this.f_58857_.m_7702_(CitadelLecternBlockEntity.this.f_58858_) != CitadelLecternBlockEntity.this) {
            return false;
        }
        return !(p_59588_.m_20275_((double)CitadelLecternBlockEntity.this.f_58858_.m_123341_() + 0.5, (double)CitadelLecternBlockEntity.this.f_58858_.m_123342_() + 0.5, (double)CitadelLecternBlockEntity.this.f_58858_.m_123343_() + 0.5) > 64.0) && CitadelLecternBlockEntity.this.hasBook();
    }

    public boolean m_7013_(int i, ItemStack stack) {
        return false;
    }

    public void m_6211_() {
    }
}
