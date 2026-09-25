package com.verity;

import com.verity.client.input.XboxControllerState;
import com.verity.common.secondplayer.SecondPlayerManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "verity_splitscreen", value = Dist.CLIENT)
public final class VeritySplitMod {
    public static final String MODID = "verity_splitscreen";
    public static final SecondPlayerManager SECOND_PLAYER = SecondPlayerManager.getInstance();

    private VeritySplitMod() {
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        SECOND_PLAYER.tick();
    }

    public static void setControllerState(XboxControllerState state) {
        SECOND_PLAYER.setXboxControllerState(state);
    }
}
