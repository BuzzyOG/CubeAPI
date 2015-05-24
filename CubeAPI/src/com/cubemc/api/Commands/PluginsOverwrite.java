package com.cubemc.api.Commands;

import com.cubemc.api.Utils.M;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class PluginsOverwrite implements Listener {

    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();
        if (e.getMessage().startsWith("/bukkit") || e.getMessage().startsWith("/BUKKIT")){
            e.setCancelled(true);
            p.sendMessage(M.reg("Don't be so cheeky with /bukkit ;)"));
            return;
        }else if (e.getMessage().equalsIgnoreCase("/plugins") ||
                e.getMessage().equalsIgnoreCase("/pl") ||
                e.getMessage().equalsIgnoreCase("/ver") ||
                e.getMessage().equalsIgnoreCase("/version")){
            e.setCancelled(true);
            p.sendMessage(M.reg("§bRunning §aCubeAPI §bby §cWilliamTiger §b:D"));
        }
    }

}
