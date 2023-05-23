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
    @Shadow
    @Final
    private ResourceKey<ConfiguredStructureFeature<?, ?>> destination;
    @Shadow
    @Final
    private TagKey<ConfiguredStructureFeature<?, ?>> destinationTag;

    @Inject(method = "getOffer", at = @At(value = "FIELD", target = "Lcom/craisinlord/integrated_api/misc/maptrades/StructureSpecificMaps$TreasureMapForEmeralds;destinationTag:Lnet/minecraft/tags/TagKey;", ordinal = 0))
    public void dcfixes$debugDestination(Entity entity, Random random, CallbackInfoReturnable<MerchantOffer> cir) {
        DawncraftFixes.LOGGER.debug("[Integrated Api] TreasureMapForEmeralds is trying to find " + (destinationTag == null ? destination : destinationTag));
    }
}
