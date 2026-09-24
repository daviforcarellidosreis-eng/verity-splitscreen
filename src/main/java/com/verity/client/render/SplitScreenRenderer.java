package com.verity.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

/**
 * Split-screen marker used while the real client camera pipeline is integrated in the
 * version-specific compatibility layer.
 */
public final class SplitScreenRenderer {
    private SplitScreenRenderer() {
    }

    public static void render(GuiGraphics graphics, float partialTick, int width, int height, Minecraft mc) {
        if (mc.player == null) {
            return;
        }

        int splitX = width / 2;

        RenderSystem.disableDepthTest();
        graphics.fill(0, 0, splitX, height, 0xFF101010);
        graphics.fill(splitX, 0, width, height, 0xFF171717);
        graphics.drawString(mc.font, "PLAYER 1", 12, 12, 0xFFFFFFFF, false);
        graphics.drawString(mc.font, "PLAYER 2", splitX + 12, 12, 0xFFFFFFFF, false);
        graphics.fill(splitX - 2, 0, splitX + 2, height, 0xFFFFFFFF);
        RenderSystem.enableDepthTest();
    }
}
