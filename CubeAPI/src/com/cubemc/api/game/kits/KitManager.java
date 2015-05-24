package com.cubemc.api.game.kits;

import com.cubemc.api.Core.Module;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Shop.ShopUtil;
import com.cubemc.api.Utils.M;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 07/04/2015
 */
public class KitManager extends Module {

    public KitManager(JavaPlugin plugin) {
        super("Kit Manager", plugin);
    }
    //--------------------------------------------------------------

    private HashMap<String, GameKit> playerKits = new HashMap<String, GameKit>();

    public GameKit getSelectedKit(Player p){
        if (playerKits.containsKey(p.getName())){
            return playerKits.get(p.getName());
        }else{
            if (CubeAPI.getGameManager().getGame().getGameKits() == null){
                Bukkit.broadcastMessage(M.fatal("§lKIT ERROR. §c(Getting selected kit returns null - KitManager.java)"));
                return null;
            }else{
                return CubeAPI.getGameManager().getGame().getGameKits().get(0);
            }
        }
    }

    public void setSelectedKit(Player p, GameKit kit){
        playerKits.put(p.getName(), kit);
        M.reg("You equipped kit §e§l" + kit.getName() + "§7.");
        return;
    }

    public boolean hasKit(GameKit kit, Player p){
        return ShopUtil.hasBoughtItem(kit.getItem(), p);
    }

    public void giveItem(Player p){
        ItemStack i = new ItemStack(Material.BLAZE_POWDER, 1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName("§d§lKIT SELECTOR §7(Click to open)");
        im.setLore(Arrays.asList("§eOpens the kit selection menu."));
        i.setItemMeta(im);
        p.getInventory().addItem(i);
    }

    public void openSelector(Player p){
        if (CubeAPI.getGameManager().getGame().isKitsEnabled() == false){
            p.sendMessage(M.error("This game does not have kits enabled."));
            return;
        }

        if (CubeAPI.getGameManager().getGame().getGameKits() == null){
            p.sendMessage(M.err(CubeAPI.getGameManager().getGame().getShortName(), "No kits have been added to the selector :("));
            return;
        }

        Inventory inv = Bukkit.createInventory(null, 54, "Kit Selector");

        for (GameKit kit : CubeAPI.getGameManager().getGame().getGameKits()) {
            if (hasKit(kit, p)) {
                ItemStack i = new ItemStack(kit.getIcon(), 1);
                ItemMeta im = i.getItemMeta();
                im.setDisplayName("§9§lKIT §a" + kit.getName());
                im.setLore(kit.getDesc());
                i.setItemMeta(im);
                inv.addItem(i);
            } else {
                ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1);
                ItemMeta im = i.getItemMeta();
                im.setDisplayName("§9§lKIT §c" + kit.getName());
                im.setLore(Arrays.asList("§7You must purchase this kit in the hub."));
                i.setItemMeta(im);
                inv.addItem(i);
            }
        }

        p.openInventory(inv);
    }



}
