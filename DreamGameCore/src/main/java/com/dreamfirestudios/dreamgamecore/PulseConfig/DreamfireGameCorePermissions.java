package com.dreamfirestudios.dreamgamecore.PulseConfig;
;
import com.dreamfirestudios.dreamConfig.Abstract.StaticEnumPulseConfig;
import com.dreamfirestudios.dreamCore.DreamfireChat.DreamfireChat;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamCore.DreamfireLuckPerms.DreamfireLuckPerms;
import com.dreamfirestudios.dreamgamecore.DreamGameCore;
import com.dreamfirestudios.dreamgamecore.Enum.DreamfireGameCorePermission;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


@PulseAutoRegister
public class DreamfireGameCorePermissions extends StaticEnumPulseConfig<DreamfireGameCorePermissions, DreamfireGameCorePermission, String> {

    @Override
    public JavaPlugin mainClass() {return DreamGameCore.GetDreamfireGameCore();}
    @Override
    protected Class<DreamfireGameCorePermission> getKeyClass() {return DreamfireGameCorePermission.class;}
    @Override
    protected Class<String> getValueClass() {return String.class;}
    @Override
    protected String getDefaultValueFor(DreamfireGameCorePermission craftLegendsCorePermission) {return craftLegendsCorePermission.permission;}

    public boolean DoesPlayerHavePermission(DreamfireGameCorePermission permission, Player player, boolean sendError){
        if(permission == null || player == null) return false;
        var user = DreamfireLuckPerms.getUser(player);
        var permissionState = DreamfireLuckPerms.hasPermission(user, getDefaultValueFor(permission));
        if(!permissionState && sendError) DreamfireChat.SendMessageToPlayer(player, permission.error);
        return permissionState;
    }
}
