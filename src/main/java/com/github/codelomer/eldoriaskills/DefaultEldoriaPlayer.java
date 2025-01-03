package com.github.codelomer.eldoriaskills;

import com.github.codelomer.eldoriaskills.api.EldoriaAttribute;
import com.github.codelomer.eldoriaskills.api.EldoriaPlayer;
import com.github.codelomer.eldoriaskills.events.GiveEldoriaRoleEvent;
import com.github.codelomer.eldoriaskills.events.RemoveEldoriaRoleEvent;
import com.github.codelomer.eldoriaskills.factory.EldoriaAttributeGlobalFactory;
import com.github.codelomer.eldoriaskills.rpg.EldoriaRace;
import com.github.codelomer.eldoriaskills.rpg.EldoriaRole;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.*;

public class DefaultEldoriaPlayer implements EldoriaPlayer {
    private final @NonNull UUID uuid;
    @NonNull
    private final EldoriaAttributeGlobalFactory factoryRegistry;
    private EldoriaRole role;
    private EldoriaRace race;
    private double evolutionPoints;
    private final PluginManager pluginManager = Bukkit.getPluginManager();
    private final Map<String, EldoriaAttribute> attributes = new HashMap<>();

    public DefaultEldoriaPlayer(@NonNull UUID uuid, @NonNull EldoriaAttributeGlobalFactory factoryRegistry) {
        this.uuid = uuid;
        this.factoryRegistry = factoryRegistry;
    }


    @Override
    public @NonNull UUID getUniqueId() {
        return uuid;
    }

    @Override
    public Player getRealPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    @Override
    public EldoriaAttribute getAttribute(@NonNull String id) {
        return attributes.computeIfAbsent(id.toLowerCase(Locale.ENGLISH), key -> factoryRegistry.createAttribute(uuid, key));
    }


    @Override
    public <T extends EldoriaAttribute> T getAttribute(@NonNull String id, @NonNull Class<T> clazz) {
        EldoriaAttribute attribute = getAttribute(id);
        if(clazz.isInstance(attribute)) return clazz.cast(attribute);
        return null;
    }

    @Override
    public Set<EldoriaAttribute> getAttributes() {
        return new HashSet<>(attributes.values());
    }

    @Override
    public double getEvolutionPoints() {
        return evolutionPoints;
    }

    @Override
    public void setEvolutionPoints(double evolutionPoints) {
        this.evolutionPoints = evolutionPoints;
    }

    @Override
    public EldoriaRole getRole() {
        return role;
    }

    @Override
    public void giveRole(@NonNull EldoriaRace race, @NonNull EldoriaRole role) {
        GiveEldoriaRoleEvent giveEldoriaRoleEvent = new GiveEldoriaRoleEvent(this, role, race);
        pluginManager.callEvent(giveEldoriaRoleEvent);
        if(giveEldoriaRoleEvent.isCancelled()){
            return;
        }
        this.role = role;
        this.race = race;
    }

    @Override
    public void removeRole() {
        if(role == null) return;
        RemoveEldoriaRoleEvent removeEldoriaRoleEvent = new RemoveEldoriaRoleEvent(this,role);
        pluginManager.callEvent(removeEldoriaRoleEvent);
        if(removeEldoriaRoleEvent.isCancelled()){
            return;
        }
        role = null;
    }

    @Override
    public EldoriaRace getRace() {
        return race;
    }

    @Override
    public boolean isOnline() {
        Player player = getRealPlayer();
        return player != null && player.isOnline();
    }
}
