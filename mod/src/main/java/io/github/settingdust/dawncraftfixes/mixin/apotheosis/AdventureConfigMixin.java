package io.github.settingdust.dawncraftfixes.mixin.apotheosis;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import shadows.apotheosis.adventure.AdventureConfig;

import java.util.Arrays;

@Mixin(value = AdventureConfig.class, remap = false)
public class AdventureConfigMixin {
    @ModifyExpressionValue(
            method = "load",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lshadows/placebo/config/Configuration;getStringList(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;"))
    private static String[] dcfixes$avoidCommentAndEmpty(final String[] original) {
        return Arrays.stream(original)
                .filter(it -> !it.isEmpty() && !it.startsWith("#"))
                .toArray(String[]::new);
    }
}
