package com.cubemc.api.Shop;

import com.cubemc.api.Core.Module;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Shop.Commands.HistoryCmd;
import com.cubemc.api.Utils.MySQL;
import com.cubemc.api.Utils.UUIDh;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 05/03/2015
 */
public class PurchaseHistory extends Module {

    public PurchaseHistory(JavaPlugin plugin) {
        super("Purchase History", plugin);
    }

    @Override
    public void AddCommands() {
        CubeAPI.getCommandCenter().addComand(new HistoryCmd(this));
    }

    public static void addToHistory(Player p, ShopItem item){
        try {
            MySQL.connectToDB("INSERT INTO PurchaseHistory (UUID, PlayerNameUsed, ItemName, Category, Price, CurrencyType) VALUES ('" + UUIDh.get(p) + "', '" + p.getName() + "', '" + item.getName() + "', '" + item.getCategory() + "', " + item.getPrice() + ", '" + item.getType().toString() + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void click(InventoryClickEvent e){
        if (e.getInventory().getName()==null) return;
        if (e.getInventory().getName().equals("Purchase History")){
            e.setCancelled(true);
        }
    }

    public static List<HistoryItem> getHistory(Player p){
        List<HistoryItem> history = new ArrayList<HistoryItem>();

        try {
            for (String item : MySQL.getListStringDB("SELECT * FROM PurchaseHistory WHERE UUID='" + UUIDh.get(p) + "';", "ItemName")){
                String UUID = UUIDh.get(p);
                String PlayerNameUsed = MySQL.getResultsString("SELECT * FROM PurchaseHistory WHERE UUID='" + UUID + "' AND ItemName='" + item + "';", "PlayerNameUsed");
                String ItemName = MySQL.getResultsString("SELECT * FROM PurchaseHistory WHERE UUID='" + UUID + "' AND ItemName='" + item + "';", "ItemName");
                String Category = MySQL.getResultsString("SELECT * FROM PurchaseHistory WHERE UUID='" + UUID + "' AND ItemName='" + item + "';", "Category");
                int Price = MySQL.getResultsInteger("SELECT * FROM PurchaseHistory WHERE UUID='" + UUID + "' AND ItemName='" + item + "';", "Price");
                String CurrencyType = MySQL.getResultsString("SELECT * FROM PurchaseHistory WHERE UUID='" + UUID + "' AND ItemName='" + item + "';", "CurrencyType");
                HistoryItem hi = new HistoryItem(UUID, PlayerNameUsed, ItemName, Category, Price, CurrencyType);
                history.add(hi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return history;
    }

}
