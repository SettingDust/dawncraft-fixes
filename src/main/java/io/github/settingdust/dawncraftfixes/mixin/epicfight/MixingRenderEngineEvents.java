package io.github.settingdust.dawncraftfixes.mixin.epicfight;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.events.engine.RenderEngine;

import java.util.Objects;

@Mixin(RenderEngine.Events.class)
public class MixingRenderEngineEvents {
    @Inject(remap = false, method = "itemTooltip", at = @At("HEAD"), cancellable = true)
    private static void dcfixes$missingClientCheck(ItemTooltipEvent tooltipEvent, CallbackInfo callbackInfo) {
        if (!Objects.requireNonNull(tooltipEvent.getPlayer()).isLocalPlayer()) callbackInfo.cancel();
    }
}
