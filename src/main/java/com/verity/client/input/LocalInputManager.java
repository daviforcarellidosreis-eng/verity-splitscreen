package com.verity.client.input;

import com.verity.client.ui.PlayerSelectionScreen;
import com.verity.common.secondplayer.SecondPlayerManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;

/** Owns only controller input; keyboard/mouse remain owned by Minecraft's local player. */
public final class LocalInputManager {
    private final XboxControllerState controller = new XboxControllerState();

    public void tick() {
        controller.poll();
        Minecraft mc = Minecraft.getInstance();
        if (controller.isStartPressed() && mc.level != null && mc.screen == null) {
            mc.setScreen(new PlayerSelectionScreen());
        }
        if (controller.isBackPressed() && SecondPlayerManager.getInstance().isActive() && mc.screen == null) {
            mc.setScreen(new com.verity.client.ui.SecondPlayerMenuScreen());
        }
    }

    public void handleKeyboardEvent(InputEvent.Key ignored) {
        // Deliberately empty: no keyboard event is forwarded to Player 2.
    }

    public XboxControllerState getXboxControllerState() { return controller; }
    public boolean isSplitScreenEnabled() { return SecondPlayerManager.getInstance().isActive(); }
    public void activateSecondPlayer() { SecondPlayerManager.getInstance().activate(); }
    public void setSplitScreenEnabled(boolean enabled) {
        if (enabled) activateSecondPlayer(); else SecondPlayerManager.getInstance().deactivate();
    }
}
