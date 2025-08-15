/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
 *  net.minecraft.world.Clearable
 *  net.minecraft.world.Container
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerData
 *  net.minecraft.world.inventory.LecternMenu
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.LecternBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 */
package com.github.alexthe666.citadel.server.block;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.block.LecternBooks;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.LecternMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CitadelLecternBlockEntity
extends BlockEntity
implements Clearable,
MenuProvider {
    private ItemStack book = ItemStack.f_41583_;
    private final Container bookAccess = new Container(){

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
    };
    private final ContainerData dataAccess = new ContainerData(){

        public int m_6413_(int i) {
            return 0;
        }

        public void m_8050_(int i, int j) {
        }

        public int m_6499_() {
            return 1;
        }
    };

    public CitadelLecternBlockEntity(BlockPos pos, BlockState state) {
        super((BlockEntityType)Citadel.LECTERN_BE.get(), pos, state);
    }

    public ItemStack getBook() {
        return this.book;
    }

    public boolean hasBook() {
        return LecternBooks.isLecternBook(this.book);
    }

    public void setBook(ItemStack stack) {
        this.setBook(stack, null);
    }

    void onBookItemRemove() {
        LecternBlock.m_269306_(null, (Level)this.m_58904_(), (BlockPos)this.m_58899_(), (BlockState)this.m_58900_(), (boolean)false);
    }

    public void setBook(ItemStack itemStack, @Nullable Player player) {
        this.book = itemStack;
        this.m_6596_();
    }

    public int getRedstoneSignal() {
        return this.hasBook() ? 1 : 0;
    }

    public void m_142466_(CompoundTag tag) {
        super.m_142466_(tag);
        this.book = tag.m_128425_("Book", 10) ? ItemStack.m_41712_((CompoundTag)tag.m_128469_("Book")) : ItemStack.f_41583_;
    }

    protected void m_183515_(CompoundTag tag) {
        super.m_183515_(tag);
        if (!this.getBook().m_41619_()) {
            tag.m_128365_("Book", (Tag)this.getBook().m_41739_(new CompoundTag()));
        }
    }

    public void m_6211_() {
        this.setBook(ItemStack.f_41583_);
    }

    public AbstractContainerMenu m_7208_(int i, Inventory inventory, Player player) {
        return new LecternMenu(i, this.bookAccess, this.dataAccess);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.m_195640_((BlockEntity)this);
    }

    public CompoundTag m_5995_() {
        return this.m_187482_();
    }

    public Component m_5446_() {
        return Component.m_237115_((String)"container.lectern");
    }
}
