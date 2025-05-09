package com.dreamfirestudios.dreamgamecore.SmartInvs;

import com.dreamfirestudios.dreamCore.DreamfireSmartInvs.SmartInventory;
import com.dreamfirestudios.dreamCore.DreamfireSmartInvs.content.InventoryContents;
import com.dreamfirestudios.dreamCore.DreamfireSmartInvs.content.InventoryProvider;
import com.dreamfirestudios.dreamgamecore.API.DreamfireGameCoreAPI;
import com.dreamfirestudios.dreamgamecore.Enum.DreamfireGameCoreInventoryItem;
import com.dreamfirestudios.dreamgamecore.Enum.DreamfireGameCorePermission;
import com.dreamfirestudios.dreamgamecore.PulseConfig.DreamfireGameCoreConfig;
import com.dreamfirestudios.dreamgamecore.PulseConfig.DreamfireGameCorePermissions;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DreamfireGameCoreMenu implements InventoryProvider {
    private final SmartInventory smartInventory;

    public DreamfireGameCoreMenu(Player... players){
        smartInventory = SmartInventory.builder()
                .id("DreamfireGameCoreMenu")
                .provider(this)
                .size(1, 9)
                .title(NamedTextColor.RED + "Admin Menu")
                .build();
        for(var player : players) smartInventory.open(player);
    }

    public CompletableFuture<Void> init(Player player, InventoryContents inventoryContents) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        DreamfireGameCoreConfig.ReturnStaticAsync(DreamfireGameCoreConfig.class, craftLegendsCoreConfig -> {
            DreamfireGameCoreSmartInvsItems.InventoryItem(player, DreamfireGameCoreInventoryItem.BlankTile, clickableItem -> {
                inventoryContents.fillRow(0, clickableItem);
            }, this::BlankTileClick);

            DreamfireGameCoreSmartInvsItems.InventoryItemWithFeedback(player, DreamfireGameCoreInventoryItem.SystemEnabled,
                    itemStack -> {
                        var itemMeta = itemStack.getItemMeta();
                        var lore = craftLegendsCoreConfig.systemEnabled
                                ? List.of(Component.text(NamedTextColor.WHITE + "Currently: " + NamedTextColor.GREEN + "ENABLED"))
                                : List.of(Component.text(NamedTextColor.WHITE + "Currently: " + NamedTextColor.RED + "DISABLED"));
                        itemMeta.lore(lore);
                        itemStack.setItemMeta(itemMeta);
                        return itemStack;
                    },
                    clickableItem -> {
                        inventoryContents.set(0, 2, clickableItem);
                    }, this::SystemEnabledClick);

            DreamfireGameCoreSmartInvsItems.InventoryItem(player, DreamfireGameCoreInventoryItem.ReloadConfigs, clickableItem -> {
                inventoryContents.set(0, 4, clickableItem);
            }, this::ReloadConfigsClick);

            DreamfireGameCoreSmartInvsItems.InventoryItem(player, DreamfireGameCoreInventoryItem.ResetConfigs, clickableItem -> {
                inventoryContents.set(0, 6, clickableItem);
            }, this::ResetConfigsClick);

            future.complete(null);
        }, future::completeExceptionally);
        return future;
    }

    private void BlankTileClick(Player player, InventoryClickEvent inventoryClickEvent){
        inventoryClickEvent.setCancelled(false);
    }

    private void SystemEnabledClick(Player player, InventoryClickEvent inventoryClickEvent){
        DreamfireGameCorePermissions.ReturnStaticAsync(DreamfireGameCorePermissions.class, craftLegendsCorePermissions -> {
            if(!craftLegendsCorePermissions.DoesPlayerHavePermission(DreamfireGameCorePermission.ENABLE_SYSTEM, player, true)) return;
            DreamfireGameCoreAPI.DreamfireGameCoreToggleEnableSystem();
            smartInventory.open(player);
        }, Throwable::printStackTrace);
    }

    private void ReloadConfigsClick(Player player, InventoryClickEvent inventoryClickEvent) {
        DreamfireGameCorePermissions.ReturnStaticAsync(DreamfireGameCorePermissions.class, craftLegendsCorePermissions -> {
            if (!craftLegendsCorePermissions.DoesPlayerHavePermission(DreamfireGameCorePermission.RELOAD_CONFIGS, player, true)) return;
            DreamfireGameCoreAPI.DreamfireGameCoreReloadConfigs();
            smartInventory.open(player);
        }, Throwable::printStackTrace);
    }

    private void ResetConfigsClick(Player player, InventoryClickEvent inventoryClickEvent){
        DreamfireGameCorePermissions.ReturnStaticAsync(DreamfireGameCorePermissions.class, craftLegendsCorePermissions -> {
            if(!craftLegendsCorePermissions.DoesPlayerHavePermission(DreamfireGameCorePermission.RESET_CONFIGS, player, true)) return;
            DreamfireGameCoreAPI.DreamfireGameCoreResetConfigs();
            smartInventory.open(player);
        }, Throwable::printStackTrace);
    }
}