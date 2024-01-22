package io.github.settingdust.dawncraftfixes.mixin.epicfight;

import io.redspace.ironsspellbooks.capabilities.magic.PlayerMagicData;
import io.redspace.ironsspellbooks.util.Utils;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.types.MainFrameAnimation;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(value = MainFrameAnimation.class, remap = false)
public class MainFrameAnimationMixin {
    @Inject(method = "begin(Lyesman/epicfight/world/capabilities/entitypatch/LivingEntityPatch;)V", at = @At("TAIL"))
    private void onBegin(LivingEntityPatch<?> entitypatch, CallbackInfo ci) {
        if (entitypatch instanceof PlayerPatch<?> playerPatch) {
            final var player = playerPatch.getOriginal();
            if (player instanceof ServerPlayer serverPlayer
                    && PlayerMagicData.getPlayerMagicData(player).isCasting()) {
                Utils.serverSideCancelCast(serverPlayer);
            }
        }
    }
}
