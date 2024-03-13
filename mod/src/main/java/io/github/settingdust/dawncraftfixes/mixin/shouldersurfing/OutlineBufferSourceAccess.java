package io.github.settingdust.dawncraftfixes.mixin.shouldersurfing;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.OutlineBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(OutlineBufferSource.class)
public interface OutlineBufferSourceAccess {
    @Accessor
    MultiBufferSource.BufferSource getBufferSource();
    @Accessor
    MultiBufferSource.BufferSource getOutlineBufferSource();

}
