package com.dreamfirestudios.dreamgamecore.SmartInvs;

import com.dreamfirestudios.dreamCore.DreamfireSmartInvs.ClickableItem;
import com.dreamfirestudios.dreamgamecore.Enum.DreamfireGameCoreInventoryItem;
import com.dreamfirestudios.dreamgamecore.PulseConfig.DreamfireGameCoreInventoryItems;
import com.dreamfirestudios.dreamgamecore.PulseConfig.DreamfireGameCoreSerilizableItems;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class DreamfireGameCoreSmartInvsItems {
    public static void SerializedItem(Player player, String itemID, Function<ItemStack, ItemStack> testFunction, Consumer<ClickableItem> clickableItemConsumer, BiConsumer<Player, InventoryClickEvent> inventoryClickEventConsumer) {
        DreamfireGameCoreSerilizableItems.ReturnStaticAsync(DreamfireGameCoreSerilizableItems.class, craftLegendsCoreSerilizableItems -> {
            var itemStack = testFunction.apply(craftLegendsCoreSerilizableItems.GetItemStack(itemID).clone());
            clickableItemConsumer.accept(ClickableItem.of(itemStack, event -> inventoryClickEventConsumer.accept(player, event)));
        }, Throwable::printStackTrace);
    }

    public static void InventoryItemWithFeedback(Player player, DreamfireGameCoreInventoryItem craftLegendsCoreInventoryItem, Function<ItemStack, ItemStack> testFunction, Consumer<ClickableItem> clickableItemConsumer, BiConsumer<Player, InventoryClickEvent> inventoryClickEventConsumer) {
        DreamfireGameCoreInventoryItems.ReturnStaticAsync(DreamfireGameCoreInventoryItems.class, craftLegendsCoreInventoryItems -> {
            var itemStack = testFunction.apply(craftLegendsCoreInventoryItems.GetValue(craftLegendsCoreInventoryItem).clone());
            clickableItemConsumer.accept(ClickableItem.of(itemStack, event -> inventoryClickEventConsumer.accept(player, event)));
        }, Throwable::printStackTrace);
    }

    public static void InventoryItem(Player player, DreamfireGameCoreInventoryItem craftLegendsCoreInventoryItem, Consumer<ClickableItem> clickableItemConsumer, BiConsumer<Player, InventoryClickEvent> inventoryClickEventConsumer) {
        DreamfireGameCoreInventoryItems.ReturnStaticAsync(DreamfireGameCoreInventoryItems.class, craftLegendsCoreInventoryItems -> {
            var itemStack = craftLegendsCoreInventoryItems.GetValue(craftLegendsCoreInventoryItem).clone();
            clickableItemConsumer.accept(ClickableItem.of(itemStack, event -> inventoryClickEventConsumer.accept(player, event)));
        }, Throwable::printStackTrace);
    }

    public static void SystemEnabled(Player player, boolean isEnabled, Consumer<ClickableItem> clickableItemConsumer, BiConsumer<Player, InventoryClickEvent> inventoryClickEventConsumer){
        DreamfireGameCoreInventoryItems.ReturnStaticAsync(DreamfireGameCoreInventoryItems.class, craftLegendsCoreInventoryItems -> {
            var itemStack = craftLegendsCoreInventoryItems.GetValue(DreamfireGameCoreInventoryItem.SystemEnabled).clone();

            clickableItemConsumer.accept(ClickableItem.of(itemStack, event -> inventoryClickEventConsumer.accept(player, event)));
        }, Throwable::printStackTrace);
    }
}
