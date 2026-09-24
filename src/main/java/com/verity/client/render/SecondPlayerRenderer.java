package com.verity.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;

public final class SplitScreenRenderer {
    private SplitScreenRenderer() {
    }

    public static void render(GuiGraphics guiGraphics, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight, Minecraft minecraft) {
        if (minecraft.player == null) {
            return;
        }

        int splitX = screenWidth / 2;
        int splitY = screenHeight;

        RenderSystem.disableDepthTest();
        guiGraphics.fill(0, 0, splitX, splitY, 0xFF101010);
        guiGraphics.fill(splitX, 0, screenWidth, splitY, 0xFF1A1A1A);

        // This is the place where a true split-screen render pipeline would call the level renderer
        // with two different camera states. The Forge API for this is version-specific and should be
        // implemented in the compatibility layer for 1.21.1.
        guiGraphics.drawString(minecraft.font, "PLAYER 1", 10, 10, 0xFFFFFFFF, false);
        guiGraphics.drawString(minecraft.font, "PLAYER 2", splitX + 10, 10, 0xFFFFFFFF, false);
        RenderSystem.enableDepthTest();
    }
}
