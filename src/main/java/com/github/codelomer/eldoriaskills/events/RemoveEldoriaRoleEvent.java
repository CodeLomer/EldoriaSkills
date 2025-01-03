package com.github.codelomer.eldoriaskills.events;

import com.github.codelomer.eldoriaskills.api.EldoriaPlayer;
import com.github.codelomer.eldoriaskills.rpg.EldoriaRole;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
@RequiredArgsConstructor
public class RemoveEldoriaRoleEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    @Getter
    private final @NonNull EldoriaPlayer player;
    @Getter
    private final @NonNull EldoriaRole role;
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
