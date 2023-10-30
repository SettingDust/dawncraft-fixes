package io.github.settingdust.dawncraftfixes.mixin.hexerei;

import net.joefoxe.hexerei.Hexerei;
import net.minecraftforge.eventbus.api.IEventBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Hexerei.class, remap = false)
public class MixinHexerei {
    @Redirect(method = "loadComplete", at=@At(value = "INVOKE", target = "Lnet/minecraftforge/eventbus/api/IEventBus;register(Ljava/lang/Object;)V", ordinal = 3))
    private void dcfixes$avoidRegisterPageDrawing(IEventBus instance, Object o) {}
}
