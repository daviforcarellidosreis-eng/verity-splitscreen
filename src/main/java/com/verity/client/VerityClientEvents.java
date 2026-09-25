package com.verity.client;

import com.verity.client.render.SplitScreenRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "verity_splitscreen", value = Dist.CLIENT)
public final class VerityClientEvents {
    private VerityClientEvents() {
    }

    @SubscribeEvent
    public static void onRenderGui(RenderGuiOverlayEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft == null || minecraft.player == null) {
            return;
        }

        GuiGraphics graphics = event.getGuiGraphics();
        SplitScreenRenderer.render(graphics, event.getPartialTick(), event.getWindow().getScreenWidth(), event.getWindow().getScreenHeight(), minecraft);
    }
}
