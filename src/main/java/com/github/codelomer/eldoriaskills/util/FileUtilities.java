package com.github.codelomer.eldoriaskills.util;

import lombok.NonNull;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileUtilities {
    private FileUtilities() {
    }

    public static Collection<File> getAllFilesFromDirectory(@NonNull String path, @NonNull JavaPlugin plugin, @NonNull String endWith) {
        File folder = new File(plugin.getDataFolder(), path);
        if (!folder.exists() || !folder.isDirectory()) {
            return Collections.emptyList();
        }
        try (Stream<Path> pathStream = Files.walk(folder.toPath())) {
            return pathStream
                    .map(Path::toFile)
                    .filter(file -> file.exists() && file.isFile() && file.getName().endsWith(endWith))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
