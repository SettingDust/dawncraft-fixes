package io.github.settingdust.dawncraftfixes.mixin.epicfight;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.entity.PartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import yesman.epicfight.api.animation.types.AttackAnimation;

@Mixin(AttackAnimation.class)
public class MixinAttackAnimation {
    @WrapOperation(method = "hurtCollidingEntities", constant = @Constant(classValue = PartEntity.class), remap = false)
    private boolean dcfixes$avoidNullTrueEntity(Object entity, Operation<Boolean> original) {
        return (!(entity instanceof PartEntity<?> part) || !(part.getParent() instanceof LivingEntity));
    }
}
