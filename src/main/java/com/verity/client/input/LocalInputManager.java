package com.verity.client.input;

import com.verity.common.secondplayer.SecondPlayerManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;

/**
 * Input manager dedicated to the second local player.
 * Keyboard/mouse remain exclusively attached to the main player.
 */
public final class LocalInputManager {
    private final XboxControllerState controller = new XboxControllerState();

    public void tick() {
        controller.poll();
        if (Minecraft.getInstance().level == null) {
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
        // intentionally empty: no keyboard input is forwarded to Player 2.
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
