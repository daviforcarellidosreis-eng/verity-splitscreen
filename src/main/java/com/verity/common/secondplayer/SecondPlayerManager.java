package com.verity.common.secondplayer;

import com.verity.client.input.XboxControllerState;
import com.verity.common.verity.VerityTransformationManager;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.MoverType;

public final class SecondPlayerManager {
    private static final SecondPlayerManager INSTANCE = new SecondPlayerManager();
    private boolean active;
    private boolean clientReady;
    private SecondPlayerEntity secondPlayer;
    private final SecondPlayerState state = new SecondPlayerState();
    private final VerityTransformationManager transformation = new VerityTransformationManager();
    private XboxControllerState controller = new XboxControllerState();

    private SecondPlayerManager() { }
    public static SecondPlayerManager getInstance() { return INSTANCE; }
    public void setClientReady(boolean value) { clientReady = value; }
    public boolean isClientReady() { return clientReady; }
    public boolean isActive() { return active; }

    public void activate() {
        active = true;
        state.setActive(true);
        ensureSpawned();
    }

    public void deactivate() {
        active = false;
        state.setActive(false);
        if (secondPlayer != null) secondPlayer.discard();
        secondPlayer = null;
    }

    public void reset() { deactivate(); }

    public void ensureSpawned() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null || secondPlayer != null && secondPlayer.isAlive()) return;
        secondPlayer = new SecondPlayerEntity(com.verity.VeritySplitMod.SECOND_PLAYER.get(), mc.level);
        BlockPos p = mc.player.blockPosition().offset(2, 0, 2);
        secondPlayer.setPos(p.getX() + .5D, p.getY(), p.getZ() + .5D);
        mc.level.addFreshEntity(secondPlayer);
    }

    public SecondPlayerEntity getSecondPlayer() { return secondPlayer; }
    public SecondPlayerState getState() { return state; }
    public VerityTransformationManager getTransformation() { return transformation; }
    public void setXboxControllerState(XboxControllerState value) { if (value != null) controller = value; }
    public XboxControllerState getXboxControllerState() { return controller; }

    public void tick() {
        if (!active) return;
        ensureSpawned();
        if (secondPlayer == null) return;

        float strafe = deadZone(controller.getLeftX());
        float forward = -deadZone(controller.getLeftY());
        secondPlayer.setYRot(secondPlayer.getYRot() + deadZone(controller.getRightX()) * 3.0F);
        secondPlayer.setXRot(Math.max(-90.0F, Math.min(90.0F, secondPlayer.getXRot() + deadZone(controller.getRightY()) * 3.0F)));
        double yaw = Math.toRadians(secondPlayer.getYRot());
        double dx = (strafe * Math.cos(yaw) - forward * Math.sin(yaw)) * 0.22D;
        double dz = (forward * Math.cos(yaw) + strafe * Math.sin(yaw)) * 0.22D;
        secondPlayer.setShiftKeyDown(controller.isBPressed());
        secondPlayer.setSprinting(controller.isYPressed());
        secondPlayer.move(MoverType.SELF, new net.minecraft.world.phys.Vec3(dx, secondPlayer.getDeltaMovement().y, dz));
        if (controller.isAPressed() && secondPlayer.onGround()) secondPlayer.jumpFromGround();
        if (controller.isRtPressed()) secondPlayer.swing(net.minecraft.world.InteractionHand.MAIN_HAND);

        state.setIrritation(transformation.tickIrritation());
        state.setMood(transformation.getMood());
        secondPlayer.setMood(transformation.getMood());
    }

    private static float deadZone(float value) { return Math.abs(value) < .12F ? 0.0F : value; }
}
