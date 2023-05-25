package io.github.settingdust.dawncraftfixes.mixin.dannysexpansion;

import bottomtextdanny.braincell.mod._base.registry.BCStructureRegistry;
import bottomtextdanny.dannys_expansion.content.structures.klifour_spawn.KlifourSpawnStructure;
import bottomtextdanny.dannys_expansion.tables.DEStructures;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DEStructures.class)
public class MixinDEStructures {
    //    @ModifyArg(
    //            method = "<clinit>",
    //            at =
    //                    @At(
    //                            value = "INVOKE",
    //                            target =
    // "Lbottomtextdanny/braincell/mod/_base/registry/BCStructureRegistry$Builder;piece(Ljava/lang/String;Lnet/minecraft/world/level/levelgen/structure/pieces/StructurePieceType;)Lbottomtextdanny/braincell/mod/_base/registry/BCStructureRegistry$Builder;"),
    //            index = 1,
    //            remap = false)
    //    private static StructurePieceType dcfixes$fixKlifourSpawn(StructurePieceType type) {
    //        return FixKlifourSpawnHelper::dcfixes$klifourSpawnPiece;
    //    }

    @Mutable
    @Shadow(remap = false)
    @Final
    public static BCStructureRegistry<KlifourSpawnStructure> KLIFOUR_SPAWN;

    @Redirect(
            method = "<clinit>",
            remap = false,
            at =
                    @At(
                            value = "FIELD",
                            opcode = Opcodes.PUTSTATIC,
                            target =
                                    "Lbottomtextdanny/dannys_expansion/tables/DEStructures;KLIFOUR_SPAWN:Lbottomtextdanny/braincell/mod/_base/registry/BCStructureRegistry;"))
    private static <T extends StructureFeature<?>> void dcfixes$removeKlifour$disableBuild(
            BCStructureRegistry<KlifourSpawnStructure> value) {
        KLIFOUR_SPAWN = null;
    }
}
