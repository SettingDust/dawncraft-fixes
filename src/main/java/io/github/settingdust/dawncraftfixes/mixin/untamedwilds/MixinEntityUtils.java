package io.github.settingdust.dawncraftfixes.mixin.untamedwilds;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import untamedwilds.entity.ComplexMob;
import untamedwilds.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @WrapOperation(method = "getSkinFromEntity", remap = false, at = @At(value = "INVOKE", target = "Ljava/util/HashMap;containsKey(Ljava/lang/Object;)Z", ordinal = 0))
    private static boolean dcfixes$rareTextureNotExist(HashMap<String, HashMap<Integer, ArrayList<ResourceLocation>>> instance, Object variant, Operation<Boolean> original, @Local String name) {
        return instance.containsKey(name) && original.call(instance, variant);
    }

    @WrapOperation(method = "getSkinFromEntity", remap = false, at = @At(value = "INVOKE", target = "Ljava/util/HashMap;containsKey(Ljava/lang/Object;)Z", ordinal = 1))
    private static boolean dcfixes$commonTextureNotExist(HashMap<String, HashMap<Integer, ArrayList<ResourceLocation>>> instance, Object variant, Operation<Boolean> original, @Local String name) {
        return instance.containsKey(name) && original.call(instance, variant);
    }
}
