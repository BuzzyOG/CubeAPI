package com.cubemc.api.game.prevention.listeners;

import com.cubemc.api.CubeAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 07/04/2015
 */
public class PlayersTakeDamage implements Listener {

    @EventHandler
    public void damage(EntityDamageEvent e){
        if (CubeAPI.getGameManager().getGame() == null) return;
        if (CubeAPI.getGameManager().getGame().getSet().isCanPlayersTakeDamage()) return;

        if (e.getEntity() instanceof Player){
            e.setCancelled(true);
            return;
        }
    }

}
