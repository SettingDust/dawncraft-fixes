package io.github.settingdust.dawncraftfixes.mixin.dannysexpansion;

import bottomtextdanny.dannys_expansion.tables.DEStructures;
import io.github.settingdust.dawncraftfixes.dannysexpansion.FixKlifourSpawnHelper;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(DEStructures.class)
public class MixinDEStructures {
    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lbottomtextdanny/braincell/mod/_base/registry/BCStructureRegistry$Builder;piece(Ljava/lang/String;Lnet/minecraft/world/level/levelgen/structure/pieces/StructurePieceType;)Lbottomtextdanny/braincell/mod/_base/registry/BCStructureRegistry$Builder;"
            ),
            index = 1,
            remap = false
    )
    private static StructurePieceType dcfixes$fixKlifourSpawn(StructurePieceType type) {
        return FixKlifourSpawnHelper::dcfixes$klifourSpawnPiece;
    }
}
