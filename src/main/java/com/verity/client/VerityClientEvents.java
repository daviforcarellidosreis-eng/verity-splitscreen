package com.verity.client;

import com.verity.client.camera.CameraController;
import com.verity.client.input.LocalInputManager;
import com.verity.client.render.SplitScreenRenderer;
import com.verity.common.secondplayer.SecondPlayerManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class VerityClientEvents {
    private static final LocalInputManager INPUT = new LocalInputManager();
    private VerityClientEvents() { }

    public static void registerClientHooks() { MinecraftForge.EVENT_BUS.register(new VerityClientEvents()); }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        INPUT.tick();
        SecondPlayerManager.getInstance().setXboxControllerState(INPUT.getXboxControllerState());
        SecondPlayerManager.getInstance().tick();
    }

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level != null) SplitScreenRenderer.render(event.getGuiGraphics(), event.getPartialTick(), event.getWindow().getGuiScaledWidth(), event.getWindow().getGuiScaledHeight(), mc);
    }

    public static LocalInputManager getInputManager() { return INPUT; }
    public static CameraController getCameraController() { return CameraController.getInstance(); }
}
