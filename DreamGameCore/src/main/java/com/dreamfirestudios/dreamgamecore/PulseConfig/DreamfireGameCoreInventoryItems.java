package com.dreamfirestudios.dreamgamecore.PulseConfig;

import com.dreamfirestudios.dreamConfig.Abstract.StaticEnumPulseConfig;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamgamecore.DreamGameCore;
import com.dreamfirestudios.dreamgamecore.Enum.DreamfireGameCoreInventoryItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

@PulseAutoRegister
public class DreamfireGameCoreInventoryItems extends StaticEnumPulseConfig<DreamfireGameCoreInventoryItems, DreamfireGameCoreInventoryItem, ItemStack> {
    @Override
    public JavaPlugin mainClass() {return DreamGameCore.GetDreamfireGameCore();}
    @Override
    protected Class<DreamfireGameCoreInventoryItem> getKeyClass () {return DreamfireGameCoreInventoryItem.class;}
    @Override
    protected Class<ItemStack> getValueClass () {return ItemStack.class;}
    @Override
    protected ItemStack getDefaultValueFor (DreamfireGameCoreInventoryItem craftLegendsCoreInventoryItem) {return craftLegendsCoreInventoryItem.ReturnItemStack();}
}