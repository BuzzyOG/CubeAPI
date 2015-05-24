package com.cubemc.api.Utils;

import com.cubemc.api.CubeAPI;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;

/**
 * Created by henry on 4/2/15.
 */
public class BungeeUtil {

    public BungeeUtil(){}

    public static void send(Player p, String server){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        p.sendPluginMessage(CubeAPI.getPlugin(), "BungeeCord", out.toByteArray());
    }

}
