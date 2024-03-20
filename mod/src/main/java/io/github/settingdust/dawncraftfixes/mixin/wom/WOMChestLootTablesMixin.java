package io.github.settingdust.dawncraftfixes.mixin.wom;

import io.github.settingdust.dawncraftfixes.DawncraftFixesConfig;
import net.minecraftforge.event.LootTableLoadEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import reascer.wom.world.loot.WOMChestLootTables;

@Mixin(WOMChestLootTables.class)
public class WOMChestLootTablesMixin {
    @Inject(method = "modifyVanillaLootPools", remap = false, at = @At("HEAD"), cancellable = true)
    private static void dcfixes$avoidBuiltin(final LootTableLoadEvent event, final CallbackInfo ci) {
        if (DawncraftFixesConfig.WOM_DISABLE_BUILTIN_LOOT.get()) ci.cancel();
    }
}
