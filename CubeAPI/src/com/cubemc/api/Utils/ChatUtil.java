package com.cubemc.api.Utils;

import org.bukkit.entity.Player;

/**
 * Created by henry on 4/4/15.
 */
public class ChatUtil {

    public ChatUtil(){}

    public static void broadcast(String msg){
        ServerUtil.getLog().info("[BROADCAST] " + msg);
        Player[] pl = (Player[])ServerUtil.getPlayers().toArray();
        for(Player p : pl){
            p.sendMessage(msg);
        }
    }

    public static void broadcast(String msg, boolean log){
        if(log)
            ServerUtil.getLog().info("[BROADCAST] " + msg);
        Player[] pl = (Player[])ServerUtil.getPlayers().toArray();
        for(Player p : pl){
            p.sendMessage(msg);
        }
    }

    public static void broadcast(String[] msg){
        Player[] pl = (Player[])ServerUtil.getPlayers().toArray();
        for(int i=0;i<msg.length;i++){
            ServerUtil.getLog().info("[BROADCAST] " + msg);
            for(int j=0;j<pl.length;j++)
                pl[i].sendMessage(msg[i]);
        }
    }

    public static void broadcast(String[] msg, boolean log){
        ServerUtil.getLog().info("[BROADCAST] " + msg);
        Player[] pl = (Player[])ServerUtil.getPlayers().toArray();
        for(int i=0;i<msg.length;i++){
            if(log)
                ServerUtil.getLog().info("[BROADCAST] " + msg);
            for(int j=0;j<pl.length;j++)
                pl[i].sendMessage(msg[i]);
        }
    }

}
