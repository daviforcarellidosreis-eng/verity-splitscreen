package com.verity.client.input;

import com.verity.common.secondplayer.SecondPlayerManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;

public final class LocalInputManager {
    private final XboxControllerState xboxControllerState = new XboxControllerState();
    private boolean splitScreenEnabled;

    public LocalInputManager() {
        this.xboxControllerState.setConnected(true);
    }

    public void handleKeyboardEvent(InputEvent.Key event) {
        if (event.getKey() == 48 && event.getAction() == 1) {
            if (Minecraft.getInstance().screen == null) {
                // No-op placeholder. Selection UI can be opened later in the menu flow.
            }
        }
    }

    public XboxControllerState getXboxControllerState() {
        return xboxControllerState;
    }

    public boolean isSplitScreenEnabled() {
        return splitScreenEnabled;
    }

    public void setSplitScreenEnabled(boolean splitScreenEnabled) {
        this.splitScreenEnabled = splitScreenEnabled;
        if (!splitScreenEnabled) {
            SecondPlayerManager.getInstance().deactivate();
        }
    }

    public void activateSecondPlayer() {
        splitScreenEnabled = true;
        SecondPlayerManager.getInstance().activate();
    }
}
