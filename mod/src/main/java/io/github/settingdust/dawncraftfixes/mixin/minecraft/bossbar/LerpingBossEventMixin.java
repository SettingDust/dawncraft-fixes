package io.github.settingdust.dawncraftfixes.mixin.minecraft.bossbar;

import io.github.settingdust.dawncraftfixes.DawncraftFixesConfig;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.BossEvent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(LerpingBossEvent.class)
public class LerpingBossEventMixin extends BossEvent {

    private LerpingBossEventMixin(
            final UUID p_18849_, final Component p_18850_, final BossBarColor p_18851_, final BossBarOverlay p_18852_) {
        super(p_18849_, p_18850_, p_18851_, p_18852_);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void dcfixes$redirectName(
            final UUID p_169021_,
            final Component p_169022_,
            final float p_169023_,
            final BossBarColor p_169024_,
            final BossBarOverlay p_169025_,
            final boolean p_169026_,
            final boolean p_169027_,
            final boolean p_169028_,
            final CallbackInfo ci) {
        if (name instanceof TranslatableComponent translatable
                && !translatable.getKey().endsWith(".bossbar")
                && I18n.exists(translatable.getKey() + ".bossbar")) {
            name = new TranslatableComponent(translatable.getKey() + ".bossbar", translatable.getArgs());
        }
    }

    @Override
    public void setName(@NotNull Component name) {
        if (name instanceof TranslatableComponent translatable
                && !translatable.getKey().endsWith(".bossbar")
                && I18n.exists(translatable.getKey() + ".bossbar")) {
            name = new TranslatableComponent(translatable.getKey() + ".bossbar", translatable.getArgs());
        }
        super.setName(name);
    }
}
