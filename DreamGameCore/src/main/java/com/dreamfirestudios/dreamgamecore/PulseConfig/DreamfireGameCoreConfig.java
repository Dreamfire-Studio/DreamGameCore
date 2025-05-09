package com.dreamfirestudios.dreamgamecore.PulseConfig;

import com.dreamfirestudios.dreamConfig.Abstract.StaticPulseConfig;
import com.dreamfirestudios.dreamConfig.Interface.StorageComment;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamgamecore.DreamGameCore;
import com.dreamfirestudios.dreamgamecore.Event.DreamfireGameCoreEnableSystemEvent;
import org.bukkit.plugin.java.JavaPlugin;

//TODO disbale commands, disbale events, disable api calls......
@PulseAutoRegister
public class DreamfireGameCoreConfig extends StaticPulseConfig<DreamfireGameCoreConfig> {
    @Override
    public JavaPlugin mainClass() {return DreamGameCore.GetDreamfireGameCore();}

    @StorageComment("WARNING: SYSTEM WONT RUN IF FALSE!")
    public boolean systemEnabled = true;

    public void ToggleSystemEnabled(boolean newState){
        new DreamfireGameCoreEnableSystemEvent(systemEnabled, newState);
        systemEnabled = newState;
        SaveConfig(iPulseConfig -> {

        }, throwable -> {throwable.printStackTrace();});
    }
}
