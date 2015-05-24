package com.cubemc.api.Shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 12/11/2014
 */
public class ShopPage implements Serializable {


    private ShopMenu parentMenu;
    private int pageNumber;
    private List<ShopItem> pageItems = new ArrayList<ShopItem>();
    private static HashMap<Inventory, ShopPage> invPages = new HashMap<Inventory, ShopPage>();
    public static List<Inventory> allInvs = new ArrayList<Inventory>();
    public static HashMap<Inventory, ShopMenu> invsInShopMenu = new HashMap<Inventory, ShopMenu>();

    public ShopPage(ShopMenu parentMenu, int pageNumber) {
        this.parentMenu = parentMenu;
        this.pageNumber = pageNumber;
    }

    public ShopMenu getParentMenu() {
        return parentMenu;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public List<ShopItem> getPageItems() {
        return pageItems;
    }

    public void addItem(ShopItem item) {
        pageItems.add(item);
    }

    public void openForPlayer(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, parentMenu.getName() + " » Page " + pageNumber);

        for (Integer i : Layout.getAllGlassInts()) {
            ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0x0);
            ItemMeta gmeta = glass.getItemMeta();
            gmeta.setDisplayName(" ");
            glass.setItemMeta(gmeta);
            inv.setItem(i, glass);
        }

        ItemStack icon = new ItemStack(parentMenu.getIcon(), 1);
        ItemMeta im = icon.getItemMeta();
        im.setDisplayName("§b§l" + parentMenu.getName());
        im.setLore(Arrays.asList("§7Page " + pageNumber));
        icon.setItemMeta(im);
        inv.setItem(4, icon);

        if (pageNumber == 1) {
            ItemStack a = new ItemStack(Material.ARROW, 1);
            ItemMeta am = a.getItemMeta();
            am.setDisplayName("§7« §6Go Back");
            a.setItemMeta(am);
            inv.setItem(45, a);
        } else {
            ItemStack a = new ItemStack(Material.ARROW, 1);
            ItemMeta am = a.getItemMeta();
            am.setDisplayName("§7« §6Previous Page");
            am.setLore(Arrays.asList("§7Page §c" + (pageNumber - 1) + " §7of §c" + parentMenu.getPages().size()));
            a.setItemMeta(am);
            inv.setItem(45, a);
        }

        if (pageNumber == parentMenu.getPages().size()) {
            ItemStack a = new ItemStack(Material.ARROW, 1);
            ItemMeta am = a.getItemMeta();
            am.setDisplayName("§6Last Page");
            am.setLore(Arrays.asList("§7Page §c" + pageNumber + " §7of §c" + pageNumber));
            a.setItemMeta(am);
            inv.setItem(53, a);
        } else {
            ItemStack a = new ItemStack(Material.ARROW, 1);
            ItemMeta am = a.getItemMeta();
            am.setDisplayName("§6Next Page §7»");
            am.setLore(Arrays.asList("§7Page §c" + pageNumber + " §7of §c" + parentMenu.getPages().size()));
            a.setItemMeta(am);
            inv.setItem(53, a);
        }

        ItemStack b = new ItemStack(Material.EMERALD, 1);
        ItemMeta bm = b.getItemMeta();
        bm.setDisplayName("§6§lYour Balance:");
        bm.setLore(Arrays.asList("§7Gems: §a" + Currency.getGems(p), "§7Credits: §b" + Currency.getCredits(p)));
        b.setItemMeta(bm);
        inv.setItem(49, b);

        int start = 19;
        for (ShopItem item : pageItems) {
            if (start == 26){
                start = start + 2;
            }
            inv.setItem(start, item.getItemStack(p));
            start++;
        }

        allInvs.add(inv);
        invsInShopMenu.put(inv, parentMenu);
        invPages.put(inv, this);
        p.openInventory(inv);
        return;
    }

    public static ShopPage getPageByInventory(Inventory inv){
        if (invPages.containsKey(inv)){
            return invPages.get(inv);
        }else{
            return null;
        }
    }
}
