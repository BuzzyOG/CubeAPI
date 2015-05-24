package com.cubemc.api.game.kits;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.Utils.M;
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
 * 01/01/2015
 */
public class KitSelectorListener implements Listener {

    @EventHandler
    public void onOpen(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if (e.getItem() == null) return;
        if (e.getItem().getItemMeta() == null) return;
        if (e.getItem().getItemMeta().getDisplayName() == null) return;
        if (e.getItem().getItemMeta().getDisplayName().contains("§d§lKIT SELECTOR")){
            CubeAPI.getGameManager().getKitManager().openSelector(p);
            return;
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if (e.getWhoClicked() instanceof Player){
            Player p = (Player) e.getWhoClicked();
            boolean purchased = false;
            if (e.getCurrentItem() == null) return;
            if (e.getCurrentItem().getItemMeta() == null) return;
            if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
            String name = e.getCurrentItem().getItemMeta().getDisplayName();
            if (!(name.startsWith("§9§lKIT"))){
                return;
            }
            e.setCancelled(true);
            name = name.replace("§9§lKIT ", "");
            if (name.startsWith("§a")){
                purchased = true;
                name = name.replaceAll("§a", "");
            }else{
                name = name.replaceAll("§c", "");
            }
            GameKit kit = null;
            for (GameKit k : CubeAPI.getGameManager().getGame().getGameKits()){
                if (k.getName().equalsIgnoreCase(name)) kit = k;
            }
            if (kit == null){
                p.sendMessage(M.fatal("Kit doesn't exist: KitSelectorListener.java"));
                return;
            }

            if (purchased == true){
                CubeAPI.getGameManager().getKitManager().setSelectedKit(p, kit);
                return;
            }else{
                p.sendMessage(M.err(CubeAPI.getGameManager().getGame().getShortName(), "You don't own this kit."));
                return;
            }

        }
    }

}
