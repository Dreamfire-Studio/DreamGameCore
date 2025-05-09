package com.dreamfirestudios.dreamgamecore.Event;

import com.dreamfirestudios.dreamgamecore.DreamGameCore;
import com.dreamfirestudios.dreamgamecore.PulseConfig.DreamfireGameCoreConfig;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class DreamfireGameCoreResetConfigEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public DreamfireGameCoreResetConfigEvent(){
        DreamfireGameCoreConfig.ReturnStaticAsync(DreamfireGameCoreConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            Bukkit.getScheduler().runTask(DreamGameCore.GetDreamfireGameCore (), () -> {Bukkit.getPluginManager().callEvent(this);});
        }, Throwable::printStackTrace);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public HandlerList getHandlers() {
        return handlers;
    }
}
