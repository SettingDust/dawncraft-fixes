package io.github.settingdust.dawncraftfixes.mixin.epicfight;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import yesman.epicfight.network.client.CPExecuteSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

@Mixin(CPExecuteSkill.class)
public class MixinCPExecuteSkill {
    @WrapWithCondition(
            method = "lambda$handle$0",
            at = @At(
                    value = "INVOKE",
                    target = "Lyesman/epicfight/world/capabilities/entitypatch/player/ServerPlayerPatch;getSkill(I)Lyesman/epicfight/skill/SkillContainer;"
            ),
            remap = false
    )
    private static boolean dcfixes$ensureAlive(ServerPlayerPatch instance, int i) {
        return instance != null;
    }
}
