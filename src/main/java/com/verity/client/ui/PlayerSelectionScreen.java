package com.verity.client.ui;

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
        int cx = width / 2;
        int cy = height / 2;

        addRenderableWidget(Button.builder(Component.literal("PLAYER"), button -> {
            this.minecraft.setScreen(null);
        }).bounds(cx - 150, cy - 20, 120, 20).build());

        addRenderableWidget(Button.builder(Component.literal("VERITY"), button -> {
            this.minecraft.setScreen(new SecondPlayerMenuScreen());
        }).bounds(cx + 30, cy - 20, 120, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, "PLAYER", width / 2 - 90, height / 2 - 55, 0xFFFFFFFF);
        guiGraphics.drawCenteredString(this.font, "VERITY", width / 2 + 90, height / 2 - 55, 0xFFFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
