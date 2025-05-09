package com.dreamfirestudios.dreamgamecore.PlayerCommand;

import com.dreamfirestudios.dreamCommand.Enums.TabType;
import com.dreamfirestudios.dreamCommand.Interface.PCAutoTab;
import com.dreamfirestudios.dreamCommand.Interface.PCMethod;
import com.dreamfirestudios.dreamCommand.Interface.PCSignature;
import com.dreamfirestudios.dreamCommand.Interface.PCTab;
import com.dreamfirestudios.dreamCommand.PlayerCommand;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamgamecore.API.DreamfireGameCoreAPI;
import com.dreamfirestudios.dreamgamecore.Enum.DreamfireGameCoreMessage;
import com.dreamfirestudios.dreamgamecore.Enum.DreamfireGameCorePermission;
import com.dreamfirestudios.dreamgamecore.PulseConfig.DreamfireGameCoreConfig;
import com.dreamfirestudios.dreamgamecore.PulseConfig.DreamfireGameCoreMessages;
import com.dreamfirestudios.dreamgamecore.PulseConfig.DreamfireGameCorePermissions;
import com.dreamfirestudios.dreamgamecore.SmartInvs.DreamfireGameCoreMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.UUID;

@PulseAutoRegister
public class DreamfireGameCorePlayerCommand extends PlayerCommand {
    public DreamfireGameCorePlayerCommand() {
        super("DGC_player", false);
    }

    @Override
    public void NoMethodFound(Player player, String s, String[] strings) {}
    @Override
    public String helpMenuPrefix(Player player) {
        return "";
    }
    @Override
    public LinkedHashMap<String, String> helpMenuFormat(Player player, LinkedHashMap<String, String> linkedHashMap) {return new LinkedHashMap<>();}
    @Override
    public String helpMenuSuffix(Player player) {
        return "";
    }

    @PCMethod
    @PCSignature({})
    public void DreamfireGameCoreMethod(UUID playerID){
        var player = Bukkit.getPlayer(playerID);
        if(player == null) return;
        DreamfireGameCorePermissions.ReturnStaticAsync(DreamfireGameCorePermissions.class, craftLegendsCorePermissions -> {
            if(!craftLegendsCorePermissions.DoesPlayerHavePermission(DreamfireGameCorePermission.ADMIN_CONSOLE, player, true)) return;
            new DreamfireGameCoreMenu(player);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"enable"})
    @PCAutoTab(pos = 1)
    public void DreamfireGameCoreEnableMethod(UUID playerID, boolean state){
        var player = Bukkit.getPlayer(playerID);
        if(player == null) return;
        DreamfireGameCorePermissions.ReturnStaticAsync(DreamfireGameCorePermissions.class, craftLegendsCorePermissions -> {
            if(!craftLegendsCorePermissions.DoesPlayerHavePermission(DreamfireGameCorePermission.ENABLE_SYSTEM, player, true)) return;
            DreamfireGameCoreAPI.DreamfireGameCoreEnableSystem(state);
            DreamfireGameCoreMessages.ReturnStaticAsync(DreamfireGameCoreMessages.class, craftLegendsCoreMessages -> {
                craftLegendsCoreMessages.SendMessageToPlayer(state ? DreamfireGameCoreMessage.ConsoleEnabledSystem : DreamfireGameCoreMessage.ConsoleDisableSystem, player);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"serialize"})
    @PCTab(pos = 1, type = TabType.PureData, data = "ITEM ID")
    public void DreamfireGameCoreSerializeItemMethod(UUID playerUUID, String itemName){
        var player = Bukkit.getPlayer(playerUUID);
        if(player == null) return;
        DreamfireGameCorePermissions.ReturnStaticAsync(DreamfireGameCorePermissions.class, craftLegendsCorePermissions -> {
            if(!craftLegendsCorePermissions.DoesPlayerHavePermission(DreamfireGameCorePermission.SERILIZE_ITEM, player, true)) return;
            DreamfireGameCoreAPI.SerializeItem(itemName, player.getInventory().getItemInMainHand());
            DreamfireGameCoreMessages.ReturnStaticAsync(DreamfireGameCoreMessages.class, craftLegendsCoreMessages -> {
                craftLegendsCoreMessages.SendMessageToPlayer(DreamfireGameCoreMessage.PlayerSerlizedItem, player, itemName);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"configs", "reset"})
    public void DreamfireGameCoreConfigsResetMethod(UUID playerID){
        var player = Bukkit.getPlayer(playerID);
        if(player == null) return;
        DreamfireGameCoreConfig.ReturnStaticAsync(DreamfireGameCoreConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            DreamfireGameCorePermissions.ReturnStaticAsync(DreamfireGameCorePermissions.class, craftLegendsCorePermissions -> {
                if(!craftLegendsCorePermissions.DoesPlayerHavePermission(DreamfireGameCorePermission.RESET_CONFIGS, player, true)) return;
                DreamfireGameCoreAPI.DreamfireGameCoreResetConfigs();
                DreamfireGameCoreMessages.ReturnStaticAsync(DreamfireGameCoreMessages.class, craftLegendsCoreMessages -> {
                    craftLegendsCoreMessages.SendMessageToPlayer(DreamfireGameCoreMessage.PlayerResetConfig, player);
                }, Throwable::printStackTrace);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"configs", "reload"})
    public void DreamfireGameCoreReloadMethod(UUID playerID){
        var player = Bukkit.getPlayer(playerID);
        if(player == null) return;
        DreamfireGameCoreConfig.ReturnStaticAsync(DreamfireGameCoreConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            DreamfireGameCorePermissions.ReturnStaticAsync(DreamfireGameCorePermissions.class, craftLegendsCorePermissions -> {
                if(!craftLegendsCorePermissions.DoesPlayerHavePermission(DreamfireGameCorePermission.RELOAD_CONFIGS, player, true)) return;
                DreamfireGameCoreAPI.DreamfireGameCoreResetConfigs();
                DreamfireGameCoreMessages.ReturnStaticAsync(DreamfireGameCoreMessages.class, craftLegendsCoreMessages -> {
                    craftLegendsCoreMessages.SendMessageToPlayer(DreamfireGameCoreMessage.PlayerReloadedConfig, player);
                }, Throwable::printStackTrace);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }
}