package io.github.settingdust.dawncraftfixes.core;

import net.minecraftforge.fml.loading.moddiscovery.JarInJarDependencyLocator;
import net.minecraftforge.forgespi.locating.IModFile;
import org.apache.commons.lang3.SystemUtils;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class DummyModLocator extends JarInJarDependencyLocator {

    @Override
    public List<IModFile> scanMods() {
        String path;
        try {
            path = getClass()
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        path = SystemUtils.IS_OS_WINDOWS
                ? path.substring(1, path.lastIndexOf("/"))
                : path.substring(0, path.lastIndexOf("/"));
        if (path.lastIndexOf("#") != -1) path = path.substring(0, path.lastIndexOf("#"));
        Optional<IModFile> mod = createMod(Paths.get(path));
        return super.scanMods(List.of(mod.orElseThrow()));
    }

    @Override
    public String name() {
        return "dawncraft fixes locator";
    }
}
