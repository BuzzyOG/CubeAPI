package com.cubemc.api.game.lobby;

import com.cubemc.api.Core.Module;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 07/04/2015
 */
public class LobbyManager extends Module {

    public LobbyManager(JavaPlugin plugin) {
        super("Lobby Manager", plugin);
    }

    private Location spawnPoint = new Location(Bukkit.getWorld("lobby"), 0, 0, 0);

    public void sendPlayerToLobby(Player p){
        if (Bukkit.getWorld("lobby") == null){
            //Bukkit.broadcastMessage("world null");
            return;
        }
        p.teleport(new Location(Bukkit.getWorld("lobby"), 0, 0, 0));
    }

}
