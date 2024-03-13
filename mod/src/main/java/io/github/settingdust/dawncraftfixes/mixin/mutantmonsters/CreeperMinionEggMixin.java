package io.github.settingdust.dawncraftfixes.mixin.mutantmonsters;

import fuzs.mutantmonsters.world.entity.CreeperMinionEgg;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreeperMinionEgg.class)
public class CreeperMinionEggMixin {
    /**
     * <a href="https://github.com/Fuzss/mutantmonsters/commit/d69730e406e6ca65afc902e5e33a919740a1f8d2#diff-89b09597499870af0c67b5f5b6d80502a30e9255f05153692a68f5d2b0fbd613">...</a>
     */
    @Inject(
            method = "interact",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lfuzs/mutantmonsters/world/entity/CreeperMinionEgg;getTopPassenger(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/entity/Entity;",
                            shift = At.Shift.BEFORE),
            cancellable = true)
    private void dcfixes$avoidStackOverflow(
            final Player player, final InteractionHand hand, final CallbackInfoReturnable<InteractionResult> cir) {
        if (player.hasPassenger((Entity) (Object) this)) cir.setReturnValue(InteractionResult.PASS);
    }
}
