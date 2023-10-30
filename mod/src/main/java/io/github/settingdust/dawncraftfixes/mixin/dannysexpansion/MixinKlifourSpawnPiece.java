package io.github.settingdust.dawncraftfixes.mixin.dannysexpansion;

import bottomtextdanny.dannys_expansion.content.structures.klifour_spawn.KlifourSpawnPiece;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(KlifourSpawnPiece.class)
public class MixinKlifourSpawnPiece {
    @WrapOperation(
            method = "postProcess",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/level/WorldGenLevel;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;"))
    public BlockState dcfixes$fixCrashWhenGenerating(
            WorldGenLevel instance, BlockPos blockPos, Operation<BlockState> original) {
        return instance.getLevel().getBlockState(blockPos);
    }
}
