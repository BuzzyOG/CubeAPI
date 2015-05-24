package com.cubemc.api.game.events;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.Ranks.RankManager;
import com.cubemc.api.Utils.M;
import com.cubemc.api.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 07/04/2015
 */
public class PlayerJoin implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        Player p = e.getPlayer();

        if (CubeAPI.getGameManager().getGame() == null) return;

        if (!(CubeAPI.getGameManager().getGame().getState().isJoinable())){
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Game can't be joined at this time!");
            return;
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        RankManager.setupRank(p);

        if (CubeAPI.getGameManager().getGame() == null) return;

        if (CubeAPI.getGameManager().getGame().getState().equals(GameState.STARTING) || CubeAPI.getGameManager().getGame().getState().equals(GameState.WAITING)){
            Bukkit.broadcastMessage(M.game(p.getDisplayName() + " §7joined the game."));
            CubeAPI.getGameManager().getKitManager().giveItem(p);
            if (CubeAPI.getGameManager().getMapManager().isVotingEnabled()){
                CubeAPI.getGameManager().getMapManager().giveItem(p);
            }
            CubeAPI.getGameManager().getLobbyManager().sendPlayerToLobby(p);
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.setFoodLevel(20);
            p.setHealth(20);
            p.setAllowFlight(false);
            p.setFlying(false);
            return;
        }else if (CubeAPI.getGameManager().getGame().getState().equals(GameState.INGAME)){
            Bukkit.broadcastMessage(M.game(p.getDisplayName() + " §7is now spectating."));
            CubeAPI.getGameManager().getSpectateManager().makePlayer(p);
            CubeAPI.getGameManager().getLobbyManager().sendPlayerToLobby(p);
            return;
        }
    }

}
