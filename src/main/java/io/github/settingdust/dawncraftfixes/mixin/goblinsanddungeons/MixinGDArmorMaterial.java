package io.github.settingdust.dawncraftfixes.mixin.goblinsanddungeons;

import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import superlord.goblinsanddungeons.common.util.GDArmorMaterial;

import java.util.function.Supplier;

@Mixin(GDArmorMaterial.class)
public class MixinGDArmorMaterial {

    @Final
    @Shadow
    private Supplier<Ingredient> repairMaterial = dcfixes$nonnullRepairMaterial();

    public Supplier<Ingredient> dcfixes$nonnullRepairMaterial() {
        return repairMaterial == null ? () -> Ingredient.EMPTY : repairMaterial;
    }
}
