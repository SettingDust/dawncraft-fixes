package io.github.settingdust.dawncraftfixes.mixin.epicfight;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.events.engine.RenderEngine;

@Mixin(RenderEngine.Events.class)
public class MixingRenderEngineEvents {
    /**
     * Fixed in newest epicfight
     * @param tooltipEvent
     * @param callbackInfo
     */
    @Inject(remap = false, method = "itemTooltip", at = @At("HEAD"), cancellable = true)
    private static void dcfixes$missingClientCheck(ItemTooltipEvent tooltipEvent, CallbackInfo callbackInfo) {
        if (tooltipEvent.getPlayer() != null && !tooltipEvent.getPlayer().isLocalPlayer()) callbackInfo.cancel();
    }
}
