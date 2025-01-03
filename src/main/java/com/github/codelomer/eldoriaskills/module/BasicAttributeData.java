package com.github.codelomer.eldoriaskills.module;

import lombok.NonNull;

import java.util.List;
import java.util.Objects;

public record BasicAttributeData(@NonNull String id, String name, double defaultValue) {

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BasicAttributeData that = (BasicAttributeData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
