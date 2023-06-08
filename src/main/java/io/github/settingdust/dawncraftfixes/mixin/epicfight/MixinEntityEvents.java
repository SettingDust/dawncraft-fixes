package io.github.settingdust.dawncraftfixes.mixin.epicfight;

import net.minecraftforge.event.entity.living.LivingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.events.EntityEvents;

@Mixin(EntityEvents.class)
public class MixinEntityEvents {
    @Inject(method = "updateEvent", at = @At("HEAD"), remap = false, cancellable = true)
    private static void dcfixes$ensureAlive(LivingEvent.LivingUpdateEvent event, CallbackInfo ci) {
        if (event.getEntity().isRemoved()) ci.cancel();
    }
}
