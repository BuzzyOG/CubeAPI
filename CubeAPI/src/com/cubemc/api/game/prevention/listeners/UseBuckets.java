package com.cubemc.api.game.prevention.listeners;

import com.cubemc.api.CubeAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 07/04/2015
 */
public class UseBuckets implements Listener {

    @EventHandler
    public void onUse(PlayerBucketEvent e){
        Player p = e.getPlayer();

        if (CubeAPI.getGameManager().getGame() == null) return;
        if (CubeAPI.getGameManager().getGame().getSet().isCanPlayersUseBuckets()) return;

        e.setCancelled(true);
        p.sendMessage("§4§lNO! » §cUse of buckets is disabled.");
        return;
    }

}
