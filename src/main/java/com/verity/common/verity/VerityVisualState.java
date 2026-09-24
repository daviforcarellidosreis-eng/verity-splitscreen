package com.verity.client.ui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class SecondPlayerMenuScreen extends Screen {
    public SecondPlayerMenuScreen() {
        super(Component.literal("Verity Config"));
    }

    @Override
    protected void init() {
        super.init();
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addRenderableWidget(Button.builder(Component.literal("Normal"), button -> {
            this.minecraft.setScreen(null);
        }).bounds(centerX - 90, centerY - 20, 180, 20).build());

        this.addRenderableWidget(Button.builder(Component.literal("Irritated"), button -> {
            this.minecraft.setScreen(null);
        }).bounds(centerX - 90, centerY + 10, 180, 20).build());

        this.addRenderableWidget(Button.builder(Component.literal("Monster"), button -> {
            this.minecraft.setScreen(null);
        }).bounds(centerX - 90, centerY + 40, 180, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, "VERITY", this.width / 2, 30, 0xFFFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
