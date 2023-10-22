package io.github.settingdust.dawncraftfixes.mixin.shrines;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.silverminer.shrines.structures.spawn_criteria.HeightSpawnCriteria;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HeightSpawnCriteria.class)
public class MixinHeightSpawnCriteria {
    @WrapOperation(
            method = "getLowestY",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lcom/silverminer/shrines/structures/spawn_criteria/HeightSpawnCriteria;getCornerHeights(IIIILnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/world/level/LevelHeightAccessor;)[I"),
            remap = false)
    private int[] dcfixes$correctTheArgs(
            HeightSpawnCriteria instance,
            int x0,
            int x1,
            int z0,
            int z1,
            ChunkGenerator chunkGenerator,
            LevelHeightAccessor heightAccessor,
            Operation<int[]> original) {
        return original.call(instance, x0, z0, x1, z1, chunkGenerator, heightAccessor);
    }
}
