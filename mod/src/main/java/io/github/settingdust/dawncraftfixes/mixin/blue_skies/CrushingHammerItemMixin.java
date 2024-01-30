package io.github.settingdust.dawncraftfixes.mixin.blue_skies;

import com.legacy.blue_skies.items.tools.weapons.CrushingHammerItem;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CrushingHammerItem.class)
public class CrushingHammerItemMixin {
    @ModifyExpressionValue(
            method = "releaseUsing",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lcom/legacy/blue_skies/items/tools/weapons/CrushingHammerItem;getUseDuration(Lnet/minecraft/world/item/ItemStack;)I"))
    private int dcfixes$limitUseDuration(final int original) {
        return original;
    }
}
