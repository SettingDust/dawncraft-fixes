package io.github.settingdust.dawncraftfixes.mixin.rei;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import harmonised.pmmo.events.TooltipHandler;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.impl.client.search.argument.type.TooltipArgumentType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = TooltipArgumentType.class, remap = false)
public class TooltipArgumentTypeMixin {
    @WrapOperation(
            method = "cacheData(Lme/shedaniel/rei/api/common/entry/EntryStack;)Ljava/lang/String;",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lme/shedaniel/rei/impl/client/search/argument/type/TooltipArgumentType;tryGetEntryStackTooltip(Lme/shedaniel/rei/api/common/entry/EntryStack;I)Ljava/lang/String;"))
    private @Nullable String dcfixes$avoidPMMOTooltip(
            final EntryStack<?> entry, final int joiner, final Operation<String> original) {
        TooltipHandler.tooltipOn = false;
        var result = original.call(entry, joiner);
        TooltipHandler.tooltipOn = true;
        return result;
    }
}
