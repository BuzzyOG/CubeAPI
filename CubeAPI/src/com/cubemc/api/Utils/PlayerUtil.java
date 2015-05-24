package com.cubemc.api.Utils;

import org.bukkit.entity.Player;

/**
 * Created by henry on 4/2/15.
 */
public class PlayerUtil {

    public PlayerUtil(){}

    public static String getUUID(Player p){
        return p.getUniqueId().toString().replaceAll("-","");
    }

    public static String getIP(Player p) {
        return p.getAddress().getHostName().replaceAll(".", "");
    }

    public static void msg(Player p, String[] msg){
        for(int i=0;i<msg.length;i++){
            p.sendMessage(msg[i]);
        }
    }

    public static void msg(Player[] p, String msg){
        for(int i=0;i<p.length;i++){
            p[i].sendMessage(msg);
        }
    }

    public static void msg(Player[] p, String[] msg){
        for(int i=0;i<p.length;i++){
            for(int j=0;j<msg.length;j++){
                p[i].sendMessage(msg[i]);
            }
        }
    }

    public static Player find(String name){
        if(ServerUtil.getServer().getPlayer(name) != null)
            return ServerUtil.getServer().getPlayer(name);
        return null;
    }

    public static Player findExact(String name){
        Player[] p = (Player[])ServerUtil.getPlayers().toArray();
        for(int i=0;i<p.length;i++){
            if(p[i].getName().equalsIgnoreCase(name))
                return p[i];
        }
        return null;
    }

    public static void kick(Player p, String msg){
        p.kickPlayer(msg);
    }

    public static boolean kick(String p, String msg){
        Player pl = find(p);
        if(pl == null)
            return false;
        pl.kickPlayer(msg);
        return true;
    }

    public static void setHealth(Player p, double h){
        if(p.isDead())
            return;
        if(h > p.getMaxHealth())
            p.setHealth(h);
        p.setHealth(h);
    }

    public static void setHunger(Player p, int h){
        if(p.isDead())
            return;
        if(h > 20)
            p.setFoodLevel(20);
        p.setFoodLevel(h);
    }

}
