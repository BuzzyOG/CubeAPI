package com.cubemc.api.Commands;

import com.cubemc.api.Commands.CommandCenter;
import com.cubemc.api.Ranks.Rank;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 23/05/2015
 */
public interface CubeCommand {

    void setCommandCenter(CommandCenter var1);

    void execute(Player p, String[] args);

    Collection<String> aliases();

    void setAliasUsed(String var1);

    Rank getRequiredRank();

}
