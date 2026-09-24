package com.verity.common.secondplayer;

import com.verity.client.input.XboxControllerState;
import com.verity.common.verity.VerityMood;
import com.verity.common.verity.VerityTransformationManager;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

public final class SecondPlayerManager {
    private static final SecondPlayerManager INSTANCE = new SecondPlayerManager();

    private boolean active;
    private boolean clientReady;
    private SecondPlayerEntity secondPlayer;
    private final SecondPlayerState state = new SecondPlayerState();
    private final VerityTransformationManager transformation = new VerityTransformationManager();
    private XboxControllerState xboxControllerState = new XboxControllerState();

    private SecondPlayerManager() {
    }

    public static SecondPlayerManager getInstance() {
        return INSTANCE;
    }

    public void setClientReady(boolean clientReady) {
        this.clientReady = clientReady;
    }

    public boolean isClientReady() {
        return clientReady;
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        active = true;
        if (Minecraft.getInstance().level != null) {
            ensureSpawned();
        }
    }

    public void deactivate() {
        active = false;
        if (secondPlayer != null) {
            secondPlayer.discard();
            secondPlayer = null;
        }
    }

    public void reset() {
        active = false;
        secondPlayer = null;
    }

    public void ensureSpawned() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft == null || minecraft.level == null || minecraft.player == null) {
            return;
        }

        if (secondPlayer == null || !secondPlayer.isAlive()) {
            secondPlayer = new SecondPlayerEntity(com.verity.VeritySplitMod.SECOND_PLAYER.get(), minecraft.level);
            BlockPos pos = minecraft.player.blockPosition().offset(2, 0, 2);
            secondPlayer.setPos(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
            minecraft.level.addFreshEntity(secondPlayer);
        }
    }

    public SecondPlayerEntity getSecondPlayer() {
        return secondPlayer;
    }

    public void setXboxControllerState(XboxControllerState state) {
        this.xboxControllerState = state;
        if (state != null) {
            if (state.isStartPressed()) {
                setActive(true);
            }
        }
    }

    public XboxControllerState getXboxControllerState() {
        return xboxControllerState;
    }

    public void tick() {
        if (!active || secondPlayer == null) {
            return;
        }

        Player player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }

        // Movement by left stick
        float xAxis = xboxControllerState.getLeftX();
        float zAxis = xboxControllerState.getLeftY();
        secondPlayer.setYRot(secondPlayer.getYRot() + xboxControllerState.getRightX() * 2.0F);
        secondPlayer.setXRot(secondPlayer.getXRot() + xboxControllerState.getRightY() * 2.0F);

        if (Math.abs(xAxis) > 0.05F || Math.abs(zAxis) > 0.05F) {
            double speed = 0.20D;
            secondPlayer.setDeltaMovement(
                xAxis * speed,
                secondPlayer.getDeltaMovement().y,
                -zAxis * speed
            );
        }

        if (xboxControllerState.isAPressed()) {
            secondPlayer.jumpFromGround();
        }

        if (xboxControllerState.isBPressed()) {
            secondPlayer.setShiftKeyDown(true);
        } else {
            secondPlayer.setShiftKeyDown(false);
        }

        if (xboxControllerState.isRtPressed()) {
            secondPlayer.setSprinting(true);
        } else {
            secondPlayer.setSprinting(false);
        }

        state.setIrritation(transformation.tickIrritation());
        state.setMood(transformation.getMood());
    }

    public SecondPlayerState getState() {
        return state;
    }

    public VerityTransformationManager getTransformation() {
        return transformation;
    }

    public void setActive(boolean active) {
        this.active = active;
        state.setActive(active);
        if (active) {
            ensureSpawned();
        }
    }
}
