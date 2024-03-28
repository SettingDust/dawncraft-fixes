package io.github.settingdust.dawncraftfixes.core;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import net.minecraftforge.fml.loading.FMLLoader;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class DawncraftFixesTransformationService implements ITransformationService {
    @Override
    public @NotNull String name() {
        return "Dawncraft Fixes";
    }

    @Override
    public void initialize(IEnvironment environment) {}

    @Override
    public void onLoad(IEnvironment env, Set<String> otherServices) {}

    @Override
    public @NotNull List<ITransformer> transformers() {
        final var list = new ArrayList<ITransformer>();
        if (FMLLoader.getDist().isClient()) {
            list.add(new HexereiBufferSourceTransformer());
            list.add(new ObscureTooltipsAPI10Transformer());
        }
        return list;
    }

    @Override
    public Map.Entry<Set<String>, Supplier<Function<String, Optional<URL>>>> additionalClassesLocator() {
        return ITransformationService.super.additionalClassesLocator();
    }
}
