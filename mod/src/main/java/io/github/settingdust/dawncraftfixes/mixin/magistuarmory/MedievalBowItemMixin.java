package io.github.settingdust.dawncraftfixes.mixin.magistuarmory;

import com.llamalad7.mixinextras.sugar.Local;
import com.magistuarmory.item.MedievalBowItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MedievalBowItem.class)
public class MedievalBowItemMixin {
    @Redirect(
            method = "releaseUsing",
            at =
                    @At(
                            value = "INVOKE",
                            ordinal = 1,
                            target =
                                    "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getItemEnchantmentLevel(Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/world/item/ItemStack;)I"))
    private int dcfixes$compatEndlessQuiver(
            final Enchantment enchantment,
            final ItemStack bow,
            @Local(ordinal = 1) ItemStack arrow,
            @Local Player player) {
        return ((ArrowItem) Items.ARROW).isInfinite(arrow, bow, player) ? 1 : 0;
    }
}
