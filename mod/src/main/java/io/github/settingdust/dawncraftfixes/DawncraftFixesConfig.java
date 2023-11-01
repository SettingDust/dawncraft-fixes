package io.github.settingdust.dawncraftfixes;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class DawncraftFixesConfig {
    public static final ForgeConfigSpec COMMON;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.comment("Dual Greatsword").push("dual_greatsword");
        COMMON_BUILDER.comment("NEED RESTART");

        COMMON_BUILDER.comment("GREATSWORD_TWOHAND_AUTO").push("greatsword_twohand_auto");

        COMMON_BUILDER.push("1");
        configDamage(COMMON_BUILDER, 0.6D);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("2");
        configDamage(COMMON_BUILDER, 0.6D);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("3");
        configDamage(COMMON_BUILDER, 0.6D);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("GREATSWORD_DUAL_AUTO").push("greatsword_dual_auto");

        COMMON_BUILDER.push("1");
        configDamage(COMMON_BUILDER, 0.55D);
        configDamage(COMMON_BUILDER, 0.65D, 1);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("2");
        configDamage(COMMON_BUILDER, 0.8D);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("3");
        configDamage(COMMON_BUILDER, 0.7D);
        configDamage(COMMON_BUILDER, 0.7D, 1);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("4");
        configDamage(COMMON_BUILDER, 1.4D);
        configAttackSpeed(COMMON_BUILDER, 0.75D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("GREATSWORD_DUAL_DASH").push("greatsword_dual_dash");
        configDamage(COMMON_BUILDER, 0.2D);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("GREATSWORD_DUAL_AIRSLASH").push("greatsword_dual_airslash");
        configDamage(COMMON_BUILDER, 1.2D);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("GREATSWORD_DUAL_EARTHQUAKE").push("greatsword_dual_earthquake");
        configDamage(COMMON_BUILDER, 1.2D);
        configDamage(COMMON_BUILDER, 2.4D, 1);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.pop();

        COMMON = COMMON_BUILDER.build();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON);
    }

    private static void configDamage(ForgeConfigSpec.Builder COMMON_BUILDER, double defaultValue, int index) {
        COMMON_BUILDER.defineInRange("damage" + index, defaultValue, 0D, 100D);
    }

    private static void configDamage(ForgeConfigSpec.Builder COMMON_BUILDER, double defaultValue) {
        COMMON_BUILDER.defineInRange("damage", defaultValue, 0D, 100D);
    }

    private static void configAttackSpeed(ForgeConfigSpec.Builder COMMON_BUILDER, double defaultValue) {
        COMMON_BUILDER.defineInRange("attackSpeed", defaultValue, 0D, 100D);
    }
}
