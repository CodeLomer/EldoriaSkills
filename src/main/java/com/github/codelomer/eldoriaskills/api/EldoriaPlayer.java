package com.github.codelomer.eldoriaskills.api;

import com.github.codelomer.eldoriaskills.rpg.EldoriaRace;
import com.github.codelomer.eldoriaskills.rpg.EldoriaRole;
import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public interface EldoriaPlayer {

    @NonNull
    UUID getUniqueId();
    Player getRealPlayer();
    EldoriaAttribute getAttribute(@NonNull String id);
    <T extends EldoriaAttribute> T getAttribute(@NonNull String id, @NonNull Class<T> clazz);
    Set<EldoriaAttribute> getAttributes();
    double getEvolutionPoints();
    void setEvolutionPoints(double evolutionPoints);
    EldoriaRole getRole();
    void giveRole(@NonNull EldoriaRace race,  @NonNull EldoriaRole role);
    void removeRole();
    EldoriaRace getRace();

    boolean isOnline();
}
