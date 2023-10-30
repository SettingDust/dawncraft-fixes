package io.github.settingdust.dawncraftfixes.mixin.questgiver;

import com.feywild.quest_giver.EventListener;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EventListener.class)
public class MixinEventListener {
    @Inject(method = "syncPlayerRenders", at = @At("HEAD"), cancellable = true, remap = false)
    private static void dcfixes$ensureAlive(ServerPlayer player, CallbackInfo ci) {
        if (player.isRemoved()) ci.cancel();
    }
}
