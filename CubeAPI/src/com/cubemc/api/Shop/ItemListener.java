package com.cubemc.api.Shop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 13/11/2014
 */
public class ItemListener implements Listener {

    @EventHandler
    public void on(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;
        if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        String name = e.getCurrentItem().getItemMeta().getDisplayName();
        name = name.replaceAll("§9§l", "");
        name = name.replaceAll("§d§lBUY ", "");
        ShopItem item = null;
        for (ShopItem i : ShopItem.allItems){
            if (i.getName().equalsIgnoreCase(name)){
                e.setCancelled(true);
                if (e.getInventory().getTitle().equalsIgnoreCase("Confirm Purchase")){
                    return;
                }
                if (ShopUtil.hasBoughtItem(i, p) == true){
                    if (i.isCanBeMadeActive()){
                        i.getActionIfAlreadyHad().onActivate(p, i);
                        return;
                    }
                    return;
                }
                if (ShopUtil.canBuyItem(i, p)){
                    ConfirmationPage.openForPlayer(p, i);
                    return;
                }else{
                    return;
                }
            }
        }
        if (item == null) {
            return;
        }

    }

}
