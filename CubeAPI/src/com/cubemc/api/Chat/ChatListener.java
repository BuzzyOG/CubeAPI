package com.cubemc.api.Chat;

import com.cubemc.api.Ranks.RankManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class ChatListener implements Listener {

    @EventHandler
    public void onChat(PlayerChatEvent e){
        Player p = e.getPlayer();

        if (RankManager.isPlayerMember(p)){
            e.setFormat("§7" + p.getName() + " §8> §7" + (e.getMessage().replaceAll("%", "")));
        }else{
            e.setFormat(RankManager.getRank(p).getPrefix() + " §7" + p.getName() + " §8> §f" + (e.getMessage().replaceAll("%", "")));
        }
    }

}
