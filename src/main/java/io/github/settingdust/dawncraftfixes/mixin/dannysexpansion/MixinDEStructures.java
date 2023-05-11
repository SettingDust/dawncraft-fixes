package io.github.settingdust.dawncraftfixes.mixin.dannysexpansion;

import bottomtextdanny.braincell.mod._base.registry.BCStructureRegistry;
import bottomtextdanny.braincell.mod._base.registry.managing.RegistryHelper;
import bottomtextdanny.braincell.mod._base.registry.managing.Wrap;
import bottomtextdanny.dannys_expansion.tables.DEStructures;
import io.github.settingdust.dawncraftfixes.dannysexpansion.FixKlifourSpawnHelper;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DEStructures.class)
public class MixinDEStructures {
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lbottomtextdanny/braincell/mod/_base/registry/BCStructureRegistry$Builder;piece(Ljava/lang/String;Lnet/minecraft/world/level/levelgen/structure/pieces/StructurePieceType;)Lbottomtextdanny/braincell/mod/_base/registry/BCStructureRegistry$Builder;"), index = 1, remap = false)
    private static StructurePieceType dcfixes$fixKlifourSpawn(StructurePieceType type) {
        return FixKlifourSpawnHelper::dcfixes$klifourSpawnPiece;
    }

    @Redirect(method = "<clinit>", remap = false, at = @At(value = "INVOKE", target = "Lbottomtextdanny/braincell/mod/_base/registry/BCStructureRegistry$Builder;build()Lbottomtextdanny/braincell/mod/_base/registry/BCStructureRegistry;"))
    private static <T extends StructureFeature<?>> BCStructureRegistry<T> dcfixes$removeKlifour$disableBuild(BCStructureRegistry.Builder<T> instance) {
        return null;
    }

    @Redirect(method = "<clinit>", remap = false, at = @At(value = "INVOKE", target = "Lbottomtextdanny/braincell/mod/_base/registry/managing/RegistryHelper;deferWrap(Lbottomtextdanny/braincell/mod/_base/registry/managing/Wrap;)Lbottomtextdanny/braincell/mod/_base/registry/managing/Wrap;"))
    private static <T extends IForgeRegistryEntry<T>, U extends Wrap<? extends T>> U dcfixes$removeKlifour(RegistryHelper<T> instance, U sup) {
        return null;
    }
}
