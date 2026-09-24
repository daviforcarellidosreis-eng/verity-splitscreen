package com.verity.client.render;

import com.verity.common.secondplayer.SecondPlayerEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;

public final class SecondPlayerRenderer extends PlayerRenderer {
    public SecondPlayerRenderer(EntityRendererProvider.Context context) {
        super(context, false);
    }

    @Override
    public boolean shouldRender(AbstractClientPlayer player, net.minecraft.client.renderer.culling.Frustum frustum, double cameraX, double cameraY, double cameraZ) {
        return true;
    }
}
