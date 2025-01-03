package com.github.codelomer.eldoriaskills.manager;

import com.github.codelomer.eldoriaskills.config.impl.RaceConfig;
import com.github.codelomer.eldoriaskills.config.impl.StatsConfig;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.plugin.java.JavaPlugin;
@Getter
public class EldoriaConfigManager {
    private final @NonNull StatsConfig statsConfig;
    private final @NonNull RaceConfig raceConfig;

    public EldoriaConfigManager(@NonNull JavaPlugin plugin) {
        statsConfig = new StatsConfig(plugin,"stats.yml");
        raceConfig = new RaceConfig(plugin,"races.yml");
    }

    public void reloadConfigs(){
        statsConfig.reloadConfig();
        raceConfig.reloadConfig();
    }


}
