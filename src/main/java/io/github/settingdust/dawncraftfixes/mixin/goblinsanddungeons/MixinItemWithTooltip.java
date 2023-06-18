package io.github.settingdust.dawncraftfixes.mixin.goblinsanddungeons;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import superlord.goblinsanddungeons.item.GoblinCrownItem;
import superlord.goblinsanddungeons.item.HealthRingItem;
import superlord.goblinsanddungeons.item.StealthRingItem;

import javax.annotation.Nullable;
import java.util.List;

@Mixin({StealthRingItem.class, HealthRingItem.class, GoblinCrownItem.class})
public class MixinItemWithTooltip {
    @WrapOperation(
            method = "appendHoverText",
            at =
                    @At(
                            value = "INVOKE",
                            target = "Lcom/mojang/blaze3d/platform/InputConstants;isKeyDown(JI)Z",
                            ordinal = 0))
    private boolean dcfixes$ensureServer(
            long window,
            int key,
            Operation<Boolean> original,
            ItemStack p_77624_1_,
            @Nullable Level level,
            List<Component> p_77624_3_,
            TooltipFlag p_77624_4_) {
        return level != null && level.isClientSide && original.call(window, key);
    }
}
