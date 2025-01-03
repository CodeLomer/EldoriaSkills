package com.github.codelomer.eldoriaskills.module;

import lombok.*;

import java.util.HashMap;
import java.util.Map;
@RequiredArgsConstructor
@Setter
public class PayloadSettings {
    private final Map<String, PayloadItem> payloadItems = new HashMap<>();
    @Getter
    private double defaultWeight;

    public void addPayloadItem(@NonNull PayloadItem payloadItem) {
        payloadItems.put(payloadItem.id(), payloadItem);
    }

    public void removePayloadItem(@NonNull String id) {
        payloadItems.remove(id);
    }

    public PayloadItem getPayloadItem(@NonNull String id) {
        return payloadItems.get(id);
    }

    public double getWeight(@NonNull String id) {
        PayloadItem payloadItem = payloadItems.get(id);
        return payloadItem == null ? defaultWeight : payloadItem.weight();
    }
}
