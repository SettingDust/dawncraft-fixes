package io.github.settingdust.dawncraftfixes;

import com.cursedcauldron.unvotedandshelved.entities.CopperGolemEntity;
import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("dawncraft_fixes")
public class DawncraftFixes {

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public DawncraftFixes() {
        MixinExtrasBootstrap.init();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onLivingCreated(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof Zombie zombie) {
            zombie.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(zombie, CopperGolemEntity.class, true));
        }
    }
}
