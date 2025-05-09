package com.dreamfirestudios.dreamgamecore.Objects;

import com.dreamfirestudios.dreamCore.DreamfireChat.DreamfireChat;
import com.dreamfirestudios.dreamgamecore.API.DreamfireLobbyAPI;
import com.dreamfirestudios.dreamgamecore.Interface.IDreamfireLobby;
import lombok.Getter;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class DreamfireLobbyWrapper {
    private IDreamfireLobby iDreamfireLobby;
    private final HashMap<UUID, DreamfireLobbyRoom> lobbyRooms = new HashMap<>();

    public DreamfireLobbyWrapper(IDreamfireLobby iDreamfireLobby){
        this.iDreamfireLobby = iDreamfireLobby;
    }

    public IDreamfireLobby GetIDreamfireLobby(){return iDreamfireLobby;}

    public boolean HostGame(Player player){
        var dreamfireGameSettings = iDreamfireLobby.GetDreamfireGameSettings();
        return HostGame(player, dreamfireGameSettings);
    }

    public DreamfireLobbyRoom GetLobbyRoomFromPlayer(Player player){
        for(var lobbyRoom : lobbyRooms.values()){
            if(lobbyRoom.IsPlayerInRoom(player)) return lobbyRoom;
        }
        return null;
    }

    public boolean HostGame(Player player, DreamfireGameSettings dreamfireGameSettings){
        if(DreamfireLobbyAPI.IsPlayerInGame(player)){
            DreamfireChat.SendMessageToPlayer(player, NamedTextColor.RED + "You are currently in a active game!");
            return false;
        }
        var lobbyRoom = new DreamfireLobbyRoom(dreamfireGameSettings, this);
        lobbyRoom.PlayerJoinRoom(player, dreamfireGameSettings, true);
        lobbyRooms.put(lobbyRoom.GetLobbyRoomUUID(), lobbyRoom);
        return true;
    }

    public void CancelGame(Player player, String cancelMessage){
        for(var lobbyRoom : lobbyRooms.values()){
            if(lobbyRoom.IsPlayerInRoom(player)){
                lobbyRoom.CancelGame(player, cancelMessage);
                lobbyRooms.remove(lobbyRoom.GetLobbyRoomUUID());
                return;
            }
        }
    }

    public boolean StartGame(Player player){
        for(var lobbyRoom : lobbyRooms.values()){
            if(lobbyRoom.IsPlayerInRoom(player)) return lobbyRoom.StartGame(player);
        }
        return false;
    }

    public void CancelALlRooms(String cancelMessage){
        for(var lobbyRoom : lobbyRooms.values()) lobbyRoom.CancelGame(null, cancelMessage);
    }

    public boolean IsPlayerInGame(Player player){
        for(var lobbyRoom : lobbyRooms.values()){
            if(lobbyRoom.IsPlayerInRoom(player)) return true;
        }
        return false;
    }

}
