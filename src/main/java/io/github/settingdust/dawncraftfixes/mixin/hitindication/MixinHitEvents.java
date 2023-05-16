package io.github.settingdust.dawncraftfixes.mixin.hitindication;

import com.rosymaple.hitindication.event.HitEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.PrintStream;

@Mixin(HitEvents.class)
public class MixinHitEvents {
    @Redirect(method = "onAttack", remap = false, at = @At(value = "INVOKE", target = "Ljava/io/PrintStream;println(Ljava/lang/Object;)V"))
    private static void dcfixes$disableDebug(PrintStream instance, Object x) {
        // Do nothing
    }
}
