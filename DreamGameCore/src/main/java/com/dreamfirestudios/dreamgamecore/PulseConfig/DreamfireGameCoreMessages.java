package com.dreamfirestudios.dreamgamecore.PulseConfig;

import com.dreamfirestudios.dreamConfig.Abstract.StaticEnumPulseConfig;
import com.dreamfirestudios.dreamCore.DreamfireChat.DreamfireChat;
import com.dreamfirestudios.dreamCore.DreamfireChat.DreamfireMessage;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamgamecore.DreamGameCore;
import com.dreamfirestudios.dreamgamecore.Enum.DreamfireGameCoreMessage;
import com.dreamfirestudios.dreamgamecore.Enum.DreamfireGameCorePermission;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@PulseAutoRegister
public class DreamfireGameCoreMessages extends StaticEnumPulseConfig<DreamfireGameCoreMessages, DreamfireGameCoreMessage, String> {
    @Override
    public JavaPlugin mainClass() {return DreamGameCore.GetDreamfireGameCore();}
    @Override
    protected Class<DreamfireGameCoreMessage> getKeyClass() { return DreamfireGameCoreMessage.class;}
    @Override
    protected Class<String> getValueClass() {return String.class; }
    @Override
    protected String getDefaultValueFor(DreamfireGameCoreMessage craftLegendsCoreMessage) { return craftLegendsCoreMessage.message; }

    public void SendMessageToBroadcast(DreamfireGameCoreMessage message, Object... objects){
        var storedMessage = getDefaultValueFor(message);
        if(storedMessage.isEmpty()) return;
        var cleanedMessage = DreamfireMessage.formatMessage(String.format(storedMessage, objects));
        DreamfireChat.BroadcastMessage(PlainTextComponentSerializer.plainText().serialize(cleanedMessage));
    }

    public void SendMessageToPlayerPermission(DreamfireGameCoreMessage message, DreamfireGameCorePermission nexusCorePermission, Object... objects){
        DreamfireGameCorePermissions.ReturnStaticAsync(DreamfireGameCorePermissions.class, craftLegendsCorePermissions -> {
            for(var player : Bukkit.getOnlinePlayers()){
                if(!craftLegendsCorePermissions.DoesPlayerHavePermission(nexusCorePermission, player, false)) continue;
                SendMessageToPlayer(message, player, objects);
            }
        }, Throwable::printStackTrace);
    }

    public void SendMessageToPlayer(DreamfireGameCoreMessage message, Player player, Object... objects){
        if(player == null || message == null) return;
        var storedMessage = getDefaultValueFor(message);
        if(storedMessage.isEmpty()) return;
        var cleanedMessage = DreamfireMessage.formatMessage(String.format(storedMessage, objects), player);
        DreamfireChat.SendMessageToPlayer(player, PlainTextComponentSerializer.plainText().serialize(cleanedMessage));
    }

    public void SendMessageToContext(DreamfireGameCoreMessage message, Player player, ConversationContext conversationContext, Object... objects){
        if(player == null || message == null) return;
        var storedMessage = getDefaultValueFor(message);
        if(storedMessage.isEmpty()) return;
        var cleanedMessage = DreamfireMessage.formatMessage(String.format(storedMessage, objects), player);
        conversationContext.getForWhom().sendRawMessage(PlainTextComponentSerializer.plainText().serialize(cleanedMessage));
    }

    public void SendMessageToConsole(DreamfireGameCoreMessage message, Object... objects){
        if(message == null) return;
        var storedMessage = getDefaultValueFor(message);
        if(storedMessage.isEmpty()) return;
        var cleanedMessage = DreamfireMessage.formatMessage(String.format(storedMessage, objects));
        DreamfireChat.SendMessageToConsole(PlainTextComponentSerializer.plainText().serialize(cleanedMessage));
    }
}
