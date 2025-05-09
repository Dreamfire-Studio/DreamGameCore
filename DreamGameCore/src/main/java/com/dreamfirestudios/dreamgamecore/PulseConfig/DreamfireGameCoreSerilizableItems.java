package com.dreamfirestudios.dreamgamecore.PulseConfig;

import com.dreamfirestudios.dreamConfig.Abstract.StaticPulseConfig;
import com.dreamfirestudios.dreamConfig.SaveableObjects.SaveableHashmap;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamgamecore.DreamGameCore;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

@PulseAutoRegister
public class DreamfireGameCoreSerilizableItems extends StaticPulseConfig<DreamfireGameCoreSerilizableItems> {
    @Override
    public JavaPlugin mainClass() {return DreamGameCore.GetDreamfireGameCore();}

    public SaveableHashmap<String, ItemStack> itemStackSaveableHashmap = new SaveableHashmap<>(String.class, ItemStack.class);

    public void AddItemStack(String id, ItemStack itemStack){
        itemStackSaveableHashmap.getHashMap().put(id, itemStack);
    }

    public ItemStack GetItemStack(String id){
        return itemStackSaveableHashmap.getHashMap().getOrDefault(id, null);
    }
}
