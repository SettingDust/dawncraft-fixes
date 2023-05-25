package io.github.settingdust.dawncraftfixes.mixin.integrated_api;

import com.craisinlord.integrated_api.misc.maptrades.StructureSpecificMaps;
import io.github.settingdust.dawncraftfixes.DawncraftFixes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(StructureSpecificMaps.TreasureMapForEmeralds.class)
public class MixinStructureSpecificMaps {
    @Shadow(remap = false)
    @Final
    private ResourceKey<ConfiguredStructureFeature<?, ?>> destination;

    @Shadow(remap = false)
    @Final
    private TagKey<ConfiguredStructureFeature<?, ?>> destinationTag;

    @Inject(method = "getOffer", at = @At(value = "FIELD", target = "destinationTag", ordinal = 0, remap = false))
    public void dcfixes$debugDestination(Entity entity, Random random, CallbackInfoReturnable<MerchantOffer> cir) {
        DawncraftFixes.LOGGER.debug("[Integrated Api] TreasureMapForEmeralds is trying to find "
                + (destinationTag == null ? destination : destinationTag));
    }
}
