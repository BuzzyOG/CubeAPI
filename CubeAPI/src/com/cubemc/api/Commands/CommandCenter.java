package com.cubemc.api.Commands;

import com.cubemc.api.Core.Module;
import com.cubemc.api.Ranks.RankManager;
import com.cubemc.api.Utils.M;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 23/05/2015
 */
public class CommandCenter extends Module {

    protected JavaPlugin plugin;
    protected HashMap<String, CubeCommand> commands;

    public CommandCenter(JavaPlugin plugin) {
        super("Command Center", plugin);

        this.plugin = plugin;
        this.commands = new HashMap<String, CubeCommand>();
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        String commandName = e.getMessage().substring(1);
        String[] args = null;

        if (commandName.contains(" ")){
            commandName = commandName.split(" ")[0];
            args = e.getMessage().substring(e.getMessage().indexOf(' ') + 1).split(" ");
        }

        CubeCommand command = (CubeCommand)commands.get(commandName.toLowerCase());

        if ((command != null)){
            if (RankManager.isRankHighEnough(e.getPlayer(), command.getRequiredRank())){
                command.setAliasUsed(commandName.toLowerCase());
                command.execute(e.getPlayer(), args);

                e.setCancelled(true);
                return;
            }else{
                e.getPlayer().sendMessage(M.noRank(command.getRequiredRank()));
                e.setCancelled(true);
                return;
            }
        }
    }

    public void addComand(CubeCommand command){
        for (String commandRoot : command.aliases()){
            commands.put(commandRoot.toLowerCase(), command);
            command.setCommandCenter(this);
        }
    }

    public void removeCommand(CubeCommand command){
        for (String commandRoot : command.aliases()){
            commands.remove(commandRoot.toLowerCase());
            command.setCommandCenter(null);
        }
    }
}
