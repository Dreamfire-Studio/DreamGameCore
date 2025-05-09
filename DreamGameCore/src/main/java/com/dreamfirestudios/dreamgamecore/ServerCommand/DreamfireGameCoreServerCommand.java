
package com.dreamfirestudios.dreamgamecore.ServerCommand;

import com.dreamfirestudios.dreamCommand.Interface.PCMethod;
import com.dreamfirestudios.dreamCommand.Interface.PCSignature;
import com.dreamfirestudios.dreamCommand.ServerCommand;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamgamecore.API.DreamfireGameCoreAPI;
import com.dreamfirestudios.dreamgamecore.Enum.DreamfireGameCoreMessage;
import com.dreamfirestudios.dreamgamecore.PulseConfig.DreamfireGameCoreConfig;
import com.dreamfirestudios.dreamgamecore.PulseConfig.DreamfireGameCoreMessages;
import org.bukkit.command.CommandSender;

import java.util.LinkedHashMap;

@PulseAutoRegister
public class DreamfireGameCoreServerCommand extends ServerCommand {

    public DreamfireGameCoreServerCommand() {
        super("DGC_server", false);
    }

    @Override
    public void NoMethodFound(CommandSender commandSender, String s, String[] strings) {}
    @Override
    public String helpMenuPrefix(CommandSender commandSender) {return "";}
    @Override
    public LinkedHashMap<String, String> helpMenuFormat(CommandSender commandSender, LinkedHashMap<String, String> linkedHashMap) {return new LinkedHashMap<>();}
    @Override
    public String helpMenuSuffix(CommandSender commandSender) {return "";}

    @PCMethod
    @PCSignature({})
    public void DreamfireGameCoreMethod(CommandSender commandSender){
        DreamfireGameCoreConfig.ReturnStaticAsync(DreamfireGameCoreConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            DreamfireGameCoreMessages.ReturnStaticAsync(DreamfireGameCoreMessages.class, craftLegendsCoreMessages -> {
                craftLegendsCoreMessages.SendMessageToConsole(DreamfireGameCoreMessage.SystemIsntEnabled);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"enable"})
    public void DreamfireGameCoreEnableMethod(CommandSender commandSender, boolean state){
        DreamfireGameCoreAPI.DreamfireGameCoreEnableSystem(state);
        DreamfireGameCoreMessages.ReturnStaticAsync(DreamfireGameCoreMessages.class, craftLegendsCoreMessages -> {
            craftLegendsCoreMessages.SendMessageToConsole(state ? DreamfireGameCoreMessage.ConsoleEnabledSystem : DreamfireGameCoreMessage.ConsoleDisableSystem);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"configs", "reset"})
    public void DreamfireGameCoreConfigsResetMethod(CommandSender commandSender){
        DreamfireGameCoreConfig.ReturnStaticAsync(DreamfireGameCoreConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            DreamfireGameCoreAPI.DreamfireGameCoreResetConfigs();
            DreamfireGameCoreMessages.ReturnStaticAsync(DreamfireGameCoreMessages.class, craftLegendsCoreMessages -> {
                craftLegendsCoreMessages.SendMessageToConsole(DreamfireGameCoreMessage.PlayerResetConfig);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"configs", "reload"})
    public void DreamfireGameCoreConfigsReloadMethod(CommandSender commandSender){
        DreamfireGameCoreConfig.ReturnStaticAsync(DreamfireGameCoreConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            DreamfireGameCoreAPI.DreamfireGameCoreResetConfigs();
            DreamfireGameCoreMessages.ReturnStaticAsync(DreamfireGameCoreMessages.class, craftLegendsCoreMessages -> {
                craftLegendsCoreMessages.SendMessageToConsole(DreamfireGameCoreMessage.PlayerReloadedConfig);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }
}

