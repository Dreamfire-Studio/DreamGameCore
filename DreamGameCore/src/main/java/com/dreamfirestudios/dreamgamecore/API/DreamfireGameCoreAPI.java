package com.dreamfirestudios.dreamgamecore.API;

import com.dreamfirestudios.dreamConfig.DreamConfig;
import com.dreamfirestudios.dreamgamecore.DreamGameCore;
import com.dreamfirestudios.dreamgamecore.Event.DreamfireGameCoreResetConfigEvent;
import com.dreamfirestudios.dreamgamecore.PulseConfig.DreamfireGameCoreConfig;
import com.dreamfirestudios.dreamgamecore.PulseConfig.DreamfireGameCoreSerilizableItems;
import org.bukkit.inventory.ItemStack;

public class DreamfireGameCoreAPI {
     public static void DreamfireGameCoreEnableSystem(boolean state){
        DreamfireGameCoreConfig.ReturnStaticAsync(DreamfireGameCoreConfig.class, craftLegendsCoreConfig -> {
            craftLegendsCoreConfig.systemEnabled = state;
            craftLegendsCoreConfig.SaveConfig(iPulseConfig -> {}, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
     }

     public static void SerializeItem(String id, ItemStack itemStack){
         DreamfireGameCoreSerilizableItems.ReturnStaticAsync(DreamfireGameCoreSerilizableItems.class, craftLegendsCoreSerilizableItems -> {
             craftLegendsCoreSerilizableItems.AddItemStack(id, itemStack);
             craftLegendsCoreSerilizableItems.SaveConfig(iPulseConfig -> {}, Throwable::printStackTrace);
         }, Throwable::printStackTrace);
     }

    public static void DreamfireGameCoreToggleEnableSystem(){
        DreamfireGameCoreConfig.ReturnStaticAsync(DreamfireGameCoreConfig.class, craftLegendsCoreConfig -> {
            craftLegendsCoreConfig.systemEnabled = !craftLegendsCoreConfig.systemEnabled;
            craftLegendsCoreConfig.SaveConfig(iPulseConfig -> {}, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    public static void DreamfireGameCoreResetConfigs(){
        DreamfireGameCoreConfig.ReturnStaticAsync(DreamfireGameCoreConfig.class, craftLegendsCoreConfig -> {
           if(!craftLegendsCoreConfig.systemEnabled) return;
           DreamConfig.GetDreamfireConfig().RegisterStatic(DreamGameCore.GetDreamfireGameCore(), true);
           new DreamfireGameCoreResetConfigEvent();
        }, Throwable::printStackTrace);
    }

    public static void DreamfireGameCoreReloadConfigs(){
        DreamfireGameCoreConfig.ReturnStaticAsync(DreamfireGameCoreConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            DreamConfig.GetDreamfireConfig().RegisterStatic(DreamGameCore.GetDreamfireGameCore(), false);
            new DreamfireGameCoreResetConfigEvent();
        }, Throwable::printStackTrace);
    }
}