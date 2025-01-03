package com.github.codelomer.eldoriaskills.config;

import com.github.codelomer.eldoriaskills.util.ConfigFile;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public abstract class PluginConfig {

    protected ConfigFile configFile;

    public PluginConfig(@NonNull JavaPlugin plugin, @NonNull String fileName){
        configFile = new ConfigFile(plugin,fileName);
        configFile.saveDefaultConfig();
    }

    protected ConfigurationSection getOrCreateSection(@NonNull ConfigurationSection main, @NonNull String subsectionName) {
        ConfigurationSection section = main.getConfigurationSection(subsectionName);
        if (section == null) {
            section = main.createSection(subsectionName);
        }
        return section;
    }

    protected void loadSubsection(@NonNull ConfigurationSection section, @NonNull String subsectionName, @NonNull Consumer<ConfigurationSection> loader) {
        ConfigurationSection subsection = getOrCreateSection(section, subsectionName);
        loader.accept(subsection);
    }

    public abstract void loadConfig();

    public void reloadConfig(){
        configFile.saveAndReloadConfig();
        loadConfig();
    }
}
