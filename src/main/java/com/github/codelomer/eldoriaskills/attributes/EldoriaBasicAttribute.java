package com.github.codelomer.eldoriaskills.attributes;

import com.github.codelomer.eldoriaskills.api.EldoriaAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.attribute.AttributeModifier;

import java.util.*;

@RequiredArgsConstructor
@AllArgsConstructor
public class EldoriaBasicAttribute implements EldoriaAttribute {
    private final @NonNull String id;
    private final @NonNull UUID ownerUuid;
    private String attributeName;
    @Getter
    private double baseValue;

    private final Collection<AttributeModifier> modifiers = new HashSet<>();

    @Override
    @NonNull public UUID getOwnerUuid() {
        return ownerUuid;
    }

    @Override
    public String getAttributeName() {
        return attributeName;
    }

    @Override
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    public @NonNull String getId() {
        return id;
    }

    @Override
    public double getValue() {
        //TODO sum modifiers
        return baseValue;
    }

    public void addModifier(@NonNull AttributeModifier modifier) {
        modifiers.add(modifier);
    }
    public void removeModifier(@NonNull AttributeModifier modifier) {
        modifiers.remove(modifier);
    }

    public Collection<AttributeModifier> getModifiers() { return Collections.unmodifiableCollection(modifiers); }
}
