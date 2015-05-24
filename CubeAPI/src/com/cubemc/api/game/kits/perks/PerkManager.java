package com.cubemc.api.game.kits.perks;

import com.cubemc.api.Core.Module;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.game.GameState;
import com.cubemc.api.game.events.UpdateEvent;
import com.cubemc.api.game.kits.GameKit;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 07/04/2015
 */
public class PerkManager extends Module {

    public PerkManager(JavaPlugin plugin) {
        super("Perk Manager", plugin);
    }

    @EventHandler
    public void onUpdate(UpdateEvent e){
        if (CubeAPI.getGameManager().getGame() == null) return;
        if (CubeAPI.getGameManager().getGame().getState() != GameState.INGAME) return;
        if (CubeAPI.getGameManager().getGame().isKitsEnabled() == false) return;
        if (CubeAPI.getGameManager().getGame().getGameKits() == null) return;
        for (GameKit kit : CubeAPI.getGameManager().getGame().getGameKits()){
            for (Perk perk : kit.getPerks()){
                perk.backgroundStart();
            }
        }
    }
    
}
