package com.dreamfirestudios.dreamgamecore.Objects;

import com.dreamfirestudios.dreamCore.DreamfireChat.DreamfireChat;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class NetworkTeam {
    public List<Player> playersOnTeam = new ArrayList<>();

    public NetworkTeam(List<Player> playersOnTeam){ this.playersOnTeam = playersOnTeam; }
    public NetworkTeam(Player... playersOnTeam){ this.playersOnTeam.addAll(Arrays.asList(playersOnTeam));}
    public boolean IsPlayer(Player player){
        return playersOnTeam.contains(player);
    }
    public int NumberOfPlayers(){ return playersOnTeam.size(); }
    public boolean RemovePlayer(Player player){
        playersOnTeam.remove(player);
        return playersOnTeam.isEmpty();
    }

    public int CountPlayers(){
        return playersOnTeam.size();
    }

    public boolean CanPlayerJoinTeam(Player player, int perTeam){
        return playersOnTeam.size() < perTeam;
    }
    public boolean JoinTeam(Player player, int perTeam){
        if(!CanPlayerJoinTeam(player, perTeam)) return false;
        playersOnTeam.add(player);
        return true;
    }
    public List<Player> ReturnAllPlayers(boolean onePerTeam){
        if(!onePerTeam) return playersOnTeam;
        else return Collections.singletonList(playersOnTeam.get(new Random().nextInt(playersOnTeam.size())));
    }
    public void AddPlayer(Player... players){ this.playersOnTeam.addAll(Arrays.asList(players)); }
    public void TeleportPlayer(Location location){ for(var player : playersOnTeam) player.teleport(location); }
    public void SetGameMode(GameMode gameMode){ for(var player : playersOnTeam) player.setGameMode(gameMode); }
    public void SendMessageToPlayers(String playerMessage){
        for(var player : playersOnTeam) DreamfireChat.SendMessageToPlayer(player, playerMessage);
    }
}
