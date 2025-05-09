package com.dreamfirestudios.dreamgamecore.Event;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class DreamfireGameCoreEnableSystemEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final boolean oldState;
    private final boolean newState;

    public DreamfireGameCoreEnableSystemEvent(boolean oldState, boolean newState){
        this.oldState = oldState;
        this.newState = newState;
        Bukkit.getPluginManager().callEvent(this);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public HandlerList getHandlers() {
        return handlers;
    }
}
