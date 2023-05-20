package io.github.settingdust.dawncraftfixes.mixin.untamedwilds;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import untamedwilds.util.EntityUtils;

@Mixin(EntityUtils.class)
public class MixinEntityUtils {
    @Redirect(method = "buildTooltipData", at = @At(value = "INVOKE", target = "Ljava/lang/String;isEmpty()Z"), remap = false)
    private static boolean dcfixes$fixNullPath(String instance) {
        if (instance == null) return false;
        return instance.isEmpty();
    }
}
