package io.github.settingdust.dawncraftfixes.mixin.shouldersurfing;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.teamderpy.shouldersurfing.client.ShoulderRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.OutlineBufferSource;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShoulderRenderer.class)
public class ShoulderRendererMixin {
    @ModifyExpressionValue(
            method = "postRenderCameraEntity",
            remap = false,
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lcom/teamderpy/shouldersurfing/client/ShoulderRenderer;shouldRenderCameraEntityTransparent(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean dcfixes$avoidCast(
            final boolean original, Entity entity, float partialTick, MultiBufferSource multiBufferSource) {
        if (original && multiBufferSource instanceof OutlineBufferSource outlineBufferSource) {
            ((OutlineBufferSourceAccess) outlineBufferSource).getBufferSource().endLastBatch();
            ((OutlineBufferSourceAccess) outlineBufferSource).getOutlineBufferSource().endLastBatch();
        }
        return original && multiBufferSource instanceof MultiBufferSource.BufferSource;
    }
}
