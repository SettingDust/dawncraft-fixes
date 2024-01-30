package io.github.settingdust.dawncraftfixes.mixin.epicfight;

import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import yesman.epicfight.events.PlayerEvents;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

// https://github.com/Yesssssman/epicfightmod/issues/1528
@Mixin(value = PlayerEvents.class, remap = false)
public class PlayerEventsMixin {
    @Redirect(
            method = "itemUseStartEvent",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lyesman/epicfight/world/capabilities/item/CapabilityItem;getUseAnimation(Lyesman/epicfight/world/capabilities/entitypatch/LivingEntityPatch;)Lnet/minecraft/world/item/UseAnim;"))
    private static UseAnim dcfixes$cancelSetDuration(
            final CapabilityItem instance, final LivingEntityPatch<?> entitypatch) {
        return null;
    }
}
