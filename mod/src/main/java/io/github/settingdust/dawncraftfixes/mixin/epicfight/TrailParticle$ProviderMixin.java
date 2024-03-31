package io.github.settingdust.dawncraftfixes.mixin.epicfight;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.SimpleParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.client.particle.TrailParticle;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

@Mixin(TrailParticle.Provider.class)
public class TrailParticle$ProviderMixin {
    @Inject(
            remap = false,
            method =
                    "createParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDD)Lnet/minecraft/client/particle/Particle;",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lyesman/epicfight/main/EpicFightMod;getInstance()Lyesman/epicfight/main/EpicFightMod;"),
            cancellable = true)
    private void dcfixes$avoidNullPatch(
            final SimpleParticleType typeIn,
            final ClientLevel level,
            final double x,
            final double y,
            final double z,
            final double xSpeed,
            final double ySpeed,
            final double zSpeed,
            final CallbackInfoReturnable<Particle> cir,
            @Local LivingEntityPatch<?> patch) {
        if (patch == null) cir.setReturnValue(null);
    }
}
