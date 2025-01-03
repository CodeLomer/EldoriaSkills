package com.github.codelomer.eldoriaskills.factory;

import com.github.codelomer.eldoriaskills.api.EldoriaAttribute;
import com.github.codelomer.eldoriaskills.api.EldoriaAttributeFactory;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EldoriaAttributeGlobalFactory {
    private final Set<EldoriaAttributeFactory> factories = new HashSet<>();


    public void register(@NonNull EldoriaAttributeFactory factory) {
        factories.add(factory);
    }

    public EldoriaAttribute createAttribute(@NonNull UUID owner, @NonNull String id) {
        EldoriaAttribute attribute = null;
        for (EldoriaAttributeFactory factory : factories) {
            attribute = factory.createAttribute(owner, id);
        }
        return attribute;
    }
}
