package com.verity.client.input;

import com.verity.common.secondplayer.SecondPlayerManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;

/**
 * Handles only the second-player input path. The main player continues using keyboard/mouse.
 */
public final class LocalInputManager {
    private final XboxControllerState controller = new XboxControllerState();

    public void tick() {
        controller.poll();
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) {
            return;
        }

        if (controller.isStartPressed()) {
            SecondPlayerManager.getInstance().activate();
        }

        if (controller.isBackPressed()) {
            SecondPlayerManager.getInstance().deactivate();
        }
    }

    public void handleKeyboardEvent(InputEvent.Key ignored) {
        // Keyboard is intentionally never redirected to Player 2.
    }

    public XboxControllerState getXboxControllerState() {
        return controller;
    }

    public void activateSecondPlayer() {
        SecondPlayerManager.getInstance().activate();
    }

    public void setSplitScreenEnabled(boolean enabled) {
        if (enabled) {
            SecondPlayerManager.getInstance().activate();
        } else {
            SecondPlayerManager.getInstance().deactivate();
        }
    }
}
