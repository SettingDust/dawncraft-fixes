package io.github.settingdust.dawncraftfixes.mixin.cataclysm;

import L_Ender.cataclysm.items.The_Incinerator;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(The_Incinerator.class)
public class The_IncineratorMixin {
    @ModifyExpressionValue(
            method = "releaseUsing",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "LL_Ender/cataclysm/items/The_Incinerator;getUseDuration(Lnet/minecraft/world/item/ItemStack;)I"))
    private int dcfixes$limitUseDuration(final int original) {
        return original;
    }
}
