package io.github.settingdust.dawncraftfixes.mixin.braincell;

import bottomtextdanny.braincell.mod.capability.BCCapabilityHelper;
import bottomtextdanny.braincell.mod.capability.player.BCAccessoryModule;
import bottomtextdanny.braincell.mod.capability.player.BCPlayerCapability;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BCCapabilityHelper.class)
public class MixinBCCapabilityHelper {
    /**
     * @author SettingDust
     * @reason Avoid crashes
     */
    @Overwrite(remap = false)
    public static BCAccessoryModule accessoryModule(Player player) {
        return player.getCapability(BCPlayerCapability.TOKEN)
                .map(BCPlayerCapability::getAccessories)
                .orElse(null);
    }
}
