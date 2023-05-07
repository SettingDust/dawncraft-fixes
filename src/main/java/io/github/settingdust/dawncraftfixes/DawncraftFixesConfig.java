package io.github.settingdust.dawncraftfixes;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class DawncraftFixesConfig {
    public static ForgeConfigSpec COMMON;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.comment("Dual Greatsword").push("dual_greatsword");
        COMMON_BUILDER.comment("NEED RESTART");


        COMMON_BUILDER.comment("GREATSWORD_TWOHAND_AUTO").push("greatsword_twohand_auto");

        COMMON_BUILDER.push("1");
        configDamage(COMMON_BUILDER, 0.6F);
        configAttackSpeed(COMMON_BUILDER, 1.05F);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("2");
        configDamage(COMMON_BUILDER, 0.6F);
        configAttackSpeed(COMMON_BUILDER, 1.05F);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("3");
        configDamage(COMMON_BUILDER, 0.6F);
        configAttackSpeed(COMMON_BUILDER, 1.05F);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.pop();


        COMMON_BUILDER.comment("GREATSWORD_DUAL_AUTO").push("greatsword_dual_auto");

        COMMON_BUILDER.push("1");
        configDamage(COMMON_BUILDER, 0.55F);
        configDamage(COMMON_BUILDER, 0.65F, 1);
        configAttackSpeed(COMMON_BUILDER, 1.05F);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("2");
        configDamage(COMMON_BUILDER, 0.8F);
        configAttackSpeed(COMMON_BUILDER, 1.05F);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("3");
        configDamage(COMMON_BUILDER, 0.7F);
        configDamage(COMMON_BUILDER, 0.7F, 1);
        configAttackSpeed(COMMON_BUILDER, 1.05F);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("4");
        configDamage(COMMON_BUILDER, 1.4F);
        configAttackSpeed(COMMON_BUILDER, 0.75F);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.pop();


        COMMON_BUILDER.comment("GREATSWORD_DUAL_DASH").push("greatsword_dual_dash");
        configDamage(COMMON_BUILDER, 0.2F);
        configAttackSpeed(COMMON_BUILDER, 1.05F);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("GREATSWORD_DUAL_AIRSLASH").push("greatsword_dual_airslash");
        configDamage(COMMON_BUILDER, 1.2F);
        configAttackSpeed(COMMON_BUILDER, 1.05F);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("GREATSWORD_DUAL_EARTHQUAKE").push("greatsword_dual_earthquake");
        configDamage(COMMON_BUILDER, 1.2F);
        configDamage(COMMON_BUILDER, 2.4F, 1);
        configAttackSpeed(COMMON_BUILDER, 1.05F);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.pop();

        COMMON = COMMON_BUILDER.build();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON);
    }

    private static void configDamage(ForgeConfigSpec.Builder COMMON_BUILDER, float defaultValue, int index) {
        COMMON_BUILDER.defineInRange("damage" + index, defaultValue, 0F, 100F);
    }

    private static void configDamage(ForgeConfigSpec.Builder COMMON_BUILDER, float defaultValue) {
        COMMON_BUILDER.defineInRange("damage", defaultValue, 0F, 100F);
    }

    private static void configAttackSpeed(ForgeConfigSpec.Builder COMMON_BUILDER, float defaultValue) {
        COMMON_BUILDER.defineInRange("attackSpeed", defaultValue, 0F, 100F);
    }
}
