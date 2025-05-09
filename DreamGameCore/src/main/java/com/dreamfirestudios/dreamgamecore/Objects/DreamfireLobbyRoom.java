package com.dreamfirestudios.dreamgamecore.Objects;

import com.dreamfirestudios.dreamgamecore.API.AdvancedSlimeManagerAPI;
import com.dreamfirestudios.dreamgamecore.Objects.DreamfireGameSettings;
import com.infernalsuite.aswm.api.AdvancedSlimePaperAPI;
import com.infernalsuite.aswm.api.world.properties.SlimePropertyMap;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DreamfireLobbyRoom {
    private final UUID lobbyRoomUUID = UUID.randomUUID();
    private DreamfireGameSettings dreamfireGameSettings;
    private DreamfireLobbyWrapper dreamfireLobbyWrapper;
    private final List<NetworkPlayer> networkPlayers = new ArrayList<>();
    private final UUID slimeWorldName;

    public DreamfireLobbyRoom(DreamfireGameSettings dreamfireGameSettings, DreamfireLobbyWrapper dreamfireLobbyWrapper){
        this.dreamfireGameSettings = dreamfireGameSettings;
        this.dreamfireLobbyWrapper = dreamfireLobbyWrapper;
        slimeWorldName = UUID.randomUUID();
        var loadedWorld = AdvancedSlimeManagerAPI.CloneSlimeWorld(dreamfireLobbyWrapper.GetIDreamfireLobby().GetLobbyName(), slimeWorldName.toString());
        dreamfireLobbyWrapper.GetIDreamfireLobby().LobbyRoomCreatedEvent(this);
    }

    public UUID GetLobbyRoomUUID(){return lobbyRoomUUID;}
    public DreamfireGameSettings GetDreamfireGameSettings(){return dreamfireGameSettings;}

    public int GetNumberOfPlayers(){
        var count = 0;
        for(var networkPlayer : networkPlayers) count += networkPlayer.CountPlayers();
        return count;
    }

    public boolean IsPlayerInRoom(Player player){
        for(var networkPlayer : networkPlayers){
            if(networkPlayer.IsPlayer(player)) return true;
        }
        return false;
    }

    public boolean IsPlayerHost(Player player){
        for(var networkPlayer : networkPlayers){
            if(networkPlayer.IsPlayer(player)){
                return networkPlayer.IsPlayerHost(player);
            }
        }
        return false;
    }

    public void PlayerJoinRoom(Player player, DreamfireGameSettings dreamfireGameSettings, boolean isCreator){
        if(dreamfireLobbyWrapper.GetIDreamfireLobby().CanPlayerJoinRoom(this, player, isCreator) && this.dreamfireGameSettings.DoSettingsMatch(dreamfireGameSettings, isCreator)){
            NetworkPlayer networkPlayer;
            if(dreamfireGameSettings.getPlayerPerTeam() == 1) networkPlayer = CreateSingleNetworkPlayer(player, isCreator);
            else networkPlayer = CreateMultiplayerNetworkPlayer(player, isCreator);
            var playerIndex = networkPlayers.indexOf(networkPlayer);
            var lobbyLocation = dreamfireLobbyWrapper.GetIDreamfireLobby().GetLobbyLocation(this, networkPlayer.getNetworkPlayerID(), player, isCreator, playerIndex);
            var spawnLocation = dreamfireLobbyWrapper.GetIDreamfireLobby().GetSpawnLocation(this, networkPlayer.getNetworkPlayerID(), player, isCreator, playerIndex);
            player.teleport(AdvancedSlimeManagerAPI.ConvertLocation(slimeWorldName, lobbyLocation));
            networkPlayer.SetSpawnLocation(AdvancedSlimeManagerAPI.ConvertLocation(slimeWorldName, spawnLocation));
            dreamfireLobbyWrapper.GetIDreamfireLobby().PlayerJoinedRoomEvent(this, player, isCreator);
        }
    }

    public void CancelGame(Player player, String cancelMessage){
        if(player == null || IsPlayerHost(player)){
            for(var networkPlayer : networkPlayers){
                for(var nPlayer : networkPlayer.GetALlPlayers()){
                    dreamfireLobbyWrapper.GetIDreamfireLobby().PlayerInRoomCancelEvent(this, nPlayer, cancelMessage);
                }
            }
        }
    }

    public boolean StartGame(Player player){
        if(player == null || IsPlayerHost(player)){
            var currentPlayers = GetNumberOfPlayers();
            var minimalPlayers = dreamfireGameSettings.getLobbyMinPlayers();
            if(currentPlayers >= minimalPlayers){
                Bukkit.getConsoleSender().sendMessage("Start Game");
                return true;
            }
        }
        return false;
    }

    public void BroadCastMessage(String message){
        for(var networkPlayer : networkPlayers){
            networkPlayer.SendMessageToPlayers(message);
        }
    }

    private NetworkPlayer CreateSingleNetworkPlayer(Player player, boolean isCreator){
        var networkPlayer = new NetworkPlayer(player, isCreator);
        networkPlayers.add(networkPlayer);
        return networkPlayer;
    }

    private NetworkPlayer CreateMultiplayerNetworkPlayer(Player player, boolean isCreator){
        if(isCreator){
            var networkPlayer = new NetworkPlayer(new NetworkTeam(player), isCreator, player.getUniqueId());
            networkPlayers.add(networkPlayer);
            return networkPlayer;
        }
        else{
            for(var networkPlayer : networkPlayers){
                if(networkPlayer.CanPlayerJoinTeam(player, dreamfireGameSettings.getPlayerPerTeam())){
                    networkPlayer.JoinTeam(player, dreamfireGameSettings.getPlayerPerTeam());
                    return networkPlayer;
                }
            }
            var networkPlayer = new NetworkPlayer(new NetworkTeam(player), isCreator, player.getUniqueId());
            networkPlayers.add(networkPlayer);
            return networkPlayer;
        }
    }
}
