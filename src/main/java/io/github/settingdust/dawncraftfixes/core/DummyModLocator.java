package io.github.settingdust.dawncraftfixes.core;

import com.google.common.collect.Lists;
import net.minecraftforge.fml.loading.EarlyLoadingException;
import net.minecraftforge.fml.loading.moddiscovery.AbstractJarFileDependencyLocator;
import net.minecraftforge.forgespi.language.IModInfo;
import net.minecraftforge.forgespi.locating.IModFile;
import net.minecraftforge.jarjar.selection.JarSelector;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.VersionRange;
import org.jetbrains.annotations.NotNull;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DummyModLocator extends AbstractJarFileDependencyLocator {

    @Override
    public List<IModFile> scanMods() {
        String path = null;
        try {
            path = getClass()
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
            path = path.substring(1, path.lastIndexOf("/"));
            if (path.lastIndexOf("#") != -1) path = path.substring(0, path.lastIndexOf("#"));
            Optional<IModFile> mod = createMod(Paths.get(path));
            return JarSelector.detectAndSelect(
                    Lists.newArrayList(mod.orElseThrow()),
                    this::loadResourceFromModFile,
                    this::loadModFileFrom,
                    this::identifyMod,
                    this::exception);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String name() {
        return "dawncraft fixes locator";
    }

    @Override
    public void initArguments(Map<String, ?> arguments) {}

    protected EarlyLoadingException exception(
            Collection<JarSelector.ResolutionFailureInformation<IModFile>> failedDependencies) {

        final List<EarlyLoadingException.ExceptionData> errors = failedDependencies.stream()
                .filter(entry -> !entry.sources().isEmpty()) // Should never be the case, but just to be sure
                .map(entry -> new EarlyLoadingException.ExceptionData(
                        getErrorTranslationKey(entry),
                        entry.identifier().group() + ":" + entry.identifier().artifact(),
                        entry.sources().stream()
                                .flatMap(this::getModWithVersionRangeStream)
                                .map(this::formatError)
                                .collect(Collectors.joining(", "))))
                .toList();

        return new EarlyLoadingException(
                failedDependencies.size() + " Dependency restrictions were not met.", null, errors);
    }

    @NotNull
    private String getErrorTranslationKey(final JarSelector.ResolutionFailureInformation<IModFile> entry) {
        return entry.failureReason() == JarSelector.FailureReason.VERSION_RESOLUTION_FAILED
                ? "fml.dependencyloading.conflictingdependencies"
                : "fml.dependencyloading.mismatchedcontaineddependencies";
    }

    @NotNull
    private Stream<ModWithVersionRange> getModWithVersionRangeStream(
            final JarSelector.SourceWithRequestedVersionRange<IModFile> file) {
        return file.sources().stream()
                .map(IModFile::getModFileInfo)
                .flatMap(modFileInfo -> modFileInfo.getMods().stream())
                .map(modInfo -> new ModWithVersionRange(modInfo, file.requestedVersionRange(), file.includedVersion()));
    }

    @NotNull
    private String formatError(final ModWithVersionRange modWithVersionRange) {
        return "\u00a7e" + modWithVersionRange.modInfo().getModId() + "\u00a7r - \u00a74"
                + modWithVersionRange.versionRange().toString() + "\u00a74 - \u00a72"
                + modWithVersionRange.artifactVersion().toString() + "\u00a72";
    }

    private record ModWithVersionRange(IModInfo modInfo, VersionRange versionRange, ArtifactVersion artifactVersion) {}
}