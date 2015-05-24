package com.cubemc.api.Shop;

import com.cubemc.api.CubeAPI;
import org.bukkit.Material;
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
public class PageListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if (e.getWhoClicked() == null) return;
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory() == null) return;
        if (ShopPage.allInvs.contains(e.getInventory())){
            e.setCancelled(true);

            if (e.getCurrentItem() == null) return;
            if (e.getCurrentItem().getItemMeta() == null) return;
            if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
            if (e.getCurrentItem().getType() == null) return;
            String name = e.getCurrentItem().getItemMeta().getDisplayName();
            if (e.getCurrentItem().getType().equals(Material.ARROW)){
                if (name.contains("§6Go Back")){
                    ShopMenu menu = ShopPage.invsInShopMenu.get(e.getInventory());
                    if (menu.getGoBackAction() == null){
                        p.closeInventory();
                        p.sendMessage(CubeAPI.PREFIX + "No [GO BACK ACTION] defined! Please report.");
                        return;
                    }
                    menu.getGoBackAction().onGoBackAction(p, menu);
                    return;
                }else if (name.contains("§6Previous Page")){
                    ShopPage page = ShopPage.getPageByInventory(e.getInventory());
                    int pNumber = page.getPageNumber();
                    page.getParentMenu().getPages().get((pNumber - 2)).openForPlayer(p);
                    return;
                }else if (name.contains("§6Next Page")){
                    ShopPage page = ShopPage.getPageByInventory(e.getInventory());
                    int pNumber = page.getPageNumber();
                    page.getParentMenu().getPages().get(pNumber).openForPlayer(p);
                    return;
                }
            }
        }
    }

}
