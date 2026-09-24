package com.verity.client.camera;

import com.verity.common.secondplayer.SecondPlayerManager;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;

public final class CameraController {
    private static final CameraController INSTANCE = new CameraController();

    private Camera primaryCamera;
    private Camera secondaryCamera;

    private CameraController() {
    }

    public static CameraController getInstance() {
        return INSTANCE;
    }

    public void updatePrimaryCamera(Camera camera) {
        this.primaryCamera = camera;
    }

    public void updateSecondaryCamera(Camera camera) {
        this.secondaryCamera = camera;
    }

    public Camera getPrimaryCamera() {
        return primaryCamera;
    }

    public Camera getSecondaryCamera() {
        return secondaryCamera;
    }

    public void updateLocalCameras(Minecraft minecraft) {
        if (minecraft.player == null) {
            return;
        }

        Entity player = minecraft.player;
        if (primaryCamera == null) {
            primaryCamera = new Camera(player, false);
        }
        primaryCamera.set(player, false);

        if (SecondPlayerManager.getInstance().isActive()) {
            var second = SecondPlayerManager.getInstance().getSecondPlayer();
            if (second != null) {
                if (secondaryCamera == null) {
                    secondaryCamera = new Camera(second, false);
                }
                secondaryCamera.set(second, false);
            }
        }
    }
}
