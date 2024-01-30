package io.github.settingdust.dawncraftfixes.mixin.meetyourfight;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import lykrast.meetyourfight.item.DepthStar;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DepthStar.class)
public class DepthStarMixin {
    @Redirect(
            method = "releaseUsing",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Llykrast/meetyourfight/item/DepthStar;getUseDuration(Lnet/minecraft/world/item/ItemStack;)I"))
    private int dcfixes$limitUseDuration(final DepthStar instance, final ItemStack stack) {
        return Integer.MAX_VALUE;
    }
}
