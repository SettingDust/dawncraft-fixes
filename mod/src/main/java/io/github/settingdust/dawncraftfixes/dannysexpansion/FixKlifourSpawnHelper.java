package io.github.settingdust.dawncraftfixes.dannysexpansion;

import bottomtextdanny.dannys_expansion.content.structures.klifour_spawn.KlifourSpawnPiece;
import bottomtextdanny.dannys_expansion.tables.DEStructures;
import io.github.settingdust.dawncraftfixes.mixin.dannysexpansion.AccessorScatteredFeaturePiece;
import io.github.settingdust.dawncraftfixes.mixin.dannysexpansion.InvokerKlifourSpawnPiece;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;

import static io.github.settingdust.dawncraftfixes.DawncraftFixes.LOGGER;

public class FixKlifourSpawnHelper {
    public static KlifourSpawnPiece dcfixes$klifourSpawnPiece(
            StructurePieceSerializationContext context, CompoundTag tag) {
        var box = BoundingBox.CODEC
                .parse(NbtOps.INSTANCE, tag.get("BB"))
                .resultOrPartial(LOGGER::error)
                .orElseThrow(() -> new IllegalArgumentException("Invalid boundingbox"));
        var direction = tag.getInt("O");
        var piece = InvokerKlifourSpawnPiece.dcfixes$init(
                DEStructures.KLIFOUR_SPAWN.getPiece(0),
                box.minX(),
                box.minY(),
                box.minZ(),
                box.maxX(),
                box.maxY(),
                box.maxZ(),
                direction == -1 ? null : Direction.from2DDataValue(direction));
        if (piece instanceof AccessorScatteredFeaturePiece converted) {
            converted.setWidth(tag.getInt("Width"));
            converted.setHeight(tag.getInt("Height"));
            converted.setDepth(tag.getInt("Depth"));
            converted.setHeightPosition(tag.getInt("HPos"));
        }
        return piece;
    }
}
