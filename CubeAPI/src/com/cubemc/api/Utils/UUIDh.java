package com.cubemc.api.Utils;

import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 06/04/2015
 */
public class UUIDh {

    public static String get(Player p){
        UUID uuid = p.getUniqueId();
        String suuid = uuid.toString();
        suuid = suuid.replaceAll("-", "");
        return suuid;
    }

    public static String get(UUID u){
        String suuid = u.toString();
        suuid = suuid.replaceAll("-", "");
        return suuid;
    }

}
