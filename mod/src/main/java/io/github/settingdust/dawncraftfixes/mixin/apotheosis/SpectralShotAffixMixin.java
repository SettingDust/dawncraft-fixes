package io.github.settingdust.dawncraftfixes.mixin.apotheosis;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shadows.apotheosis.adventure.affix.effect.SpectralShotAffix;
import shadows.apotheosis.adventure.loot.LootRarity;

@Mixin(SpectralShotAffix.class)
public class SpectralShotAffixMixin {

    /**
     * <a href="https://github.com/Shadows-of-Fire/Apotheosis/issues/1269">https://github.com/Shadows-of-Fire/Apotheosis/issues/1269</a>
     */
    @Inject(
            method = "onArrowFired",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private void dcfixes$markAsGenerated(
        final ItemStack stack,
        final LootRarity rarity,
        final float level,
        final LivingEntity user,
        final AbstractArrow arrow,
        final CallbackInfo ci,
        @Local(ordinal = 1) AbstractArrow spectralArrow) {
        spectralArrow.getPersistentData().putBoolean("apoth.generated", true);
    }
}
