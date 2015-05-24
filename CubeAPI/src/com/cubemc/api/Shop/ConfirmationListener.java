package com.cubemc.api.Shop;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.Utils.M;
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
 * 10/12/2014
 */
public class ConfirmationListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == null) return;
        if (!(e.getInventory().getTitle().equalsIgnoreCase("Confirm Purchase"))) return;
        if (e.getCurrentItem().getType() == null) return;
        if (e.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)){
            if (e.getCurrentItem().getData().toString().equals("STAINED_GLASS_PANE(5)")){
                ShopItem i = ConfirmationPage.getShopItem(e.getInventory());
                if (i.isCanBePurchasedAgain() == true){
                    if (i.getActionIfAlreadyHad() == null){
                        p.sendMessage(M.error("This purchase has been cancelled because no action is defined for it. [ConfirmationListener.java]"));
                        return;
                    }
                    i.getActionIfAlreadyHad().onActivate(p, i);
                    Currency.removeCurrency(p, i.getType(), i.getPrice());
                }else{
                    ShopUtil.purchaseShopItem(i, p);
                }
                PurchaseHistory.addToHistory(p, i);
                p.sendMessage(CubeAPI.PREFIX + "You purchased §e" + i.getName() + "§7.");
                p.closeInventory();
                return;
            }else{
                p.closeInventory();
                return;
            }
        }
    }

}
