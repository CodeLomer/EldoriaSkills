package com.github.codelomer.eldoriaskills.util;

import com.github.codelomer.eldoriaskills.exceptions.ConfigCreationException;
import com.github.codelomer.eldoriaskills.exceptions.ConfigLoadException;
import com.github.codelomer.eldoriaskills.exceptions.ConfigSaveException;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigFile {
    private final JavaPlugin plugin;
    private final String configPath;
    @Getter
    private FileConfiguration config;
    private File file;

    public ConfigFile(@NonNull JavaPlugin plugin, @NonNull String configPath) {

        this.plugin = plugin;
        this.configPath = configPath;
    }


    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void saveDefaultConfig() {
        file = new File(plugin.getDataFolder(), configPath);
        if (!file.exists()){
            plugin.getDataFolder().mkdirs();
            plugin.saveResource(configPath, true);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }


    public void reloadDefaultConfig(){
        if(isFileInvalid()) saveDefaultConfig();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new ConfigLoadException("Failed to load configuration", e);
        }
    }

    public void saveConfig(){
        if(isFileInvalid()) createNewFile();
        try {
            config.save(file);
        } catch (IOException e) {
            throw new ConfigSaveException("Failed to save configuration", e);
        }
    }

    public void reloadConfig(){
        if(isFileInvalid()) createNewFile();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new ConfigLoadException("Failed to reload configuration", e);
        }
    }

    public void saveAndReloadConfig(){
        saveConfig();
        reloadConfig();
    }

    private void createNewFile(){
        file = new File(plugin.getDataFolder(),configPath);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new ConfigCreationException("Failed to create new configuration file", e);
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    private boolean isFileInvalid(){
        return file == null || !file.exists() || config == null;
    }
}
