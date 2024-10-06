package io.github.settingdust.dawncraftfixes.mixin.gaubtlet_laser_damage;

import com.cerbon.bosses_of_mass_destruction.entity.custom.gauntlet.GauntletEntity;
import com.cerbon.bosses_of_mass_destruction.entity.custom.gauntlet.LaserAction;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.UUID;

@Mixin(LaserAction.class)
public class LaserActionMixin {
    private static final UUID LASER_DAMAGE_MODIFIER = UUID.fromString("92a72e3f-6e6f-4e8b-9ac9-e4753b4d5bdb");

    @Redirect(method = "applyLaserToEntities",
              at = @At(value = "INVOKE",
                       target = "Lcom/cerbon/bosses_of_mass_destruction/entity/custom/gauntlet/GauntletEntity;getAttributeValue(Lnet/minecraft/world/entity/ai/attributes/Attribute;)D"))
    private double dcfixes$avoidGet(final GauntletEntity instance, final Attribute attribute) {
        return 0;
    }

    @Redirect(method = "applyLaserToEntities",
              at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/world/entity/ai/attributes/AttributeInstance;setBaseValue(D)V"))
    private void dcfixes$avoidSet0(final AttributeInstance instance, final double p_22101_) {
        instance.addTransientModifier(new AttributeModifier(LASER_DAMAGE_MODIFIER, "Laser damage", 0.75, AttributeModifier.Operation.MULTIPLY_BASE));
    }

    @Redirect(method = "applyLaserToEntities",
              at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/world/entity/ai/attributes/AttributeInstance;setBaseValue(D)V"))
    private void dcfixes$avoidSet1(final AttributeInstance instance, final double p_22101_) {
        instance.removeModifier(LASER_DAMAGE_MODIFIER);
    }
}
