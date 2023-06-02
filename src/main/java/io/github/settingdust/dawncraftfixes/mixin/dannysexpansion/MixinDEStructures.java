package io.github.settingdust.dawncraftfixes.mixin.dannysexpansion;

import bottomtextdanny.braincell.mod._base.registry.BCStructureRegistry;
import bottomtextdanny.braincell.mod._base.registry.managing.RegistryHelper;
import bottomtextdanny.braincell.mod._base.registry.managing.Wrap;
import bottomtextdanny.dannys_expansion.content.structures.klifour_spawn.KlifourSpawnStructure;
import bottomtextdanny.dannys_expansion.tables.DEStructures;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
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

    @WrapOperation(
            method = "<clinit>",
            remap = false,
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lbottomtextdanny/braincell/mod/_base/registry/managing/RegistryHelper;deferWrap(Lbottomtextdanny/braincell/mod/_base/registry/managing/Wrap;)Lbottomtextdanny/braincell/mod/_base/registry/managing/Wrap;"))
    private static Wrap<?> dcfixes$removeKlifour$disableWrap(
            RegistryHelper<StructureFeature<?>> helper, Wrap<?> value, Operation<Void> original) {
        return null;
    }

    @Redirect(
            method = "<clinit>",
            remap = false,
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lbottomtextdanny/braincell/mod/_base/registry/BCStructureRegistry$Builder;build()Lbottomtextdanny/braincell/mod/_base/registry/BCStructureRegistry;"))
    private static BCStructureRegistry<KlifourSpawnStructure> dcfixes$removeKlifour$disableBuild(BCStructureRegistry.Builder<?> builder) {
        return null;
    }
}
