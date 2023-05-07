package io.github.settingdust.dawncraftfixes.mixin.dannysexpansion;

import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ScatteredFeaturePiece.class)
public interface AccessorScatteredFeaturePiece {
    @Accessor @Mutable
    void setWidth(int width);

    @Accessor @Mutable
    void setHeight(int height);

    @Accessor @Mutable
    void setDepth(int depth);

    @Accessor @Mutable
    void setHeightPosition(int heightPosition);
}
