package com.cubemc.api.game.maps;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.Ranks.Rank;
import com.cubemc.api.Ranks.RankManager;
import com.cubemc.api.Utils.M;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 07/04/2015
 */
public class MapSelectorListener implements Listener {

    @EventHandler
    public void onOpen(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if (e.getItem() == null) return;
        if (e.getItem().getItemMeta() == null) return;
        if (e.getItem().getItemMeta().getDisplayName() == null) return;
        if (e.getItem().getItemMeta().getDisplayName().contains("§6§lMAP SELECTOR")){
            CubeAPI.getGameManager().getMapManager().openSelector(p);
            return;
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;
        if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        String name = e.getCurrentItem().getItemMeta().getDisplayName();
        if (!(name.startsWith("§9§lKIT"))){
            return;
        }
        e.setCancelled(true);
        name = name.replace("§9§lMAP §a", "");
        GameMap map = null;
        for (GameMap m : CubeAPI.getGameManager().getMapManager().getSuppliedMaps()){
            if (m.getName().equals(name)) map = m;
        }
        if (map == null){
            p.sendMessage(M.game("§cMap doesn't exist. That's an error lol."));
            return;
        }

        if (CubeAPI.getGameManager().getMapManager().playerVotes.containsKey(p.getName())){
            if (RankManager.isRankHighEnough(p, Rank.VIP)){
                CubeAPI.getGameManager().getMapManager().playerVotes.put(p.getName(), map);
                Bukkit.broadcastMessage(M.game(p.getDisplayName() + " §7changed their vote to §e" + map.getName() + "§7."));
                return;
            }else{
                p.sendMessage(M.game("§cYou need to be a donor to change your vote."));
                return;
            }
        }else{
            CubeAPI.getGameManager().getMapManager().playerVotes.put(p.getName(), map);
            Bukkit.broadcastMessage(M.game(p.getDisplayName() + " §7voted for §e" + map.getName() + "§7."));
            return;
        }
    }

}
