package io.github.settingdust.dawncraftfixes.mixin.braincell;

import bottomtextdanny.braincell.mod.hooks.BCMovementHooks;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BCMovementHooks.class)
public class MixinBCMovementHooks {
    @Inject(method = "playerFallHook", at = @At("HEAD"), remap = false, cancellable = true)
    private static void dcfixes$ensureAlive(Player player, LivingFallEvent event, CallbackInfo ci) {
        if (!player.isAlive()) ci.cancel();
    }
}
