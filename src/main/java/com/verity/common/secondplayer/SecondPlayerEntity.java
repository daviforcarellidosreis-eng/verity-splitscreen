package com.verity.common.secondplayer;

import com.verity.common.verity.VerityMood;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SecondPlayerEntity extends Player {
    private static final EntityDataAccessor<Integer> DATA_COLOR = SynchedEntityData.defineId(SecondPlayerEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_MOOD = SynchedEntityData.defineId(SecondPlayerEntity.class, EntityDataSerializers.INT);

    public SecondPlayerEntity(EntityType<? extends Player> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_COLOR, 0xFF66CC);
        this.entityData.define(DATA_MOOD, VerityMood.NORMAL.ordinal());
    }

    public void setCustomColor(int rgb) {
        this.entityData.set(DATA_COLOR, rgb);
    }

    public int getCustomColor() {
        return this.entityData.get(DATA_COLOR);
    }

    public void setMood(VerityMood mood) {
        if (mood == null) {
            mood = VerityMood.NORMAL;
        }
        this.entityData.set(DATA_MOOD, mood.ordinal());
    }

    public VerityMood getMood() {
        int ordinal = this.entityData.get(DATA_MOOD);
        VerityMood[] values = VerityMood.values();
        return ordinal >= 0 && ordinal < values.length ? values[ordinal] : VerityMood.NORMAL;
    }
}
