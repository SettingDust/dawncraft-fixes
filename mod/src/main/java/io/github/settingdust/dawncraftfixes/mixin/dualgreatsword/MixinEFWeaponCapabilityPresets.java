package io.github.settingdust.dawncraftfixes.mixin.dualgreatsword;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import reascer.efdg.gameasset.EFAnimations;
import reascer.efdg.gameasset.EFColliders;
import reascer.efdg.gameasset.EFSkills;
import reascer.efdg.world.capabilities.item.EFWeaponCapabilityPresets;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.function.Function;

@Mixin(EFWeaponCapabilityPresets.class)
public class MixinEFWeaponCapabilityPresets {

    @Final
    @Shadow(remap = false)
    @Mutable
    public static Function<Item, CapabilityItem.Builder> GREATSWORD;

    @Redirect(
            method = "<clinit>",
            at =
                    @At(
                            value = "FIELD",
                            opcode = Opcodes.PUTSTATIC,
                            target =
                                    "Lreascer/efdg/world/capabilities/item/EFWeaponCapabilityPresets;GREATSWORD:Ljava/util/function/Function;"),
            remap = false)
    private static void dcfixes$crashWithNonPlayer(Function<Item, CapabilityItem.Builder> value) {
        GREATSWORD = item -> WeaponCapability.builder()
                .category(CapabilityItem.WeaponCategories.GREATSWORD)
                .styleProvider((entityPatch) -> {
                    if (entityPatch
                                    .getHoldingItemCapability(InteractionHand.OFF_HAND)
                                    .getWeaponCategory()
                            == CapabilityItem.WeaponCategories.GREATSWORD) {
                        if (entityPatch instanceof PlayerPatch<?> playerPatch) {
                            if (playerPatch.getSkill(EFSkills.DUALGREATSWORD) != null
                                    && (playerPatch)
                                            .getSkill(EFSkills.DUALGREATSWORD)
                                            .getSkill()
                                            .getRegistryName()
                                            .getPath()
                                            .equals("dualgreatsword")) {
                                return CapabilityItem.Styles.OCHS;
                            } else return CapabilityItem.Styles.TWO_HAND;
                        } else return CapabilityItem.Styles.TWO_HAND;
                    }
                    return CapabilityItem.Styles.ONE_HAND;
                })
                .collider(EFColliders.GREATSWORD)
                .swingSound(EpicFightSounds.WHOOSH_BIG)
                .hitSound(EpicFightSounds.BLADE_HIT)
                .newStyleCombo(
                        CapabilityItem.Styles.TWO_HAND,
                        Animations.GREATSWORD_AUTO1,
                        Animations.GREATSWORD_AUTO2,
                        Animations.GREATSWORD_DASH,
                        Animations.GREATSWORD_AIR_SLASH)
                .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemstack) -> EpicFightSkills.STEEL_WHIRLWIND)
                .livingMotionModifier(
                        CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(
                        CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(
                        CapabilityItem.Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(
                        CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(
                        CapabilityItem.Styles.TWO_HAND, LivingMotions.JUMP, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(
                        CapabilityItem.Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(
                        CapabilityItem.Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(
                        CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
                .newStyleCombo(
                        CapabilityItem.Styles.OCHS,
                        EFAnimations.GREATSWORD_DUAL_AUTO_1,
                        EFAnimations.GREATSWORD_DUAL_AUTO_2,
                        EFAnimations.GREATSWORD_DUAL_AUTO_3,
                        EFAnimations.GREATSWORD_DUAL_AUTO_4,
                        EFAnimations.GREATSWORD_DUAL_DASH,
                        EFAnimations.GREATSWORD_DUAL_AIRSLASH)
                .innateSkill(CapabilityItem.Styles.OCHS, (itemstack) -> EFSkills.EARTHQUAKE)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.IDLE, EFAnimations.GREATSWORD_DUAL_IDLE)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.WALK, EFAnimations.GREATSWORD_DUAL_WALK)
                .livingMotionModifier(
                        CapabilityItem.Styles.OCHS, LivingMotions.CHASE, EFAnimations.GREATSWORD_DUAL_IDLE)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.RUN, EFAnimations.GREATSWORD_DUAL_RUN)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.JUMP, EFAnimations.GREATSWORD_DUAL_RUN)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.KNEEL, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.SNEAK, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.SWIM, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
                .weaponCombinationPredicator((entityPatch) -> {
                    if (entityPatch
                                    .getHoldingItemCapability(InteractionHand.OFF_HAND)
                                    .getWeaponCategory()
                            == CapabilityItem.WeaponCategories.GREATSWORD) {
                        if (entityPatch instanceof PlayerPatch<?> playerPatch) {
                            return playerPatch.getSkill(EFSkills.DUALGREATSWORD) != null
                                    && playerPatch
                                            .getSkill(EFSkills.DUALGREATSWORD)
                                            .getSkill()
                                            .getRegistryName()
                                            .getPath()
                                            .equals("dualgreatsword");
                        } else return true;
                    }
                    return false;
                });
    }
}
