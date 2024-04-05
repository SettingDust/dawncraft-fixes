package io.github.settingdust.dawncraftfixes.mixin.spamlogging.betterfortress;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.feature.BasaltColumnsFeature;
import net.minecraft.world.level.levelgen.feature.DeltaFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = DeltaFeature.class, priority = 1500)
public class DeltaFeatureMixin {
    @TargetHandler(
            mixin = "com.yungnickyoung.minecraft.betterfortresses.mixin.NoDeltasInStructuresMixin",
            name = "betterfortresses_noDeltasInStructures")
    @Redirect(
            method = "@MixinSquared:Handler",
            at =
            @At(
                    value = "INVOKE",
                    target =
                            "Lnet/minecraft/world/level/chunk/ChunkStatus;isOrAfter(Lnet/minecraft/world/level/chunk/ChunkStatus;)Z"))
    private static boolean dcfixes$avoidSpam(ChunkStatus status, ChunkStatus other) {
        return true;
    }
}
