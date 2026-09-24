package com.verity.common.secondplayer;

import com.verity.client.input.XboxControllerState;
import com.verity.common.verity.VerityMood;
import com.verity.common.verity.VerityTransformationManager;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

public final class SecondPlayerManager {
    private static final SecondPlayerManager INSTANCE = new SecondPlayerManager();

    private boolean active;
    private SecondPlayerEntity secondPlayer;
    private final SecondPlayerState state = new SecondPlayerState();
    private final VerityTransformationManager transformation = new VerityTransformationManager();
    private XboxControllerState controller = new XboxControllerState();

    private SecondPlayerManager() {
    }

    public static SecondPlayerManager getInstance() {
        return INSTANCE;
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        active = true;
        state.setActive(true);
        ensureSpawned();
    }

    public void deactivate() {
        active = false;
        state.setActive(false);
        if (secondPlayer != null) {
            secondPlayer.discard();
            secondPlayer = null;
        }
    }

    public void reset() {
        deactivate();
    }

    public void ensureSpawned() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) {
            return;
        }

        if (secondPlayer == null || !secondPlayer.isAlive()) {
            secondPlayer = new SecondPlayerEntity(com.verity.VeritySplitMod.SECOND_PLAYER.get(), mc.level);
            BlockPos pos = mc.player.blockPosition().offset(2, 0, 2);
            secondPlayer.setPos(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
            mc.level.addFreshEntity(secondPlayer);
        }
    }

    public SecondPlayerEntity getSecondPlayer() {
        return secondPlayer;
    }

    public void setXboxControllerState(XboxControllerState controller) {
        if (controller != null) {
            this.controller = controller;
        }
    }

    public XboxControllerState getXboxControllerState() {
        return controller;
    }

    public SecondPlayerState getState() {
        return state;
    }

    public VerityTransformationManager getTransformation() {
        return transformation;
    }

    public void tick() {
        if (!active) {
            return;
        }

        ensureSpawned();
        if (secondPlayer == null) {
            return;
        }

        float moveX = deadZone(controller.getLeftX());
        float moveY = -deadZone(controller.getLeftY());
        float aimX = deadZone(controller.getRightX());
        float aimY = deadZone(controller.getRightY());

        secondPlayer.setYRot(secondPlayer.getYRot() + aimX * 3.0F);
        secondPlayer.setXRot(Math.max(-90.0F, Math.min(90.0F, secondPlayer.getXRot() + aimY * 3.0F)));

        double yaw = Math.toRadians(secondPlayer.getYRot());
        double dx = (moveX * Math.cos(yaw) - moveY * Math.sin(yaw)) * 0.18D;
        double dz = (moveY * Math.cos(yaw) + moveX * Math.sin(yaw)) * 0.18D;

        secondPlayer.setDeltaMovement(new Vec3(dx, secondPlayer.getDeltaMovement().y, dz));

        if (controller.isAPressed() && secondPlayer.onGround()) {
            secondPlayer.jumpFromGround();
        }

        secondPlayer.setShiftKeyDown(controller.isBPressed());
        secondPlayer.setSprinting(controller.isRtPressed());

        state.setIrritation(transformation.tickIrritation());
        state.setMood(transformation.getMood());
        secondPlayer.setMood(transformation.getMood());
    }

    private static float deadZone(float value) {
        return Math.abs(value) < 0.12F ? 0.0F : value;
    }
}
