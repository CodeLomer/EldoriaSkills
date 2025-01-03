package com.github.codelomer.eldoriaskills.api;

import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.UUID;

public interface EldoriaAttributeFactory {

    @Nullable
    EldoriaAttribute createAttribute(@NonNull UUID ownerUuid, @NonNull String id);
}
