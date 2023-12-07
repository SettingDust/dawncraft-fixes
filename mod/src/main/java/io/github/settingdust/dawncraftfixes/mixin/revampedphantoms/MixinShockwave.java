package io.github.settingdust.dawncraftfixes.mixin.revampedphantoms;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import revamped_phantoms.entity.Shockwave;

@Mixin(Shockwave.class)
public abstract class MixinShockwave extends AbstractHurtingProjectile {

    private MixinShockwave(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
        super(p_36833_, p_36834_);
    }

    @Unique
    public int dcfixes$livingTick = 0;

    @Unique
    public int dcfixes$lifeTime = 180;

    @Override
    public void tick() {
        dcfixes$livingTick++;
        if (dcfixes$livingTick >= dcfixes$lifeTime || getY() >= level.getMaxBuildHeight() * 2) {
            discard();
        }
        super.tick();
    }
}
