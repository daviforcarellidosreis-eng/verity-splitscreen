package com.verity.client;

import com.verity.client.camera.CameraController;
import com.verity.client.input.LocalInputManager;
import com.verity.client.render.SplitScreenRenderer;
import com.verity.common.secondplayer.SecondPlayerManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class VerityClientEvents {
    private static final LocalInputManager INPUT = new LocalInputManager();

    public static void registerClientHooks() {
        MinecraftForge.EVENT_BUS.register(new VerityClientEvents());
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        // Keyboard still belongs to Player 1. Xbox is handled by LocalInputManager.
        INPUT.handleKeyboardEvent(event);
    }

    @SubscribeEvent
    public void onClientLoggedIn(ClientPlayerNetworkEvent.LoggingIn event) {
        SecondPlayerManager.getInstance().reset();
    }

    @SubscribeEvent
    public void onClientLoggedOut(ClientPlayerNetworkEvent.LoggingOut event) {
        SecondPlayerManager.getInstance().deactivate();
    }

    @SubscribeEvent
    public void onGuiOverlayRegistration(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("verity_split_screen", (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
            SplitScreenRenderer.render(gui, poseStack, partialTick, screenWidth, screenHeight, Minecraft.getInstance());
        });
    }

    public static LocalInputManager getInputManager() {
        return INPUT;
    }

    public static CameraController getCameraController() {
        return CameraController.getInstance();
    }
}
