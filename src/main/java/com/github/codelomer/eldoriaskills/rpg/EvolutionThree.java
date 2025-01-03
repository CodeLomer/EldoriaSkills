package com.github.codelomer.eldoriaskills.rpg;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EvolutionThree {
    private final @NonNull EldoriaRace race;

    @Getter
    private final Set<EvolutionBranch> branches = new HashSet<>();
    @Getter
    private Set<EldoriaRole> firstRoles = new HashSet<>();

    private final Multimap<String, EvolutionBranch> outFromBranch = HashMultimap.create();
    private final Multimap<String, EvolutionBranch> inToBranch = HashMultimap.create();

    private final DirectedAcyclicGraph<EldoriaRole, DefaultEdge> graph =
            new DirectedAcyclicGraph<>(DefaultEdge.class);

    public boolean addBranch(@NonNull EvolutionBranch branch) {
        EldoriaRole from = branch.getFrom();
        EldoriaRole to = branch.getTo();

        // Добавляем вершины
        graph.addVertex(from);
        graph.addVertex(to);

        // Добавляем ребро
        graph.addEdge(from, to);

        // Проверяем на циклы
        if (!isAcyclic()) {
            graph.removeEdge(from, to); // Удаляем ребро при обнаружении цикла
            return false; // Не удалось добавить ветку
        }

        // Сохраняем ветку и обновляем мапы
        if (branches.add(branch)) {
            outFromBranch.put(from.getId().toLowerCase(Locale.ENGLISH), branch);
            inToBranch.put(to.getId().toLowerCase(Locale.ENGLISH), branch);
        }

        // Пересчитываем первые роли
        calculateFirstRoles();

        return true;
    }

    public void removeBranch(@NonNull EvolutionBranch branch) {
        if (branches.remove(branch)) {
            EldoriaRole from = branch.getFrom();
            EldoriaRole to = branch.getTo();

            // Удаляем ребро из графа
            graph.removeEdge(from, to);

            // Обновляем мапы
            outFromBranch.remove(from.getId().toLowerCase(Locale.ENGLISH), branch);
            inToBranch.remove(to.getId().toLowerCase(Locale.ENGLISH), branch);

            // Пересчитываем первые роли
            calculateFirstRoles();
        }
    }

    private void calculateFirstRoles() {
        // Все вершины без входящих рёбер являются первыми ролями
        firstRoles = graph.vertexSet().stream()
                .filter(role -> graph.incomingEdgesOf(role).isEmpty())
                .collect(Collectors.toSet());
    }

    private boolean isAcyclic() {
        return !new CycleDetector<>(graph).detectCycles();
    }

    public Collection<EvolutionBranch> getOutFromBranches(@NonNull String id) {
        return outFromBranch.get(id);
    }

    public Collection<EvolutionBranch> getInToBranches(@NonNull String id) {
        return inToBranch.get(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EvolutionThree that = (EvolutionThree) o;
        return Objects.equals(race, that.race);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(race);
    }
}
