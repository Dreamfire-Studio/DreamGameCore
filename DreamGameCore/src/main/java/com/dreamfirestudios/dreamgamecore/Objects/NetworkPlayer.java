package com.dreamfirestudios.dreamgamecore.Objects;

import com.dreamfirestudios.dreamCore.DreamfireChat.DreamfireChat;
import lombok.Getter;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class NetworkPlayer {
    @Getter
    private UUID networkPlayerID;
    private Player player;
    private NetworkTeam networkTeam;
    private Location spawnLocation;
    private UUID creatorUUID;

    public NetworkPlayer(Player player, boolean isCreator){
        this.player = player;
        networkPlayerID = UUID.randomUUID();
        if(isCreator) creatorUUID = player.getUniqueId();
    }

    public NetworkPlayer(NetworkTeam networkTeam, boolean isCreator, UUID creatorUUID){
        this.networkTeam = networkTeam;
        networkPlayerID = UUID.randomUUID();
        if(isCreator) this.creatorUUID = creatorUUID;
    }

    public int CountPlayers(){
        if(IsTeam()) return networkTeam.CountPlayers();
        else return 1;
    }

    public List<Player> GetALlPlayers(){
        if(IsTeam()) return networkTeam.playersOnTeam;
        else return List.of(player);
    }

    public boolean IsTeam(){ return player == null; }

    public boolean IsPlayerHost(Player player){
        return creatorUUID.equals(player.getUniqueId());
    }

    public boolean IsPlayer(Player player){
        if(IsTeam()) return networkTeam.IsPlayer(player);
        else return this.player == player;
    }

    public boolean CanPlayerJoinTeam(Player player, int perTeam){
        if(networkTeam == null) return false;
        return networkTeam.CanPlayerJoinTeam(player, perTeam);
    }

    public boolean JoinTeam(Player player, int perTeam){
        if(networkTeam == null) return false;
        return networkTeam.JoinTeam(player, perTeam);
    }

    public int NumberOfPlayers(){
        if(IsTeam()) return networkTeam.NumberOfPlayers();
        else return 1;
    }

    public boolean RemovePlayer(Player player){
        if(IsTeam()) return networkTeam.RemovePlayer(player);
        else return true;
    }

    public List<Player> ReturnAllPlayers(boolean onePerTeam){
        if(IsTeam()) return networkTeam.ReturnAllPlayers(onePerTeam);
        else return Collections.singletonList(player);
    }

    public void TeleportPlayer(Location location){
        if(IsTeam()) networkTeam.TeleportPlayer(location);
        else player.teleport(location);
    }

    public void SetGameMode(GameMode gameMode){
        if(IsTeam()) networkTeam.SetGameMode(gameMode);
        else player.setGameMode(gameMode);
    }

    public void SendMessageToPlayers(String playerMessage){
        if(IsTeam()) networkTeam.SendMessageToPlayers(playerMessage);
        DreamfireChat.SendMessageToPlayer(player, playerMessage);
    }

    public void SetSpawnLocation(Location spawnLocation){
        if(this.spawnLocation != null) return;
        this.spawnLocation = spawnLocation;
    }

    public void TeleportToSpawn(){
        TeleportPlayer(spawnLocation);
    }
}