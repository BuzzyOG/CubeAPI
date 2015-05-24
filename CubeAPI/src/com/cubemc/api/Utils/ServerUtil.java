package com.cubemc.api.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created by henry on 4/2/15.
 */

public class ServerUtil {

    public ServerUtil(){}

    public static Server getServer(){
        return Bukkit.getServer();
    }

    public static Collection<? extends org.bukkit.entity.Player> getPlayers(){
        return getServer().getOnlinePlayers();
    }

    public static boolean isFull(){
        return getPlayers().size() >= getServer().getMaxPlayers();
    }

    public static double getPlayerPercent(){
        return (double)getPlayers().size() / (double)getServer().getMaxPlayers();
    }

    public static Logger getLog(){
        return Logger.getLogger("Minecraft");
    }

}
