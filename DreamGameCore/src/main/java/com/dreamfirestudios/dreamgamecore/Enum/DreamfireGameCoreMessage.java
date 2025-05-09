package com.dreamfirestudios.dreamgamecore.Enum;

import com.dreamfirestudios.dreamgamecore.DreamGameCore;

public enum DreamfireGameCoreMessage {
    ConsoleEnabledSystem(String.format("#7fff36%s has been enabled!", DreamGameCore.GetDreamfireGameCore().getName())),
    ConsoleDisableSystem(String.format("#7fff36%s has been disabled!", DreamGameCore.GetDreamfireGameCore().getName())),
    PlayerReloadedConfig(String.format("#7fff36%s configs have been reloaded!", DreamGameCore.GetDreamfireGameCore().getName())),
    PlayerSerlizedItem("#7fff36You have added a item to the serialized items: #ffffff%s"),
    PlayerResetConfig(String.format("#7fff36%s configs have been reset!", DreamGameCore.GetDreamfireGameCore().getName())),
    SystemIsntEnabled(String.format("#7fff36%s Isn't Enabled!", DreamGameCore.GetDreamfireGameCore().getName()));

    public final String message;
    DreamfireGameCoreMessage(String message){
        this.message = message;
    }
}