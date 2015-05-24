package com.cubemc.api.Commands;

import com.cubemc.api.Core.Module;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Ranks.Rank;
import com.cubemc.api.Ranks.RankManager;
import com.cubemc.api.Utils.M;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public abstract class MultiCommandBase<PluginType extends Module> extends CommandBase<PluginType> {

    protected HashMap<String, CubeCommand> commands;

    protected MultiCommandBase(PluginType plugin, Rank requiredRank, List<String> aliases) {
        super(plugin, requiredRank, aliases);

        commands = new HashMap<String, CubeCommand>();
    }

    public void addCommand(CubeCommand command){
        for (String commandRoot : command.aliases()){
            commands.put(commandRoot, command);
            command.setCommandCenter(commandCenter);
        }
    }

    public void execute(Player sender, String[] args){
        String commandName = null;
        String[] newArgs = null;


        if (args==null || args.length==0){
            help(sender, new String[]{});
            return;
        }


        if ((args != null) && (args.length > 0)){
            commandName = args[0];

            if (args.length > 1){
                newArgs = new String[args.length - 1];

                for (int i = 0; i < newArgs.length; i++){
                    newArgs[i] = args[(i + 1)];
                }
            }
        }

        CubeCommand command = (CubeCommand)commands.get(commandName);

        if (command != null){
            if (RankManager.isRankHighEnough(sender, command.getRequiredRank())){
                command.setAliasUsed(commandName);
                command.execute(sender, newArgs);
            }else{
                sender.sendMessage(M.noRank(command.getRequiredRank()));
                return;
            }
        }else{
            help(sender, newArgs);
        }
    }

    public abstract void help(Player p, String[] args);
}