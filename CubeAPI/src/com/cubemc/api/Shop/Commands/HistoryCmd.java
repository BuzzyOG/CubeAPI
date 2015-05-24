package com.cubemc.api.Shop.Commands;

import com.cubemc.api.Commands.CommandBase;
import com.cubemc.api.Commands.Random.CMDh;
import com.cubemc.api.Ranks.Rank;
import com.cubemc.api.Shop.HistoryItem;
import com.cubemc.api.Shop.PurchaseHistory;
import com.cubemc.api.Utils.M;
import com.cubemc.api.Utils.MySQL;
import com.cubemc.api.Utils.UUIDh;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class HistoryCmd extends CommandBase<PurchaseHistory> {

    public HistoryCmd(PurchaseHistory plugin) {
        super(plugin, Rank.MEMBER, Arrays.asList("history", "ph", "purchases", "purchasehistory", "purchaseshistory"));
        CMDh.register("history", "", Rank.MEMBER, "Shows purchase history");
    }

    @Override
    public void execute(Player p, String[] args) {
        try {
            if (MySQL.doesTableContain("SELECT * FROM PurchaseHistory WHERE UUID='" + UUIDh.get(p) + "';")==false){
                p.sendMessage(M.error("You do not have any purchase history."));
                return;
            }

            if (PurchaseHistory.getHistory(p).size() > 54){
                p.sendMessage(M.error("Your history is too long to list. This will be fixed in a coming update."));
                return;
            }

            Inventory inv = Bukkit.createInventory(null, 54, "Purchase History");

            for (HistoryItem hi : PurchaseHistory.getHistory(p)){
                ItemStack i = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta im = i.getItemMeta();
                im.setDisplayName("§7Name: §d" + hi.getItemName());
                im.setLore(Arrays.asList("§7Category: §6" + hi.getCategory(), "§7Price: " + hi.getCurrencyType().getColour() + hi.getPrice() + " " + hi.getCurrencyType().getName()));
                i.setItemMeta(im);
                inv.addItem(i);
            }

            p.openInventory(inv);
        } catch (SQLException e) {
            p.sendMessage(M.error("An error occured, please try again later."));
            e.printStackTrace();
            return;
        }
    }
}
