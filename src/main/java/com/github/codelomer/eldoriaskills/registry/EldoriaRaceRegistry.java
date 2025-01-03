package com.github.codelomer.eldoriaskills.registry;

import com.github.codelomer.eldoriaskills.config.impl.RaceConfig;
import com.github.codelomer.eldoriaskills.rpg.EldoriaRace;
import lombok.NonNull;

import java.util.*;

public class EldoriaRaceRegistry {
    private final Map<String, EldoriaRace> races = new HashMap<>();

    @NonNull
    private final RaceConfig raceConfig;

    public EldoriaRaceRegistry(@NonNull RaceConfig raceConfig) {
        this.raceConfig = raceConfig;
        reload();
    }

    public void register(@NonNull EldoriaRace race) {
        races.put(normalizeId(race.getId()), race);
    }

    public void register(@NonNull Collection<EldoriaRace> races) {
        races.stream().filter(Objects::nonNull).forEach(this::register);
    }

    public EldoriaRace getRace(@NonNull String id) {
        return races.get(normalizeId(id));
    }

    public Set<EldoriaRace> getRaces() {
        return new HashSet<>(races.values());
    }

    public void reload() {
        races.clear();
        for (EldoriaRace race : raceConfig.getRaceSet()) {
            register(race);
        }
    }

    private String normalizeId(String id) {
        return id.toLowerCase(Locale.ENGLISH);
    }
}
