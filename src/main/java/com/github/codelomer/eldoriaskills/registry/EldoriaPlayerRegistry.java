package com.github.codelomer.eldoriaskills.registry;

import com.github.codelomer.eldoriaskills.api.EldoriaPlayer;
import com.github.codelomer.eldoriaskills.validator.EldoriaPlayerValidator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class EldoriaPlayerRegistry {
    private final Map<UUID, EldoriaPlayer> players = new HashMap<>();
    private final @NonNull EldoriaPlayerValidator validator;

    public void register(@NonNull EldoriaPlayer player) {
        if(!player.isOnline()) return;
        players.put(player.getUniqueId(), validator.validateAndAdapt(player));
    }

    public void unregister(@NonNull EldoriaPlayer player) {
        if(player.isOnline()) return;
        players.remove(player.getUniqueId());
    }

    public EldoriaPlayer getPlayer(@NonNull UUID uuid) {
        return players.get(uuid);
    }

    public Set<EldoriaPlayer> getOnlinePlayers() {
        return new HashSet<>(players.values());
    }
}
