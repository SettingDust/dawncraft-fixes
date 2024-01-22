package io.github.settingdust.dawncraftfixes.mixin.epicfight;

import io.redspace.ironsspellbooks.player.ClientMagicData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.events.engine.RenderEngine;

@Mixin(value = RenderEngine.Events.class, remap = false)
public class RenderEngine$EventsMixin {
    @Inject(
            method = {"renderLivingEvent", "renderHand"},
            at = @At("HEAD"),
            cancellable = true)
    private static void dcfixes$cancelRendering(final CallbackInfo ci) {
        if (ClientMagicData.isCasting()) ci.cancel();
    }
}
