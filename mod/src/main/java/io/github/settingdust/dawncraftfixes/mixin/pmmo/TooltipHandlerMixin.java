package io.github.settingdust.dawncraftfixes.mixin.pmmo;

import harmonised.pmmo.client.events.TooltipHandler;
import io.github.settingdust.dawncraftfixes.DawncraftFixesConfig;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TooltipHandler.class)
public class TooltipHandlerMixin {
    @Inject(method = "onTooltip", at = @At("HEAD"), cancellable = true)
    private static void dcfixes$disableTooltip(final ItemTooltipEvent event, final CallbackInfo ci) {
        if (DawncraftFixesConfig.DISABLE_PMMO_TOOLTIP.get()) ci.cancel();
    }
}
