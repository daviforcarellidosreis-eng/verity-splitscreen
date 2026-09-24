package com.verity;

import com.verity.client.VerityClientEvents;
import com.verity.common.secondplayer.SecondPlayerEntity;
import com.verity.common.secondplayer.SecondPlayerManager;
import com.verity.compat.forge1211.Forge1211Compat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(VeritySplitMod.MODID)
public final class VeritySplitMod {
    public static final String MODID = "veritysplitscreen";
    public static final String NAME = "Verity SplitScreen";

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<SecondPlayerEntity>> SECOND_PLAYER =
            ENTITY_TYPES.register(
                    "second_player",
                    () -> EntityType.Builder.of(SecondPlayerEntity::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.8F)
                            .clientTrackingRange(32)
                            .updateInterval(1)
                            .build(ResourceLocation.fromNamespaceAndPath(MODID, "second_player"))
            );

    public static VeritySplitMod INSTANCE;

    public VeritySplitMod() {
        INSTANCE = this;
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITY_TYPES.register(modBus);
        modBus.addListener(this::onClientSetup);

        Forge1211Compat.applyVersionSpecificSetup();
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        SecondPlayerManager.getInstance().setClientReady(true);
        VerityClientEvents.registerClientHooks();
    }
}
