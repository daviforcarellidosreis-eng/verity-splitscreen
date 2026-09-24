package com.verity.client.ui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class SecondPlayerMenuScreen extends Screen {
    public SecondPlayerMenuScreen() {
        super(Component.literal("Verity Configuration"));
    }

    @Override
    protected void init() {
        super.init();
        int cx = width / 2;
        int cy = height / 2;

        addRenderableWidget(Button.builder(Component.literal("NORMAL"), button -> {
            this.minecraft.setScreen(null);
        }).bounds(cx - 90, cy - 45, 180, 20).build());

        addRenderableWidget(Button.builder(Component.literal("IRRITADO"), button -> {
            this.minecraft.setScreen(null);
        }).bounds(cx - 90, cy - 15, 180, 20).build());

        addRenderableWidget(Button.builder(Component.literal("MONSTRO"), button -> {
            this.minecraft.setScreen(null);
        }).bounds(cx - 90, cy + 20, 180, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, "VERITY", width / 2, 40, 0xFFFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
