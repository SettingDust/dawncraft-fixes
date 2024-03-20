package io.github.settingdust.dawncraftfixes;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class DawncraftFixesConfig {
    public static final ForgeConfigSpec COMMON;

    public static final ForgeConfigSpec.BooleanValue WOM_DISABLE_BUILTIN_LOOT;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.comment("Weapon Of Minecraft").push("wom");
        WOM_DISABLE_BUILTIN_LOOT = COMMON_BUILDER.define("disable_builtin_loot", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Dual Greatsword").push("dual_greatsword");
        COMMON_BUILDER.comment("NEED RESTART");

        COMMON_BUILDER.comment("GREATSWORD_DUAL_AUTO").push("greatsword_dual_auto");

        COMMON_BUILDER.push("1");
        configDamageModifier(COMMON_BUILDER, 0.7D);
        configDamageModifier(COMMON_BUILDER, 0.7D, 1);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("2");
        configDamageModifier(COMMON_BUILDER, 1.5D);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("3");
        configDamageModifier(COMMON_BUILDER, 0.6D);
        configDamageModifier(COMMON_BUILDER, 1.0D, 1);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("4");
        configDamageModifier(COMMON_BUILDER, 1.8D);
        configAttackSpeed(COMMON_BUILDER, 0.75D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("GREATSWORD_DUAL_DASH").push("greatsword_dual_dash");
        configDamageModifier(COMMON_BUILDER, 0.2D);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("GREATSWORD_DUAL_AIRSLASH").push("greatsword_dual_airslash");
        configDamageModifier(COMMON_BUILDER, 1.8D);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("GREATSWORD_DUAL_EARTHQUAKE").push("greatsword_dual_earthquake");
        configDamageModifier(COMMON_BUILDER, 1.2D);
        configDamageModifier(COMMON_BUILDER, 2.4D, 1);
        configAttackSpeed(COMMON_BUILDER, 1.05D);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.pop();

        COMMON = COMMON_BUILDER.build();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON);
    }

    private static void configDamageModifier(ForgeConfigSpec.Builder COMMON_BUILDER, double defaultValue, int index) {
        COMMON_BUILDER.defineInRange("damage" + index, defaultValue, 0D, 100D);
    }

    private static void configDamageModifier(ForgeConfigSpec.Builder COMMON_BUILDER, double defaultValue) {
        COMMON_BUILDER.defineInRange("damage", defaultValue, 0D, 100D);
    }

    private static void configAttackSpeed(ForgeConfigSpec.Builder COMMON_BUILDER, double defaultValue) {
        COMMON_BUILDER.defineInRange("attackSpeed", defaultValue, 0D, 100D);
    }
}
