package io.github.settingdust.dawncraftfixes.mixin.bygonenether;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.feature.BasaltColumnsFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BasaltColumnsFeature.class)
public class BasaltColumnsFeatureMixin {
    @TargetHandler(
            mixin = "com.izofar.bygonenether.mixin.NoBasaltColumnsInStructuresMixin",
            name = "bygonenether_noBasaltColumnsInStructures")
    @Redirect(
            method = "@MixinSquared:Handler",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/level/chunk/ChunkStatus;isOrAfter(Lnet/minecraft/world/level/chunk/ChunkStatus;)Z"))
    private boolean dcfixes$avoidSpam(ChunkStatus status, ChunkStatus other) {
        return false;
    }
}
