package com.dreamfirestudios.dreamgamecore.Enum;

import com.dreamfirestudios.dreamgamecore.DreamGameCore;

public enum DreamfireGameCorePermission {
    RELOAD_CONFIGS("DreamfireGameCore.Admin.ReloadConfigs", String.format("#7fff36%s: You do not have the permission to use this command!", DreamGameCore.class.getSimpleName())),
    RESET_CONFIGS("DreamfireGameCore.Admin.ResetConfigs", String.format("#7fff36%s: You do not have the permission to use this command!", DreamGameCore.class.getSimpleName())),
    ENABLE_SYSTEM("DreamfireGameCore.Admin.EnableSystem", String.format("#7fff36%s: You do not have the permission to use this command!", DreamGameCore.class.getSimpleName())),
    SERILIZE_ITEM("DreamfireGameCore.Admin.SerilizeItem", String.format("#7fff36%s: You do not have the permission to use this command!", DreamGameCore.class.getSimpleName())),
    ADMIN_CONSOLE("CraftLegendsPaper.Admin.SerilizeItem", String.format("#7fff36%s: You do not have the permission to use this command!", DreamGameCore.class.getSimpleName())),
    f("f", "#7fff36You do not have the permission to use this command!");

    public final String permission;
    public final String error;
    DreamfireGameCorePermission(String permission, String error){
        this.permission = permission;
        this.error = error;
    }
}
