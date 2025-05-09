package com.dreamfirestudios.dreamgamecore.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
public class DreamfireGameSettings {
    private int lobbyMinPlayers;
    private int lobbyMaxPlayers;
    private String lobbyPassword;
    private boolean isVisible;
    private int playerTTL;
    private int serverTTL;
    private int playerPerTeam;

    private DreamfireGameSettings(){

    }

    public boolean DoSettingsMatch(DreamfireGameSettings dreamfireGameSettings, boolean isCreator){
        if(isCreator) return true;
        if(lobbyPassword.isEmpty()) return true;
        else if(!lobbyPassword.equalsIgnoreCase(dreamfireGameSettings.lobbyPassword)) return false;
        return playerPerTeam == dreamfireGameSettings.playerPerTeam;
    }

    @Getter
    public static class DreamfireGameSettingsBuilder{
        private int lobbyMinPlayers = 1;
        private int lobbyMaxPlayers = 1;
        private String lobbyPassword = "";
        private boolean isVisible = false;
        private int playerTTL = 5;
        private int serverTTL = 10;
        private int playerPerTeam = 1;

        public DreamfireGameSettingsBuilder lobbyMinPlayers(int lobbyMinPlayers){
            this.lobbyMinPlayers = lobbyMinPlayers;
            return this;
        }

        public DreamfireGameSettingsBuilder lobbyMaxPlayers(int lobbyMaxPlayers){
            this.lobbyMaxPlayers = lobbyMaxPlayers;
            return this;
        }

        public DreamfireGameSettingsBuilder lobbyPassword(String lobbyPassword){
            this.lobbyPassword = lobbyPassword;
            return this;
        }

        public DreamfireGameSettingsBuilder isVisible(boolean isVisible){
            this.isVisible = isVisible;
            return this;
        }

        public DreamfireGameSettingsBuilder playerTTL(int playerTTL){
            this.playerTTL = playerTTL;
            return this;
        }

        public DreamfireGameSettingsBuilder serverTTL(int serverTTL){
            this.serverTTL = serverTTL;
            return this;
        }

        public DreamfireGameSettingsBuilder PlayerPerTeam(int playerPerTeam){
            this.playerPerTeam = playerPerTeam;
            return this;
        }

        public DreamfireGameSettings Build(){
            var dreamfireGameSettings = new DreamfireGameSettings();
            dreamfireGameSettings.lobbyMaxPlayers = lobbyMaxPlayers;
            dreamfireGameSettings.lobbyMinPlayers = lobbyMinPlayers;
            dreamfireGameSettings.lobbyPassword = lobbyPassword;
            dreamfireGameSettings.isVisible = isVisible;
            dreamfireGameSettings.playerTTL = playerTTL;
            dreamfireGameSettings.serverTTL = serverTTL;
            dreamfireGameSettings.playerPerTeam = playerPerTeam;
            return dreamfireGameSettings;
        }
    }
}
