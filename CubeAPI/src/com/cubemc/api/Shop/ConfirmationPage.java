package com.cubemc.api.Shop;

import com.cubemc.api.Ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 10/12/2014
 */
public class ConfirmationPage {

    private static HashMap<Inventory, ShopItem> invToItem = new HashMap<Inventory, ShopItem>();

    public static void openForPlayer(Player p, ShopItem item){
        Inventory inv = Bukkit.createInventory(null, 54, "Confirm Purchase");

        for (Integer n : Layout.getGreenGlass()){
            ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)0x5);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName("§a§l✔ CONFIRM PURCHASE");
            i.setItemMeta(im);
            inv.setItem(n, i);
        }

        for (Integer n : Layout.getRedGlass()){
            ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)0xE);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName("§c§l✘ CANCEL PURCHASE");
            i.setItemMeta(im);
            inv.setItem(n, i);
        }

        ItemStack i = new ItemStack(item.getIcon(), 1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName("§9§l" + item.getName());
        List<String> l = new ArrayList<String>();
        for (String s : item.getLore()) {
            l.add("§7" + s);
        }
        l.add("");
        l.add("§7Category: §6" + item.getCategory());
        l.add("§7Price: " + item.getType().getColour() + item.getPrice() + " " + item.getType().getName());
        if (!(item.getRank().equals(Rank.MEMBER))) {
            l.add("§7Rank: " + item.getRank().getPrefix());
        }
        im.setLore(l);
        i.setItemMeta(im);
        inv.setItem(22, i);

        invToItem.put(inv, item);
        p.openInventory(inv);
    }

    public static ShopItem getShopItem(Inventory inv){
        if (invToItem.containsKey(inv)){
            return invToItem.get(inv);
        }else return null;
    }

}
