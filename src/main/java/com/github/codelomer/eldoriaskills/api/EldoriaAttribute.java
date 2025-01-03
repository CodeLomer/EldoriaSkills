package com.github.codelomer.eldoriaskills.api;

import lombok.NonNull;

import java.util.UUID;

public interface EldoriaAttribute {
    @NonNull UUID getOwnerUuid();
    String getAttributeName();
    void setAttributeName(String attributeName);
    @NonNull String getId();
    double getValue();
}
