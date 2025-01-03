package com.github.codelomer.eldoriaskills.attributes.impl;

import com.github.codelomer.eldoriaskills.module.PayloadSettings;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;
@Getter
public class AdvancedPayloadAttribute extends AdvancedDefaultAttribute{
    private final PayloadSettings settings;

    public AdvancedPayloadAttribute(@NonNull String id, @NonNull UUID ownerUuid, @NonNull String formula, @NonNull PayloadSettings settings) {
        super(id, ownerUuid, formula);
        this.settings = settings;
    }

    public AdvancedPayloadAttribute(@NonNull String id, @NonNull UUID ownerUuid, String attributeName, @NonNull String formula, @NonNull PayloadSettings settings) {
        super(id, ownerUuid, attributeName, formula);
        this.settings = settings;
    }
}
