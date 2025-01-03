package com.github.codelomer.eldoriaskills.module;

import lombok.NonNull;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
public record PayloadItem(@NonNull String id, @NonNull ItemStack itemStack, double weight) {

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PayloadItem that = (PayloadItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
