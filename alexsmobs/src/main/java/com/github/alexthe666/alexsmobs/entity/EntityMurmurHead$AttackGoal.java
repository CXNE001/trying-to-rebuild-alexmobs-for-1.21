/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.arguments.EntityAnchorArgument$Anchor
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityMurmur;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import java.util.EnumSet;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

private class EntityMurmurHead.AttackGoal
extends Goal {
    private int time;
    private int biteCooldown = 0;
    private Vec3 emergeFrom = Vec3.f_82478_;
    private float emergeAngle = 0.0f;

    public EntityMurmurHead.AttackGoal() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return EntityMurmurHead.this.m_5448_() != null && EntityMurmurHead.this.m_5448_().m_6084_();
    }

    public void m_8056_() {
        this.time = 0;
        this.biteCooldown = 0;
        EntityMurmurHead.this.setPulledIn(false);
    }

    public void m_8041_() {
        this.time = 0;
        EntityMurmurHead.this.setPulledIn(true);
        EntityMurmurHead.this.setAngry(false);
    }

    public void m_8037_() {
        LivingEntity target = EntityMurmurHead.this.m_5448_();
        Entity body = EntityMurmurHead.this.getBody();
        if (target != null) {
            double bodyDist;
            double dist = Math.sqrt(EntityMurmurHead.this.m_20238_(target.m_146892_()));
            double d = bodyDist = body != null ? (double)body.m_20270_((Entity)target) : 0.0;
            if (bodyDist > 16.0 && this.time > 30 && body instanceof EntityMurmur) {
                EntityMurmur murmur = (EntityMurmur)body;
                murmur.m_6710_(target);
                murmur.m_21573_().m_5624_((Entity)target, 1.35);
            }
            if (bodyDist > 64.0) {
                EntityMurmurHead.this.setPulledIn(true);
            } else if (this.biteCooldown == 0) {
                EntityMurmurHead.this.setPulledIn(false);
                Vec3 moveTo = target.m_146892_();
                if (this.time > 30) {
                    if (!EntityMurmurHead.this.isAngry()) {
                        EntityMurmurHead.this.m_5496_((SoundEvent)AMSoundRegistry.MURMUR_ANGER.get(), 1.5f * EntityMurmurHead.this.m_6121_(), EntityMurmurHead.this.m_6100_());
                        EntityMurmurHead.this.m_146850_(GameEvent.f_223709_);
                    }
                    EntityMurmurHead.this.setAngry(true);
                    EntityMurmurHead.this.m_21573_().m_26519_(moveTo.f_82479_, moveTo.f_82480_, moveTo.f_82481_, 1.3);
                } else {
                    if (this.time == 0) {
                        this.emergeFrom = EntityMurmurHead.this.getNeckTop(1.0f).m_82520_(0.0, 0.5, 0.0);
                        Vec3 vec3 = moveTo.m_82546_(this.emergeFrom);
                    }
                    boolean clockwise = false;
                    float circleDistance = 2.5f;
                    float circlingTime = 30 * this.time;
                    float angle = (float)Math.PI / 180 * (clockwise ? -circlingTime : circlingTime);
                    double extraX = circleDistance * Mth.m_14031_((float)((float)Math.PI + angle));
                    double extraZ = circleDistance * Mth.m_14089_((float)angle);
                    double y = Math.max(this.emergeFrom.f_82480_ + 2.0, target.m_20188_());
                    Vec3 vec3 = new Vec3(this.emergeFrom.f_82479_ + extraX, y, this.emergeFrom.f_82481_ + extraZ);
                    EntityMurmurHead.this.m_21573_().m_26519_(vec3.f_82479_, vec3.f_82480_, vec3.f_82481_, 0.7);
                }
                EntityMurmurHead.this.m_7618_(EntityAnchorArgument.Anchor.EYES, moveTo);
                if (dist < 1.5 && EntityMurmurHead.this.m_142582_((Entity)target)) {
                    EntityMurmurHead.this.m_5496_((SoundEvent)AMSoundRegistry.MURMUR_ATTACK.get(), EntityMurmurHead.this.m_6121_(), EntityMurmurHead.this.m_6100_());
                    this.biteCooldown = 5 + EntityMurmurHead.this.m_217043_().m_188503_(15);
                    target.m_6469_(EntityMurmurHead.this.m_269291_().m_269333_((LivingEntity)EntityMurmurHead.this), 5.0f);
                }
            } else {
                EntityMurmurHead.this.setPulledIn(true);
                EntityMurmurHead.this.m_7618_(EntityAnchorArgument.Anchor.EYES, target.m_146892_());
                EntityMurmurHead.this.setAngry(false);
            }
            ++this.time;
        }
        if (this.biteCooldown > 0) {
            --this.biteCooldown;
        }
    }
}
