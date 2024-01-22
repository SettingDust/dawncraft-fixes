package io.github.settingdust.dawncraftfixes;

import com.cursedcauldron.unvotedandshelved.entities.CopperGolemEntity;
import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.Objects;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DawncraftFixes.MODID)
public class DawncraftFixes {
    public static final String MODID = "dawncraft_fixes";

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public DawncraftFixes() {
        if (ModList.get().isLoaded("unvotedandshelved")) MinecraftForge.EVENT_BUS.addListener(this::onLivingCreated);

        Objects.requireNonNull(DawncraftFixesConfig.COMMON);
    }

    private void onLivingCreated(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof Zombie zombie && !event.loadedFromDisk()) {
            zombie.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(zombie, CopperGolemEntity.class, true));
        }
    }
}
