package com.github.codelomer.eldoriaskills;


import com.github.codelomer.eldoriaskills.events.PluginReloadedEvent;
import com.github.codelomer.eldoriaskills.manager.EldoriaConfigManager;
import com.github.codelomer.eldoriaskills.registry.EldoriaRaceRegistry;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class EldoriaSkills extends JavaPlugin {
    @Getter
    private static EldoriaSkills instance;
    private final EldoriaConfigManager configManager = new EldoriaConfigManager(this);
    @Getter
    private final EldoriaRaceRegistry raceRegistry = new EldoriaRaceRegistry(configManager.getRaceConfig());



    @Override
    public void onEnable() {
        instance = this;
    }
    @Override
    public void onDisable() {

    }

    public void reloadPlugin(){
        configManager.reloadConfigs();
        raceRegistry.reload();
        PluginReloadedEvent reloadedEvent = new PluginReloadedEvent(this);
        Bukkit.getPluginManager().callEvent(reloadedEvent);
    }


}
