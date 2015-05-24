package com.cubemc.api.Shop;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Utils.MySQL;
import com.cubemc.api.Utils.UUIDh;
import org.bukkit.entity.Player;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 11/11/2014
 */
public class Currency {

    public static boolean hasEnough(Player p, CurrencyType type, int amount){
        if (type.equals(CurrencyType.GEMS)){
            try {
                int pa = MySQL.getResultsInteger("SELECT * FROM PlayerData WHERE PlayerName='" + p.getName() + "';", "Gems");
                if (pa >= amount) return true;
                return false;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (type.equals(CurrencyType.CREDITS)){
            try {
                int pa = MySQL.getResultsInteger("SELECT * FROM PlayerData WHERE PlayerName='" + p.getName() + "';", "Credits");
                if (pa >= amount) return true;
                return false;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static int getGems(Player p){
        try {
            int pa = MySQL.getResultsInteger("SELECT * FROM PlayerData WHERE PlayerName='" + p.getName() + "';", "Gems");
            return pa;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static int getCredits(Player p){
        try {
            int pa = MySQL.getResultsInteger("SELECT * FROM PlayerData WHERE PlayerName='" + p.getName() + "';", "Credits");
            return pa;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static void addCurrency(Player p, CurrencyType type, int amount){
        try {
            if (type.equals(CurrencyType.GEMS)){
                MySQL.connectToDB("INSERT INTO PlayerData (UUID, Gems) VALUES ('" + UUIDh.get(p) + "', " + amount + ") ON DUPLICATE KEY UPDATE Gems=Gems+" + amount);
                p.sendMessage(CubeAPI.PREFIX + "§a[+" + amount + " Gems]");
                return;
            }else if (type.equals(CurrencyType.CREDITS)){
                MySQL.connectToDB("INSERT INTO PlayerData (UUID, Credits) VALUES ('" + UUIDh.get(p) + "', " + amount + ") ON DUPLICATE KEY UPDATE Credits=Credits+" + amount);
                p.sendMessage(CubeAPI.PREFIX + "§b[+" + amount + " Credits]");
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addCurrency(Player p, CurrencyType type, int amount, String reason){
        try {
            if (type.equals(CurrencyType.GEMS)){
                MySQL.connectToDB("INSERT INTO PlayerData (UUID, Gems) VALUES ('" + UUIDh.get(p) + "', " + amount + ") ON DUPLICATE KEY UPDATE Gems=Gems+" + amount);
                p.sendMessage(CubeAPI.PREFIX + "§a[+" + amount + " Gems§7 (" + reason + ")§a]");
                return;
            }else if (type.equals(CurrencyType.CREDITS)){
                MySQL.connectToDB("INSERT INTO PlayerData (UUID, Credits) VALUES ('" + UUIDh.get(p) + "', " + amount + ") ON DUPLICATE KEY UPDATE Credits=Credits+" + amount);
                p.sendMessage(CubeAPI.PREFIX + "§b[+" + amount + " Credits§7 (" + reason + ")§b]");
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void removeCurrency(Player p, CurrencyType type, int amount){
        try {
            if (type.equals(CurrencyType.GEMS)){
                MySQL.connectToDB("INSERT INTO PlayerData (UUID, Gems) VALUES ('" + UUIDh.get(p) + "', -" + amount + ") ON DUPLICATE KEY UPDATE Gems=Gems-" + amount);
                p.sendMessage(CubeAPI.PREFIX + "§c[-" + amount + " Gems]");
                return;
            }else if (type.equals(CurrencyType.CREDITS)){
                MySQL.connectToDB("INSERT INTO PlayerData (UUID, Credits) VALUES ('" + UUIDh.get(p) + "', -" + amount + ") ON DUPLICATE KEY UPDATE Credits=Credits-" + amount);
                p.sendMessage(CubeAPI.PREFIX + "§c[-" + amount + " Credits]");
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setCurrency(Player p, CurrencyType type, int amount){
        try {
            if (type.equals(CurrencyType.GEMS)){
                MySQL.connectToDB("INSERT INTO PlayerData (UUID, Gems) VALUES ('" + UUIDh.get(p) + "', -" + amount + ") ON DUPLICATE KEY UPDATE Gems=" + amount);
                p.sendMessage(CubeAPI.PREFIX + "§a[Set to " + amount + " Gems]");
                return;
            }else if (type.equals(CurrencyType.CREDITS)){
                MySQL.connectToDB("INSERT INTO PlayerData (UUID, Credits) VALUES ('" + UUIDh.get(p) + "', -" + amount + ") ON DUPLICATE KEY UPDATE Credits=" + amount);
                p.sendMessage(CubeAPI.PREFIX + "§b[Set to " + amount + " Credits]");
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
