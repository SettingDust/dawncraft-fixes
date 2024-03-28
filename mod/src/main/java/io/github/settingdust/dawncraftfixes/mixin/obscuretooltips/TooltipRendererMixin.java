package io.github.settingdust.dawncraftfixes.mixin.obscuretooltips;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.obscuria.obscuretooltips.tooltips.TooltipRenderer;
import net.minecraftforge.client.event.RenderTooltipEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = TooltipRenderer.class, remap = false)
public class TooltipRendererMixin {
    @WrapWithCondition(method = "onTooltip", at = @At(value = "INVOKE",
                                                      target = "Lnet/minecraftforge/client/event/RenderTooltipEvent;setCanceled(Z)V"))
    private static boolean dcfixes$avoidCancelForNonCancellable(final RenderTooltipEvent instance, final boolean b) {
        return instance.isCancelable();
    }
}
