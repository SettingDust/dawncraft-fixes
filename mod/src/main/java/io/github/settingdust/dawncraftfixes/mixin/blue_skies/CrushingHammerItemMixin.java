package io.github.settingdust.dawncraftfixes.mixin.blue_skies;

import com.legacy.blue_skies.items.tools.weapons.CrushingHammerItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CrushingHammerItem.class)
public class CrushingHammerItemMixin {
    @Redirect(
            method = "releaseUsing",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lcom/legacy/blue_skies/items/tools/weapons/CrushingHammerItem;getUseDuration(Lnet/minecraft/world/item/ItemStack;)I"))
    private int dcfixes$limitUseDuration(final CrushingHammerItem instance, final ItemStack stack) {
        return Integer.MAX_VALUE;
    }
}
