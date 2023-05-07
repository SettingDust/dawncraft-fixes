package io.github.settingdust.dawncraftfixes.mixin.dannysexpansion;

import bottomtextdanny.dannys_expansion.content.structures.klifour_spawn.KlifourSpawnPiece;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(KlifourSpawnPiece.class)
public interface InvokerKlifourSpawnPiece {
    @Invoker("<init>")
    static KlifourSpawnPiece dcfixes$init(StructurePieceType p_163188_, int p_163189_, int p_163190_, int p_163191_, int p_163192_, int p_163193_, int p_163194_, Direction p_163195_) {
        throw new AssertionError();
    }
}
