package io.github.settingdust.dawncraftfixes.mixin.meetyourfight;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import lykrast.meetyourfight.item.DepthStar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DepthStar.class)
public class DepthStarMixin {
    @ModifyExpressionValue(
            method = "releaseUsing",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Llykrast/meetyourfight/item/DepthStar;getUseDuration(Lnet/minecraft/world/item/ItemStack;)I"))
    private int dcfixes$constantUseDuration(final int original) {
        return Math.max(72000, original);
    }
}
