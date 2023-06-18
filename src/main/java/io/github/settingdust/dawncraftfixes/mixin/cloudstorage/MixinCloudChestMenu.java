package io.github.settingdust.dawncraftfixes.mixin.cloudstorage;

import com.github.alexthe668.cloudstorage.inventory.CloudChestMenu;
import com.google.common.collect.Sets;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.settingdust.dawncraftfixes.DawncraftFixes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Set;

@Mixin(CloudChestMenu.class)
public class MixinCloudChestMenu {
    private static final Set<Item> blacklistedItems = Sets.newHashSet();

    @WrapOperation(
            method = "search",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lcom/github/alexthe668/cloudstorage/inventory/CloudChestMenu;matchesSearch(Lnet/minecraft/world/entity/player/Player;Ljava/lang/String;Lnet/minecraft/world/item/ItemStack;)Z",
                            remap = false),
            remap = false)
    private static boolean dcfixes$avoidClientTooltip(
            Player player, String search, ItemStack stack, Operation<Boolean> original) {
        var item = stack.getItem();
        if (blacklistedItems.contains(item)) return false;
        try {
            return original.call(player, search, stack);
        } catch (Exception e) {
            blacklistedItems.add(item);
            DawncraftFixes.LOGGER.debug("Can't match item " + item, e);
            return false;
        }
    }
}
