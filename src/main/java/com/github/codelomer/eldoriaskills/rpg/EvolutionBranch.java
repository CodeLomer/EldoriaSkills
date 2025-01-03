package com.github.codelomer.eldoriaskills.rpg;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class EvolutionBranch {
    private final @NonNull EldoriaRole from;
    private final @NonNull EldoriaRole to;
    private double needEvolutionPoints;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EvolutionBranch that = (EvolutionBranch) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
