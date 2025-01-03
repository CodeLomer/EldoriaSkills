package com.github.codelomer.eldoriaskills.factory;

import com.github.codelomer.eldoriaskills.api.EldoriaAttribute;
import com.github.codelomer.eldoriaskills.api.EldoriaAttributeFactory;
import com.github.codelomer.eldoriaskills.attributes.EldoriaBasicAttribute;
import com.github.codelomer.eldoriaskills.attributes.impl.AdvancedDefaultAttribute;
import com.github.codelomer.eldoriaskills.attributes.impl.AdvancedPayloadAttribute;
import com.github.codelomer.eldoriaskills.attributes.impl.AdvancedVanillaAttribute;
import com.github.codelomer.eldoriaskills.config.impl.StatsConfig;
import com.github.codelomer.eldoriaskills.module.AdvancedAttributeData;
import com.github.codelomer.eldoriaskills.module.BasicAttributeData;
import com.github.codelomer.eldoriaskills.module.PayloadAdvancedData;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.attribute.Attribute;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
@RequiredArgsConstructor
public class DefaultAttributeFactory implements EldoriaAttributeFactory {
    private static final Map<String, Attribute> vanillaAttributes = Map.of(
            "physical-strength",Attribute.GENERIC_ATTACK_DAMAGE,
            "running-speed",Attribute.GENERIC_MOVEMENT_SPEED,
            "max-health",Attribute.GENERIC_MAX_HEALTH,
            "physical-resistance",Attribute.GENERIC_ARMOR);
    private final @NonNull StatsConfig statsConfig;

    @Override
    public @Nullable EldoriaAttribute createAttribute(@NonNull UUID ownerUuid, @NonNull String id) {
        if(id.equalsIgnoreCase("payload")){
            PayloadAdvancedData payloadAdvancedData = statsConfig.getPayloadAdvancedData();
            return new AdvancedPayloadAttribute(id,ownerUuid,payloadAdvancedData.data().name(),payloadAdvancedData.data().formula(),payloadAdvancedData.settings());
        }
        AdvancedAttributeData advancedAttributeData = statsConfig.getAdvancedAttributeData(id);
        if(advancedAttributeData != null){
            Attribute attribute = vanillaAttributes.get(id.toLowerCase());
            if(attribute != null) return new AdvancedVanillaAttribute(id,ownerUuid, advancedAttributeData.name(),advancedAttributeData.formula(),attribute);
            if(id.equalsIgnoreCase("health-regen") || id.equalsIgnoreCase("sprinting-reserve") ||
                    id.equalsIgnoreCase("sprinting-drain") || id.equalsIgnoreCase("sprinting-regen")){
                return new AdvancedDefaultAttribute(id,ownerUuid, advancedAttributeData.name(),advancedAttributeData.formula());
            }
        }
        BasicAttributeData basicAttributeData = statsConfig.getBasicAttributeData(id);
        if(basicAttributeData != null){
            return new EldoriaBasicAttribute(basicAttributeData.id(),ownerUuid, basicAttributeData.name(), basicAttributeData.defaultValue());
        }
        return null;
    }
}
