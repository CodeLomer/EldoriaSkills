package com.github.codelomer.eldoriaskills.attributes.impl;

import com.github.codelomer.eldoriaskills.attributes.EldoriaAdvancedAttribute;
import lombok.NonNull;

import java.util.UUID;

public class AdvancedDefaultAttribute extends EldoriaAdvancedAttribute {
    private double value;
    public AdvancedDefaultAttribute(@NonNull String id, @NonNull UUID ownerUuid, String attributeName, @NonNull String formula) {
        super(id, ownerUuid, attributeName, formula);
    }

    public AdvancedDefaultAttribute(@NonNull String id, @NonNull UUID ownerUuid, @NonNull String formula) {
        super(id, ownerUuid, formula);
    }

    @Override
    protected void updateValue(double value) {
        this.value = Math.max(value,0.0);
    }

    @Override
    public double getValue() {
        return value;
    }
}
