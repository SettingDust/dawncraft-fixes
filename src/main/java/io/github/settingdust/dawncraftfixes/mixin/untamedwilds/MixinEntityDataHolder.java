package io.github.settingdust.dawncraftfixes.mixin.untamedwilds;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import untamedwilds.util.EntityDataHolder;
import untamedwilds.util.SpeciesDataHolder;

import java.util.List;

@Mixin(EntityDataHolder.class)
public class MixinEntityDataHolder {
    @Shadow(remap = false)
    @Final
    private List<SpeciesDataHolder> speciesData;

    @Inject(method = "getName", at = @At("HEAD"), remap = false, cancellable = true)
    public void dcfixes$getName(int i, CallbackInfoReturnable<String> cir) {
        if (speciesData.size() <= i) cir.setReturnValue("");
    }
}
