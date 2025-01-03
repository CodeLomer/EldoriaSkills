package com.github.codelomer.eldoriaskills.config.impl;

import com.github.codelomer.eldoriaskills.config.PluginConfig;
import com.github.codelomer.eldoriaskills.module.*;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StatsConfig extends PluginConfig {
    private final static List<String> advancedAttributeKeys = List.of(
            "physical-strength", "running-speed", "max-health", "health-regen",
            "sprinting-reserve", "sprinting-regen", "sprinting-drain", "physical-resistance", "payload"
    );

    private Map<String, BasicAttributeData> basicAttributeDataById;
    private Map<String, AdvancedAttributeData> advancedAttributeDataById;

    @Getter
    private PayloadAdvancedData payloadAdvancedData;

    public StatsConfig(@NonNull JavaPlugin plugin, @NonNull String fileName) {
        super(plugin, fileName);
    }

    @Override
    public void loadConfig() {
        basicAttributeDataById = new HashMap<>();
        advancedAttributeDataById = new HashMap<>();
        ConfigurationSection section = configFile.getConfig();

        loadBasicAttributes(section);
        loadAdvancedAttributes(section);
    }

    private void loadBasicAttributes(@NonNull ConfigurationSection section) {
        ConfigurationSection basicAttributesSection = section.getConfigurationSection("basic-attributes");
        if (basicAttributesSection != null) {
            basicAttributesSection.getKeys(false).forEach(id -> {
                BasicAttributeData basicAttributeData = createBasicAttributeData(basicAttributesSection, id);
                basicAttributeDataById.put(basicAttributeData.id(), basicAttributeData);
            });
        }
    }

    private BasicAttributeData createBasicAttributeData(ConfigurationSection section, String id) {
        double defaultValue = section.getDouble(id + ".default");
        String name = section.getString(id + ".name");
        return new BasicAttributeData(id, name, defaultValue);
    }

    private void loadAdvancedAttributes(@NonNull ConfigurationSection section) {
        ConfigurationSection advancedAttributesSection = section.getConfigurationSection("advanced-attributes");
        if (advancedAttributesSection != null) {
            advancedAttributeKeys.forEach(key -> loadAdvancedAttribute(advancedAttributesSection, key));
        }
    }

    private void loadAdvancedAttribute(@NonNull ConfigurationSection section, @NonNull String advancedAttributeKey) {
        AdvancedAttributeData advancedAttributeData = loadAdvancedAttributeData(section, advancedAttributeKey);
        if (advancedAttributeKey.equalsIgnoreCase("payload")) {
            payloadAdvancedData = createPayloadAdvancedData(section, advancedAttributeData);
        } else {
            advancedAttributeDataById.put(advancedAttributeData.id(), advancedAttributeData);
        }
    }

    private AdvancedAttributeData loadAdvancedAttributeData(@NonNull ConfigurationSection section, @NonNull String key) {
        String name = section.getString(key + ".name");
        String formula = section.getString(key + ".formula", "0+0");
        return new AdvancedAttributeData(key, formula, name);
    }

    private PayloadAdvancedData createPayloadAdvancedData(@NonNull ConfigurationSection section, AdvancedAttributeData advancedAttributeData) {
        PayloadSettings payloadSettings = loadPayloadSettings(section);
        return new PayloadAdvancedData(advancedAttributeData, payloadSettings);
    }

    private PayloadSettings loadPayloadSettings(@NonNull ConfigurationSection section) {
        double defaultWeight = section.getDouble("payload.settings.default-weight");
        PayloadSettings payloadSettings = new PayloadSettings();
        payloadSettings.setDefaultWeight(defaultWeight);

        ConfigurationSection payloadItemSection = getOrCreateSection(section, "payload.settings.others");
        if (payloadItemSection != null) {
            payloadItemSection.getKeys(false).forEach(key -> loadPayloadItem(payloadItemSection, key, payloadSettings));
        }
        return payloadSettings;
    }

    private void loadPayloadItem(ConfigurationSection payloadItemSection, String key, PayloadSettings payloadSettings) {
        double weight = payloadItemSection.getDouble(key + ".weight");
        ItemStack itemStack = payloadItemSection.getItemStack(key + ".item");
        if (itemStack != null && weight > 0.0) {
            PayloadItem payloadItem = new PayloadItem(key, itemStack, weight);
            payloadSettings.addPayloadItem(payloadItem);
        }
    }

    public BasicAttributeData getBasicAttributeData(@NonNull String id) {
        return basicAttributeDataById.get(id.toLowerCase(Locale.ENGLISH));
    }

    public AdvancedAttributeData getAdvancedAttributeData(@NonNull String id) {
        return advancedAttributeDataById.get(id.toLowerCase(Locale.ENGLISH));
    }
}
