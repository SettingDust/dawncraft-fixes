package io.github.settingdust.dawncraftfixes.mixin.aggrofixcrashwithsummoned;

import com.llamalad7.mixinextras.sugar.Local;
import net.blzinite.aggrofix.procedures.AggroCalcProcedure;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.PriorityQueue;

@Mixin(value = AggroCalcProcedure.class, remap = false)
public class AggroCalcProcedureMixin {
    @Inject(
            method = "onEntityAttacked",
            at = @At(value = "INVOKE", target = "Ljava/util/PriorityQueue;poll()Ljava/lang/Object;"),
            cancellable = true)
    private static void dcfixes$avoidCrash(
            final LivingHurtEvent event, final CallbackInfo ci, @Local PriorityQueue<Map.Entry<String, Double>> queue) {
        if (queue.isEmpty()) {
            ci.cancel();
        }
    }
}
