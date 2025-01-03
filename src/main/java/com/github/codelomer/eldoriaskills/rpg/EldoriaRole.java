package com.github.codelomer.eldoriaskills.rpg;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class EldoriaRole {
    private final @NonNull String id;
    private final @NonNull Map<String,Double> basicAttributeBonuses;
    private String name;
    private List<String> descriptions;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EldoriaRole role = (EldoriaRole) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
