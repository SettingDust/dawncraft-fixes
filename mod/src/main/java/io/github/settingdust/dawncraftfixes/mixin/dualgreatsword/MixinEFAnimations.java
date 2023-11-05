package io.github.settingdust.dawncraftfixes.mixin.dualgreatsword;

import io.github.settingdust.dawncraftfixes.DawncraftFixesConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import reascer.efdg.gameasset.EFAnimations;

@Mixin(EFAnimations.class)
public class MixinEFAnimations {

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 0.7F, ordinal = 1), remap = false)
    private static float dcfixes$greatswordDualAuto1Damage(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_auto.1.damage")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 0.7F, ordinal = 2), remap = false)
    private static float dcfixes$greatswordDualAuto1Damage1(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_auto.1.damage1")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 1.05F, ordinal = 0), remap = false)
    private static float dcfixes$greatswordDualAuto1AttackSpeed(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_auto.1.attackSpeed")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 1.5F, ordinal = 0), remap = false)
    private static float dcfixes$greatswordDualAuto2Damage(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_auto.2.damage")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 1.05F, ordinal = 1), remap = false)
    private static float dcfixes$greatswordDualAuto2AttackSpeed(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_auto.2.attackSpeed")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 0.6F, ordinal = 0), remap = false)
    private static float dcfixes$greatswordDualAuto3Damage(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_auto.3.damage")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 1.0F, ordinal = 0), remap = false)
    private static float dcfixes$greatswordDualAuto3Damage1(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_auto.3.damage1")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 1.05F, ordinal = 2), remap = false)
    private static float dcfixes$greatswordDualAuto3AttackSpeed(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_auto.3.attackSpeed")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 1.8F, ordinal = 0), remap = false)
    private static float dcfixes$greatswordDualAuto4Damage(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_auto.4.damage")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 0.75F, ordinal = 0), remap = false)
    private static float dcfixes$greatswordDualAuto54AttackSpeed(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_auto.4.damage")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 0.2F, ordinal = 2), remap = false)
    private static float dcfixes$greatswordDualDashDamage(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_dash.damage")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 1.05F, ordinal = 3), remap = false)
    private static float dcfixes$greatswordDualDashAttackSpeed(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_dash.attackSpeed")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 1.8F, ordinal = 1), remap = false)
    private static float dcfixes$greatswordDualAirslashDamage(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_airslash.damage")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 1.05F, ordinal = 4), remap = false)
    private static float dcfixes$greatswordDualAirslashAttackSpeed(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_airslash.attackSpeed")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 1.2F, ordinal = 0), remap = false)
    private static float dcfixes$greatswordDualEarthquakeDamage(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_earthquake.damage")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 2.4F, ordinal = 0), remap = false)
    private static float dcfixes$greatswordDualEarthquakeDamage1(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_earthquake.damage1")
                .get()
                .floatValue();
    }

    @ModifyConstant(method = "build", constant = @Constant(floatValue = 1.05F, ordinal = 5), remap = false)
    private static float dcfixes$greatswordDualEarthquakeAttackSpeed(float value) {
        return DawncraftFixesConfig.COMMON
                .getValues()
                .<ForgeConfigSpec.ConfigValue<Double>>get("dual_greatsword.greatsword_dual_earthquake.attackSpeed")
                .get()
                .floatValue();
    }
}
