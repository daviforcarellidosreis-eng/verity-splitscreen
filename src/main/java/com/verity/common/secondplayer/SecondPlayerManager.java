package com.verity.common.secondplayer;

import com.verity.common.verity.VerityMood;
import com.verity.common.verity.VerityTransformationManager;
import com.verity.common.verity.VerityVisualState;
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
    private final VerityVisualState visualState = new VerityVisualState();
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
        state.setActive(true);
        if (Minecraft.getInstance().level != null) {
            ensureSpawned();
        }
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
        active = false;
        secondPlayer = null;
        state.setActive(false);
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
            secondPlayer.setCustomColor(visualState.getColor());
            minecraft.level.addFreshEntity(secondPlayer);
        }
    }

    public SecondPlayerEntity getSecondPlayer() {
        return secondPlayer;
    }

    public void setXboxControllerState(XboxControllerState state) {
        this.xboxControllerState = state;
        if (state != null && state.isStartPressed()) {
            setActive(true);
        }
    }

    public XboxControllerState getXboxControllerState() {
        return xboxControllerState;
    }

    public void tick() {
        if (!active) {
            return;
        }

        ensureSpawned();
        if (secondPlayer == null) {
            return;
        }

        Player player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }

        float xAxis = deadZone(xboxControllerState.getLeftX());
        float zAxis = deadZone(xboxControllerState.getLeftY());
        float rotationX = deadZone(xboxControllerState.getRightX());
        float rotationY = deadZone(xboxControllerState.getRightY());

        secondPlayer.setYRot(secondPlayer.getYRot() + rotationX * 3.0F);
        secondPlayer.setXRot(secondPlayer.getXRot() + rotationY * 3.0F);

        double yaw = Math.toRadians(secondPlayer.getYRot());
        double speed = 0.18D;
        if (Math.abs(xAxis) > 0.02F || Math.abs(zAxis) > 0.02F) {
            double dx = (xAxis * Math.cos(yaw) - (-zAxis) * Math.sin(yaw)) * speed;
            double dz = ((-zAxis) * Math.cos(yaw) + xAxis * Math.sin(yaw)) * speed;
            secondPlayer.setDeltaMovement(dx, secondPlayer.getDeltaMovement().y, dz);
        }

        if (xboxControllerState.isAPressed()) {
            secondPlayer.jumpFromGround();
        }

        secondPlayer.setShiftKeyDown(xboxControllerState.isBPressed());
        secondPlayer.setSprinting(xboxControllerState.isRtPressed());

        float irritationDelta = 0.0F;
        if (Math.abs(xAxis) > 0.05F || Math.abs(zAxis) > 0.05F) {
            irritationDelta += 0.15F;
        }
        if (xboxControllerState.isRtPressed()) {
            irritationDelta += 0.25F;
        }
        if (xboxControllerState.isAPressed()) {
            irritationDelta += 0.30F;
        }
        if (xboxControllerState.isBPressed()) {
            irritationDelta += 0.10F;
        }

        transformation.applyStress(irritationDelta);
        VisualizeVerity();

        state.setIrritation(transformation.getIrritation());
        state.setMood(transformation.getMood());
        state.setColor(visualState.getColor());
        secondPlayer.setMood(transformation.getMood());
        secondPlayer.setCustomColor(visualState.getColor());
    }

    public SecondPlayerState getState() {
        return state;
    }

    public VerityTransformationManager getTransformation() {
        return transformation;
    }

    public VerityVisualState getVisualState() {
        return visualState;
    }

    public void setActive(boolean active) {
        this.active = active;
        state.setActive(active);
        if (active) {
            ensureSpawned();
        }
    }

    private void VisualizeVerity() {
        visualState.setMood(transformation.getMood());
        visualState.setAggression(transformation.getIrritation() / 100.0F);
        switch (transformation.getMood()) {
            case MONSTER -> visualState.setColor(0xFF3A2A);
            case IRRITATED -> visualState.setColor(0xFFB347);
            default -> visualState.setColor(0xFF66CC);
        }
    }

    private static float deadZone(float value) {
        return Math.abs(value) < 0.12F ? 0.0F : value;
    }
}
