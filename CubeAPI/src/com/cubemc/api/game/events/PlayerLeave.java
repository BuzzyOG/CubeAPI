package com.cubemc.api.game.events;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.Utils.M;
import com.cubemc.api.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 07/04/2015
 */
public class PlayerLeave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();

        if (CubeAPI.getGameManager().getGame() == null) return;

        if (CubeAPI.getGameManager().getGame().getState().equals(GameState.WAITING) || CubeAPI.getGameManager().getGame().getState().equals(GameState.STARTING)){
            Bukkit.broadcastMessage(M.game(p.getDisplayName() + " §7left the game."));
            return;
        }else if (CubeAPI.getGameManager().getGame().getState().equals(GameState.INGAME)){
            if (CubeAPI.getGameManager().getSpectateManager().getActiveSpectators().contains(p.getName())){
                Bukkit.broadcastMessage(M.game(p.getDisplayName() + " §7is no longer spectating."));
                CubeAPI.getGameManager().getTeamManager().leaveTeam(p);
                return;
            }else{
                Bukkit.broadcastMessage(M.game(p.getDisplayName() + " §7left the game."));
                CubeAPI.getGameManager().getTeamManager().leaveTeam(p);
                return;
            }
        }
    }

}
