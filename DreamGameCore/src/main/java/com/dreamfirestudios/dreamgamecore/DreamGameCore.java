package com.dreamfirestudios.dreamgamecore;

import com.dreamfirestudios.dreamCommand.DreamCommand;
import com.dreamfirestudios.dreamCore.DreamfireChat.DreamfireChat;
import com.dreamfirestudios.dreamCore.DreamfireJava.DreamfireClassAPI;
import com.dreamfirestudios.dreamCore.DreamfireJava.DreamfireJavaAPI;
import com.dreamfirestudios.dreamgamecore.API.AdvancedSlimeManagerAPI;
import com.dreamfirestudios.dreamgamecore.API.DreamfireGameCoreAPI;
import com.dreamfirestudios.dreamgamecore.DreamfireVariableTest.DreamfireGameCoreInventoryItemVariableTest;
import com.dreamfirestudios.dreamgamecore.DreamfireVariableTest.DreamfireGameCoreMessageVariableTest;
import com.dreamfirestudios.dreamgamecore.DreamfireVariableTest.DreamfireGameCorePermissionVariableTest;
import com.dreamfirestudios.dreamgamecore.Interface.IDreamfireLobby;
import com.dreamfirestudios.dreamgamecore.Objects.DreamfireLobbyWrapper;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class DreamGameCore extends JavaPlugin {
    private static DreamGameCore craftLegendsCore;
    public static DreamGameCore GetDreamfireGameCore(){return craftLegendsCore;}
    private static HashMap<String, DreamfireLobbyWrapper> networkLobbies = new HashMap<>();

    @Override
    public void onEnable() {
        craftLegendsCore = this;
        DreamfireClassAPI.RegisterPulseVariableTest(craftLegendsCore, new DreamfireGameCoreMessageVariableTest());
        DreamfireClassAPI.RegisterPulseVariableTest(craftLegendsCore, new DreamfireGameCoreInventoryItemVariableTest());
        DreamfireClassAPI.RegisterPulseVariableTest(craftLegendsCore, new DreamfireGameCorePermissionVariableTest());
        DreamfireGameCoreAPI.DreamfireGameCoreReloadConfigs();
        DreamfireClassAPI.RegisterClasses(this);
        DreamCommand.RegisterRaw(this);
    }

    @Override
    public void onDisable() {
        for(var lobbyWrapper : networkLobbies.values()){
            lobbyWrapper.CancelALlRooms(NamedTextColor.RED + "Room canceled due to server restart!");
        }
    }

    public static DreamfireLobbyWrapper GetDreamfireLobbyWrapper(String lobbyName){
        return networkLobbies.getOrDefault(lobbyName, null);
    }

    public static boolean IsPlayerInGame(Player player){
        for(var dreamfireLobbyWrapper : DreamGameCore.networkLobbies.values()){
            if(dreamfireLobbyWrapper.IsPlayerInGame(player)) return true;
        }
        return false;
    }

    public static DreamfireLobbyWrapper GetDreamfireLobbyWrapperFromPlayer(Player player){
        for(var dreamfireLobbyWrapper : DreamGameCore.networkLobbies.values()){
            if(dreamfireLobbyWrapper.IsPlayerInGame(player)) return dreamfireLobbyWrapper;
        }
        return null;
    }

    public static void RegisterCustomLobbiesRaw(JavaPlugin javaPlugin){
        try { RegisterCustomLobbiesRaw(javaPlugin); }
        catch (Exception e) { throw new RuntimeException(e); }
    }

    public static void RegisterCustomLobbies(JavaPlugin javaPlugin) throws Exception {
        for(var autoRegisterClass : DreamfireJavaAPI.getAutoRegisterClasses(javaPlugin)){
            if(IDreamfireLobby.class.isAssignableFrom(autoRegisterClass)){
                var dreamfireLobby = (IDreamfireLobby) autoRegisterClass.getConstructor().newInstance();
                RegisterCustomLobbies(dreamfireLobby);
            }
        }
    }

    public static void RegisterCustomLobbies(IDreamfireLobby iDreamfireLobby){
        Bukkit.getScheduler().runTask(GetDreamfireGameCore(), () -> {
            var dreamfireLobbyWrapper = new DreamfireLobbyWrapper(iDreamfireLobby);
            var slimeWorld = AdvancedSlimeManagerAPI.ReadVanillaWorld(dreamfireLobbyWrapper.GetIDreamfireLobby().GetLobbyName());
            networkLobbies.put(dreamfireLobbyWrapper.GetIDreamfireLobby().GetLobbyName(), dreamfireLobbyWrapper);
            DreamfireChat.SendMessageToConsole(String.format("&8Registered Dreamfire Lobby: %s", dreamfireLobbyWrapper.GetIDreamfireLobby().GetLobbyName()));
        });
    }
}