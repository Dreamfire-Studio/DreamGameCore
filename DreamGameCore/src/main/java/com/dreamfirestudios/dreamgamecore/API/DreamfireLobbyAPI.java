package com.dreamfirestudios.dreamgamecore.API;

import com.dreamfirestudios.dreamgamecore.DreamGameCore;
import com.dreamfirestudios.dreamgamecore.Interface.IDreamfireLobby;
import com.dreamfirestudios.dreamgamecore.Objects.DreamfireLobbyWrapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DreamfireLobbyAPI {
    public static DreamfireLobbyWrapper GetDreamfireLobbyWrapperFromPlayer(Player player){
        return DreamGameCore.GetDreamfireLobbyWrapperFromPlayer(player);
    }

    public static boolean HostDreamfireLobby(Player player, IDreamfireLobby iDreamfireLobby){
        Bukkit.getScheduler().runTask(DreamGameCore.GetDreamfireGameCore(), () -> {
            var lobbyWrapper = DreamGameCore.GetDreamfireLobbyWrapper(iDreamfireLobby.GetLobbyName());
            if(lobbyWrapper == null) return;
            lobbyWrapper.HostGame(player);
        });
        return true;
    }

    public static boolean HostDreamfireLobby(Player player, IDreamfireLobby iDreamfireLobby, com.dreamfirestudios.dreamgamecore.Objects.DreamfireGameSettings dreamfireGameSettings){
        Bukkit.getScheduler().runTask(DreamGameCore.GetDreamfireGameCore(), () -> {
            var lobbyWrapper = DreamGameCore.GetDreamfireLobbyWrapper(iDreamfireLobby.GetLobbyName());
            if(lobbyWrapper == null) return;
            lobbyWrapper.HostGame(player, dreamfireGameSettings);
        });
        return true;
    }

    public static boolean IsPlayerInGame(Player player){
       return DreamGameCore.IsPlayerInGame(player);
    }
}
