package com.verity.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.verity.common.secondplayer.SecondPlayerManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

/** UI compositor. World rendering must be injected before the vanilla GUI pass; this class does not fake it with rectangles. */
public final class SplitScreenRenderer {
    private SplitScreenRenderer() { }

    public static void render(GuiGraphics graphics, float partialTick, int width, int height, Minecraft mc) {
        if (!SecondPlayerManager.getInstance().isActive()) return;
        RenderSystem.disableDepthTest();
        int x = width / 2;
        graphics.fill(x - 1, 0, x + 1, height, 0xFFFFFFFF);
        graphics.drawString(mc.font, "PLAYER 1", 8, 8, 0xFFFFFFFF, true);
        graphics.drawString(mc.font, "PLAYER 2", x + 8, 8, 0xFFFFFFFF, true);
        RenderSystem.enableDepthTest();
    }
}
