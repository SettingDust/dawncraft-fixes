package io.github.settingdust.dawncraftfixes.mixin.unvoted;

import com.cursedcauldron.unvotedandshelved.events.MobEvents;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MobEvents.class)
public class MixinMobEvents {
    /**
     * @author SettingDust
     * @reason Wrong event. Causing leaking
     */
    @Overwrite(remap = false)
    @SubscribeEvent
    public void onLivingCreated(LivingSpawnEvent event) {
    }
}
