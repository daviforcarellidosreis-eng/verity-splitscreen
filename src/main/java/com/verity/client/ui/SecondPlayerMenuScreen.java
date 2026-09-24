package com.verity.client.ui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class PlayerSelectionScreen extends Screen {
    public PlayerSelectionScreen() {
        super(Component.literal("Player / Verity"));
    }

    @Override
    protected void init() {
        super.init();
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addRenderableWidget(Button.builder(Component.literal("PLAYER"), p -> {
            // Player 2 selected as a real second player.
            this.minecraft.setScreen(null);
        }).bounds(centerX - 150, centerY - 20, 120, 20).build());

        this.addRenderableWidget(Button.builder(Component.literal("VERITY"), p -> {
            // Verity mode selected.
            this.minecraft.setScreen(new SecondPlayerMenuScreen());
        }).bounds(centerX + 30, centerY - 20, 120, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.translate(this.width / 2.0D, this.height / 2.0D - 60.0D, 0.0D);
        guiGraphics.drawCenteredString(this.font, "PLAYER", 0, 0, 0xFFFFFFFF);
        guiGraphics.drawCenteredString(this.font, "VERITY", 0, 0, 0xFFFFFFFF);
        poseStack.popPose();
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
