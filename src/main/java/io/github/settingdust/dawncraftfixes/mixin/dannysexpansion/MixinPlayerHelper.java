package io.github.settingdust.dawncraftfixes.mixin.dannysexpansion;

import bottomtextdanny.braincell.mod.capability.player.BCAccessoryModule;
import bottomtextdanny.braincell.mod.capability.player.BCPlayerCapability;
import bottomtextdanny.braincell.mod.capability.player.accessory.IAccessory;
import bottomtextdanny.dannys_expansion._base.capabilities.player.PlayerHelper;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nullable;

@Mixin(PlayerHelper.class)
public class MixinPlayerHelper {
    /**
     * @author SettingDust
     * @reason Avoid null
     */
    @Overwrite(remap = false)
    public static BCAccessoryModule braincellAccessoryModule(Player player) {
        return player.getCapability(BCPlayerCapability.TOKEN).map(BCPlayerCapability::getAccessories).orElse((null));
    }

    /**
     * @author SettingDust
     * @reason Avoid null
     */
    @Overwrite(remap = false)
    public static boolean hasAccessory(Player player, Class<? extends IAccessory> clazz) {
        var module = braincellAccessoryModule(player);
        return module != null && module.isAccessoryTypeCurrent(clazz);
    }

    /**
     * @author SettingDust
     * @reason Avoid null
     */
    @Overwrite(remap = false)
    @Nullable
    public static <A extends IAccessory> A getAccessory(Player player, Class<A> clazz) {
        var module = braincellAccessoryModule(player);
        return module == null ? null : module.getAccessoryByType(clazz);
    }
}
