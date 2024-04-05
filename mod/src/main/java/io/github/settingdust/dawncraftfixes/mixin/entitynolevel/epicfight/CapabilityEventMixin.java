package io.github.settingdust.dawncraftfixes.mixin.entitynolevel.epicfight;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.events.CapabilityEvent;

@Mixin(value = CapabilityEvent.class, remap = false)
public class CapabilityEventMixin {
    @Inject(method = "attachEntityCapability", at = @At("HEAD"), cancellable = true)
    private static void dcfixes$avoidErrorWhenNoLevel(
            final AttachCapabilitiesEvent<Entity> event, final CallbackInfo ci) {
        if (event.getObject().level == null) ci.cancel();
    }
}
