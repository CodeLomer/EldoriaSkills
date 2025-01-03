package com.github.codelomer.eldoriaskills.attributes.impl;

import com.github.codelomer.eldoriaskills.attributes.EldoriaAdvancedAttribute;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;

import java.util.UUID;
@Getter
public class AdvancedVanillaAttribute extends EldoriaAdvancedAttribute {
    private final Attribute attribute;

    public AdvancedVanillaAttribute(@NonNull String id, @NonNull UUID ownerUuid, String attributeName, @NonNull String formula, @NonNull Attribute attribute) {
        super(id, ownerUuid, attributeName, formula);
        this.attribute = attribute;
    }

    public AdvancedVanillaAttribute(@NonNull String id, @NonNull UUID ownerUuid, @NonNull String formula, @NonNull Attribute attribute) {
        super(id, ownerUuid, formula);
        this.attribute = attribute;
    }

    @Override
    protected void updateValue(double value) {
        AttributeInstance instance = getAttributeInstance();
        if(instance != null){
            String modifyId = getClass()+"-"+getId()+"-"+ getOwnerUuid();
            AttributeModifier modifier = new AttributeModifier(getOwnerUuid(),modifyId,Math.max(0.0,value),AttributeModifier.Operation.ADD_NUMBER);
            instance.addModifier(modifier);
        }
    }

    @Override
    public double getValue() {
        AttributeInstance instance = getAttributeInstance();
        if(instance != null) return instance.getValue();
        return -1;
    }
    private AttributeInstance getAttributeInstance() {
        Player player = Bukkit.getPlayer(getOwnerUuid());
        if(player != null) return player.getAttribute(attribute);
        return null;
    }
}
