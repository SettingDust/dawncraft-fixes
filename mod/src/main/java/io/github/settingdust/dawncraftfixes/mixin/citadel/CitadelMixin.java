package io.github.settingdust.dawncraftfixes.mixin.citadel;

import com.github.alexthe666.citadel.Citadel;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.io.BufferedReader;

@Mixin(value = Citadel.class, remap = false)
public class CitadelMixin {
    @WrapOperation(
            method = "setup",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lcom/github/alexthe666/citadel/web/WebHelper;getURLContents(Ljava/lang/String;Ljava/lang/String;)Ljava/io/BufferedReader;"))
    private BufferedReader dcfixes$disableNetwork(
            final String url,
            final String connection,
            final Operation<BufferedReader> original) {
        return null;
    }
}
