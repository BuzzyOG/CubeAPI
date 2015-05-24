package com.cubemc.api.game.prevention.listeners;

import com.cubemc.api.CubeAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 07/04/2015
 */
public class MobsSpawn implements Listener {

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent e){
        if (CubeAPI.getGameManager().getGame() == null) return;
        if (CubeAPI.getGameManager().getGame().getSet().isCanMobsSpawn()) return;

        e.setCancelled(true);

    }

}
