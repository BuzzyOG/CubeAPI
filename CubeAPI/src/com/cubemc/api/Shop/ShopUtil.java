package com.cubemc.api.Shop;

import com.cubemc.api.Ranks.RankManager;
import com.cubemc.api.Utils.MySQL;
import com.cubemc.api.Utils.UUIDh;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 15/11/2014
 */
public class ShopUtil {

    public static void purchaseShopItem(ShopItem i, Player p){
        String catName = i.getCategory().replaceAll(" ", "_");
        String tableName = catName + "_Shop_Category";
        createTable(i);
        try {
            createTable(i);
            List<String> packages = new ArrayList<String>();
            String packageString = MySQL.getResultsString("SELECT * FROM " + tableName + " WHERE UUID='" + UUIDh.get(p) + "';", "Packages");
            if (!(packageString.equals("LOL"))) {
                String[] array = packageString.split(",");
                for (String s : array){
                    packages.add(s);
                }
            }
            String newPackage = i.getName();
            newPackage = newPackage.replaceAll(" ", "-");
            packages.add(newPackage);
            String packSerial = "";
            for (String s : packages){
                if (packSerial.equals("")){
                    packSerial = s;
                    continue;
                }
                packSerial = packSerial + "," + s;
            }
            MySQL.connectToDB("INSERT INTO " + tableName + " (UUID, Packages) VALUES ('" + UUIDh.get(p) + "', '" + packSerial + "') ON DUPLICATE KEY UPDATE Packages='" + packSerial + "';");
            Currency.removeCurrency(p, i.getType(), i.getPrice());
            return;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean hasBoughtItem(ShopItem i, Player p){
        try {
            String catName = i.getCategory().replaceAll(" ", "_");
            String tableName = catName + "_Shop_Category";
            List<String> packages = new ArrayList<String>();
            String packageString = MySQL.getResultsString("SELECT * FROM " + tableName + " WHERE UUID='" + UUIDh.get(p) + "';", "Packages");
            if (packageString != null) {
                String[] array = packageString.split(",");
                for (String s : array){
                    packages.add(s);
                }
            }
            String itemName = i.getName();
            itemName = itemName.replaceAll(" ", "-");
            if (packages.contains(itemName)){
                return true;
            }else return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean canBuyItem(ShopItem i, Player p){
        if (i == null) {
            p.sendMessage("i is null 2");
            return false;
        }
        if (i.getRank() == null){
            p.sendMessage("rank null");
            return false;
        }
        if (RankManager.isRankHighEnough(p, i.getRank())){
            if (Currency.hasEnough(p, i.getType(), i.getPrice())){
                return true;
            }
        }
        return false;
    }

    public static void createTable(ShopItem i){
        String catName = i.getCategory().replaceAll(" ", "_");
        String tableName = catName + "_Shop_Category";
        try {
            MySQL.connectToDB("CREATE TABLE IF NOT EXISTS " + tableName + " (UUID VARCHAR(200), Packages VARCHAR(5000), PRIMARY KEY (UUID));");
            System.out.println("[MYSQL] Created table " + tableName + ".");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
