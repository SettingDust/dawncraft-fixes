package io.github.settingdust.dawncraftfixes.mixin.callablehorses;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import tschipp.callablehorses.client.gui.GuiStatViewer;

@Mixin(GuiStatViewer.class)
public class MixinGuiStatViewer {
    @ModifyConstant(remap = false, method ="<clinit>", constant = @Constant(stringValue = "setSwag"))
    private static String dcfixes$wrongNameOfSetSwag(String constant) {
        return "m_30771_";
    }
}
