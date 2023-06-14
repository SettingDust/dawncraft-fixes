package io.github.settingdust.dawncraftfixes.mixin.goblinsanddungeons;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import superlord.goblinsanddungeons.item.GoblinCrownItem;
import superlord.goblinsanddungeons.item.HealthRingItem;
import superlord.goblinsanddungeons.item.StealthRingItem;

import java.util.List;

@Mixin({StealthRingItem.class, HealthRingItem.class, GoblinCrownItem.class})
public class MixinItemWithTooltip {
    @Inject(
            method = "appendHoverText",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/item/ArmorItem;appendHoverText(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Ljava/util/List;Lnet/minecraft/world/item/TooltipFlag;)V",
                            shift = At.Shift.AFTER),
            cancellable = true)
    private void dcfixes$ensureServer(
            ItemStack p_77624_1_,
            Level p_77624_2_,
            List<Component> p_77624_3_,
            TooltipFlag p_77624_4_,
            CallbackInfo ci) {
        if (!p_77624_2_.isClientSide) ci.cancel();
    }
}
