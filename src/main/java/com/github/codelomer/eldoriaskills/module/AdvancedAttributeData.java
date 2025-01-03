package com.github.codelomer.eldoriaskills.module;

import lombok.NonNull;

import java.util.List;
import java.util.Objects;

public record AdvancedAttributeData(@NonNull String id, @NonNull String formula, String name) {

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AdvancedAttributeData that = (AdvancedAttributeData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
