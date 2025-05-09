package com.dreamfirestudios.dreamgamecore.API;

import com.infernalsuite.aswm.api.AdvancedSlimePaperAPI;
import com.infernalsuite.aswm.api.exceptions.*;
import com.infernalsuite.aswm.api.loaders.SlimeLoader;
import com.infernalsuite.aswm.api.world.SlimeWorld;
import com.infernalsuite.aswm.loaders.file.FileLoader;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class AdvancedSlimeManagerAPI {

    private static AdvancedSlimePaperAPI GetAdvancedSlimePaperAPI() {
        return AdvancedSlimePaperAPI.instance();
    }

    private static SlimeLoader GetSlimeLoader() {
        return new FileLoader(new File("slime_worlds"));
    }

    private static String CleanWorldName(String worldName){
        return worldName.replace(" ", "_");
    }

    public static Location ConvertLocation(UUID slimeWorldName, Location location){
        var world = Bukkit.getWorld(slimeWorldName.toString());
        return new Location(world, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public static SlimeWorld CloneSlimeWorld(String worldName, String newWorldName){
        try {
            var slimeWold = GetAdvancedSlimePaperAPI().getLoadedWorld(CleanWorldName(worldName));
            var cloneWorld = slimeWold.clone(newWorldName, GetSlimeLoader());
            GetAdvancedSlimePaperAPI().loadWorld(cloneWorld, true);
            return slimeWold;
        } catch (WorldAlreadyExistsException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SlimeWorld ReadVanillaWorld(String worldName) {
        try {
            File baseFolder = Bukkit.getServer().getWorldContainer();
            File worldFolder = new File(baseFolder, worldName);
            var slimeWorld = GetAdvancedSlimePaperAPI().readVanillaWorld(worldFolder, CleanWorldName(worldName), GetSlimeLoader());
            GetAdvancedSlimePaperAPI().saveWorld(slimeWorld);
            Bukkit.getConsoleSender().sendMessage(NamedTextColor.GREEN + "ReadVanillaWorld (" + worldName + "):" + CleanWorldName(worldName));
            return slimeWorld;
        } catch (InvalidWorldException | WorldLoadedException | WorldTooBigException | IOException | WorldAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }
}