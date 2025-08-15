/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.client.extensions.common.IClientItemExtensions
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.entity.IFalconry;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.item.ILeftClick;
import com.github.alexthe666.alexsmobs.message.MessageSyncEntityPos;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ItemFalconryGlove
extends Item
implements ILeftClick {
    public ItemFalconryGlove(Item.Properties properties) {
        super(properties);
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions)AlexsMobs.PROXY.getISTERProperties());
    }

    @Override
    public boolean onLeftClick(ItemStack stack, LivingEntity playerIn) {
        if (stack.m_41720_() == AMItemRegistry.FALCONRY_GLOVE.get()) {
            float dist = 128.0f;
            Vec3 Vector3d = playerIn.m_20299_(1.0f);
            Vec3 Vector3d1 = playerIn.m_20252_(1.0f);
            double vector3d1xDist = Vector3d1.f_82479_ * 128.0;
            double vector3d1yDist = Vector3d1.f_82480_ * 128.0;
            double vector3d1zDist = Vector3d1.f_82481_ * 128.0;
            Vec3 Vector3d2 = Vector3d.m_82520_(vector3d1xDist, vector3d1yDist, vector3d1zDist);
            double d1 = 128.0;
            Entity pointedEntity = null;
            List list = playerIn.m_9236_().m_6249_((Entity)playerIn, playerIn.m_20191_().m_82363_(vector3d1xDist, vector3d1yDist, vector3d1zDist).m_82377_(1.0, 1.0, 1.0), (Predicate)new com.google.common.base.Predicate<Entity>(){

                public boolean apply(@Nullable Entity entity) {
                    return entity != null && entity.m_6087_() && (entity instanceof Player || entity instanceof LivingEntity);
                }
            });
            for (Entity entity1 : list) {
                double d3;
                AABB axisalignedbb = entity1.m_20191_().m_82400_((double)entity1.m_6143_());
                Optional optional = axisalignedbb.m_82371_(Vector3d, Vector3d2);
                if (axisalignedbb.m_82390_(Vector3d)) {
                    if (!(d1 >= 0.0)) continue;
                    d1 = 0.0;
                    continue;
                }
                if (!optional.isPresent() || !((d3 = Vector3d.m_82554_((Vec3)optional.get())) < d1) && d1 != 0.0) continue;
                if (entity1.m_20201_() == playerIn.m_20201_() && !playerIn.canRiderInteract()) {
                    if (d1 != 0.0) continue;
                    pointedEntity = entity1;
                    continue;
                }
                pointedEntity = entity1;
                d1 = d3;
            }
            if (!playerIn.m_20197_().isEmpty()) {
                for (Entity entity : playerIn.m_20197_()) {
                    if (!(entity instanceof IFalconry) || !(entity instanceof Animal)) continue;
                    Animal animal = (Animal)entity;
                    IFalconry falcon = (IFalconry)entity;
                    animal.m_6038_();
                    animal.m_7678_(playerIn.m_20185_(), playerIn.m_20188_(), playerIn.m_20189_(), animal.m_146908_(), animal.m_146909_());
                    if (animal.m_9236_().f_46443_) {
                        AlexsMobs.sendMSGToServer(new MessageSyncEntityPos(animal.m_19879_(), playerIn.m_20185_(), playerIn.m_20188_(), playerIn.m_20189_()));
                    } else {
                        AlexsMobs.sendMSGToAll(new MessageSyncEntityPos(animal.m_19879_(), playerIn.m_20185_(), playerIn.m_20188_(), playerIn.m_20189_()));
                    }
                    if (playerIn instanceof Player) {
                        falcon.onLaunch((Player)playerIn, pointedEntity);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
