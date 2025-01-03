package com.github.codelomer.eldoriaskills.rpg;

import lombok.*;

import java.util.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
public class EldoriaRace {
    private final @NonNull @Getter String id;
    private @Getter String name;
    private @Getter List<String> description;
    private final EvolutionThree evolutionThree = new EvolutionThree(this);
    private final Map<String,EldoriaRole> roles = new HashMap<>();

    public void registerRole(@NonNull EldoriaRole role) {
        roles.put(normalizeId(role.getId()),role);
    }

    public EldoriaRole getRole(@NonNull String id) {
        return roles.get(normalizeId(id));
    }

    public Set<EldoriaRole> getRoles() {
        return new HashSet<>(roles.values());
    }

    public boolean addBranch(@NonNull EvolutionBranch branch) {
        if(evolutionThree.addBranch(branch)) {
            registerRole(branch.getTo());
            registerRole(branch.getFrom());
            return true;
        }
        return false;
    }

    public void removeBranch(EvolutionBranch branch) {
        evolutionThree.removeBranch(branch);
    }

    public Set<EvolutionBranch> getBranches() {
        return evolutionThree.getBranches();
    }

    public Collection<EvolutionBranch> getOutFromBranches(@NonNull String roleId) {
        return evolutionThree.getOutFromBranches(normalizeId(roleId));
    }

    public Collection<EvolutionBranch> getInToBranches(@NonNull String roleId) {
        return evolutionThree.getInToBranches(normalizeId(roleId));
    }

    public Set<EldoriaRole> firstRoles(){
        return new HashSet<>(evolutionThree.getFirstRoles());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EldoriaRace race = (EldoriaRace) o;
        return Objects.equals(id, race.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    private String normalizeId(String id) {
        return id.toLowerCase(Locale.ENGLISH);
    }
}
