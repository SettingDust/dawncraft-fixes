package io.github.settingdust.dawncraftfixes.mixin.configured;

import com.mrcrayfish.configured.impl.forge.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ForgeConfig.class, remap = false)
public class ForgeConfigMixin {
    @Shadow
    @Final
    protected ForgeConfigSpec spec;

    @Redirect(
            method = "getRoot",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraftforge/fml/config/ModConfig;getSpec()Lnet/minecraftforge/fml/config/IConfigSpec;"))
    private IConfigSpec<?> dcfixes$returnCorrectSpec(final ModConfig instance) {
        return spec;
    }
}
