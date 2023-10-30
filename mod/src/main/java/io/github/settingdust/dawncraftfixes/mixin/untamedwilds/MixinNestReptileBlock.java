package io.github.settingdust.dawncraftfixes.mixin.untamedwilds;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import untamedwilds.block.NestReptileBlock;

@Mixin(NestReptileBlock.class)
public class MixinNestReptileBlock {
    @ModifyConstant(method = "use", constant = @Constant(stringValue = "custom_model_data"))
    public String dcfixes$wrongEggTag(String constant) {
        return "CustomModelData";
    }
}
