package com.dreamfirestudios.dreamgamecore.Interface;

import com.dreamfirestudios.dreamgamecore.Objects.DreamfireGameSettings;
import com.dreamfirestudios.dreamgamecore.Objects.DreamfireLobbyRoom;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IDreamfireLobby {
    String GetLobbyName();
    DreamfireGameSettings GetDreamfireGameSettings();
    int GetLobbyMinPlayers();
    int GetLobbyMaxPlayers();
    boolean GetIsVisible();
    int GetMinPlayerTTL();
    int GetMaxPlayerTTL();
    int GetMinServerTTL();
    int GetMaxServerTTL();
    int GetMinPlayerPerTeam();
    int GetMaxPlayerPerTeam();
    boolean CanPlayerJoinRoom(DreamfireLobbyRoom lobbyRoom, Player player, boolean creator);
    Location GetLobbyLocation(DreamfireLobbyRoom lobbyRoom, UUID networkPlayerID, Player player, boolean creator, int index);
    Location GetSpawnLocation(DreamfireLobbyRoom lobbyRoom, UUID networkPlayerID, Player player, boolean creator, int index);
    void LobbyRoomCreatedEvent(DreamfireLobbyRoom dreamfireLobbyRoom);
    void PlayerJoinedRoomEvent(DreamfireLobbyRoom lobbyRoom, Player player, boolean isCreator);
    void PlayerLeftRoomEvent(DreamfireLobbyRoom lobbyRoom, Player player, boolean isCreator);
    void PlayerInRoomCancelEvent(DreamfireLobbyRoom dreamfireLobbyRoom, Player player, String cancelMessage);
}
