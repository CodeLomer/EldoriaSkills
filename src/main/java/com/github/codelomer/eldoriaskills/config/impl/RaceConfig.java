package com.github.codelomer.eldoriaskills.config.impl;

import com.github.codelomer.eldoriaskills.config.PluginConfig;
import com.github.codelomer.eldoriaskills.rpg.EldoriaRace;
import com.github.codelomer.eldoriaskills.rpg.EldoriaRole;
import com.github.codelomer.eldoriaskills.rpg.EvolutionBranch;
import com.github.codelomer.eldoriaskills.util.FileUtilities;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

public class RaceConfig extends PluginConfig {
    @Getter
    private Set<EldoriaRace> raceSet;
    private final JavaPlugin plugin;

    public RaceConfig(@NonNull JavaPlugin plugin, @NonNull String fileName) {
        super(plugin, fileName);
        this.plugin = plugin;
    }

    @Override
    public void loadConfig() {
        raceSet = new HashSet<>();
        ConfigurationSection configSection = configFile.getConfig();
        Map<String, EldoriaRole> roleMap = loadRolesFromFiles();

        loadRacesAndBranches(configSection, roleMap);
    }

    private Map<String, EldoriaRole> loadRolesFromFiles() {
        Map<String, EldoriaRole> roleMap = new HashMap<>();
        for (File raceFile : FileUtilities.getAllFilesFromDirectory("races", plugin, ".yml")) {
            EldoriaRole role = loadRoleFromFile(raceFile);
            if (role != null) {
                roleMap.put(role.getId(), role);
            }
        }
        return roleMap;
    }

    private void loadRacesAndBranches(ConfigurationSection configSection, Map<String, EldoriaRole> roleMap) {
        for (String raceKey : configSection.getKeys(false)) {
            EldoriaRace race = createRace(configSection, raceKey);
            loadEvolutionBranches(configSection, raceKey, race, roleMap);
            raceSet.add(race);
        }
    }

    private EldoriaRace createRace(ConfigurationSection configSection, String raceKey) {
        String raceName = configSection.getString(raceKey + ".name");
        List<String> raceDescriptions = configSection.getStringList(raceKey + ".description");
        return new EldoriaRace(raceKey, raceName, raceDescriptions);
    }

    private void loadEvolutionBranches(ConfigurationSection configSection, String raceKey, EldoriaRace race, Map<String, EldoriaRole> roleMap) {
        ConfigurationSection evolutionSection = getOrCreateSection(configSection, raceKey + ".evolution-three");
        for (String fromRoleId : evolutionSection.getKeys(false)) {
            EldoriaRole fromRole = roleMap.get(fromRoleId);
            if (fromRole == null) continue;

            loadBranchForRole(configSection, raceKey, fromRole, fromRoleId, roleMap,race);
        }
    }

    private void loadBranchForRole(ConfigurationSection configSection, String raceKey, EldoriaRole fromRole, String fromRoleId, Map<String, EldoriaRole> roleMap, EldoriaRace race) {
        ConfigurationSection branchSection = getOrCreateSection(configSection, raceKey + ".evolution-three." + fromRoleId + ".to");
        for (String toRoleId : branchSection.getKeys(false)) {
            EldoriaRole toRole = roleMap.get(toRoleId);
            if (toRole == null) continue;

            double requiredEvolutionPoints = branchSection.getDouble(toRoleId);
            race.addBranch(new EvolutionBranch(fromRole, toRole, requiredEvolutionPoints));
        }
    }

    private EldoriaRole loadRoleFromFile(File file) {
        ConfigurationSection fileSection = YamlConfiguration.loadConfiguration(file);

        String roleId = fileSection.getString("id");
        if (roleId == null) return null;

        String roleName = fileSection.getString("name");
        List<String> roleDescriptions = fileSection.getStringList("description");

        Map<String, Double> attributeBonuses = loadAttributeBonuses(fileSection);

        return new EldoriaRole(roleId, attributeBonuses, roleName, roleDescriptions);
    }

    private Map<String, Double> loadAttributeBonuses(ConfigurationSection fileSection) {
        Map<String, Double> attributeBonuses = new HashMap<>();
        ConfigurationSection basicAttributesSection = getOrCreateSection(fileSection, "basics-attributes");
        for (String attributeKey : basicAttributesSection.getKeys(false)) {
            double attributeValue = basicAttributesSection.getDouble(attributeKey);
            attributeBonuses.put(attributeKey, attributeValue);
        }
        return attributeBonuses;
    }
}
