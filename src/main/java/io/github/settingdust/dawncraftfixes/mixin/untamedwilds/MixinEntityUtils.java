package io.github.settingdust.dawncraftfixes.mixin.untamedwilds;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import untamedwilds.util.EntityUtils;

@Mixin(EntityUtils.class)
public class MixinEntityUtils {
    @Redirect(method = "buildTooltipData", at = @At(value = "INVOKE", target = "Ljava/lang/String;isEmpty()Z"), remap = false)
    private static boolean dcfixes$fixNullPath(String instance) {
        if (instance == null) return false;
        return instance.isEmpty();
    }

    @ModifyConstant(method = "dropEggs", constant = @Constant(stringValue = "custom_model_data"), remap = false)
    private static String dcfixes$wrongEggTag(String constant) {
        return "CustomModelData";
    }
}
