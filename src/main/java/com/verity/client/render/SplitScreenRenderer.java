package com.verity.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public final class SplitScreenRenderer {
    private SplitScreenRenderer() {
    }

    public static void render(GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight, Minecraft minecraft) {
        if (minecraft.player == null) {
            return;
        }

        int splitX = screenWidth / 2;

        RenderSystem.disableDepthTest();
        guiGraphics.fill(0, 0, splitX, screenHeight, 0xFF101010);
        guiGraphics.fill(splitX, 0, screenWidth, screenHeight, 0xFF1A1A1A);
        guiGraphics.drawString(minecraft.font, "PLAYER 1", 12, 12, 0xFFFFFFFF, false);
        guiGraphics.drawString(minecraft.font, "PLAYER 2", splitX + 12, 12, 0xFFFFFFFF, false);
        RenderSystem.enableDepthTest();
    }
}
