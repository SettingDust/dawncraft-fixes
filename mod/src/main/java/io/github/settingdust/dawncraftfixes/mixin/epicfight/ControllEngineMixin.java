package io.github.settingdust.dawncraftfixes.mixin.epicfight;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.client.events.engine.ControllEngine;

@Mixin(ControllEngine.class)
public class ControllEngineMixin {
    @Inject(method = "isKeyDown", at = @At("HEAD"), cancellable = true)
    private void dcfixes$ensureThread(final KeyMapping key, final CallbackInfoReturnable<Boolean> cir) {
        if (!RenderSystem.isOnRenderThread()) cir.setReturnValue(false);
    }
}
