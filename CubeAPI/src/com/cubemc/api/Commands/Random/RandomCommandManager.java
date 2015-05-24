package com.cubemc.api.Commands.Random;

import com.cubemc.api.Core.Module;
import com.cubemc.api.CubeAPI;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class RandomCommandManager extends Module {

    public RandomCommandManager(JavaPlugin plugin) {
        super("Random Command Manager", plugin);
    }

    @Override
    public void AddCommands() {
        CubeAPI.getCommandCenter().addComand(new HelpCmd(this));
    }
}
