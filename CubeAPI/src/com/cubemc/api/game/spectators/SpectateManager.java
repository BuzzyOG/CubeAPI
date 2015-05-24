package com.cubemc.api.game.spectators;

import com.cubemc.api.Core.Module;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Utils.BungeeUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 07/04/2015
 */
public class SpectateManager extends Module {

    public SpectateManager(JavaPlugin plugin) {
        super("Spectate Manager", plugin);
    }

    private List<String> activeSpectators = new ArrayList<String>();

    public void makePlayer(Player p){
        try {
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.setAllowFlight(true);
            CubeAPI.getGameManager().getTeamManager().joinTeam(p, CubeAPI.getGameManager().getTeamManager().getTeam("Spectators"));
            ItemStack clock = new ItemStack(Material.WATCH, 1);
            ItemMeta cmeta = clock.getItemMeta();
            cmeta.setDisplayName("§c§lRETURN TO HUB §7(Click)");
            cmeta.setLore(Arrays.asList("§eReturns you to the hub, leaving the game."));
            clock.setItemMeta(cmeta);
            p.getInventory().setItem(8, clock);
            activeSpectators.add(p.getName());
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    @EventHandler
    public void onClock(PlayerInteractEvent e){
        if (e.getItem() == null) return;
        if (e.getItem().getType() == null) return;
        if (e.getItem().getType().equals(Material.WATCH)){
            if (CubeAPI.getGameManager().getTeamManager().getTeam(e.getPlayer()) != null){
                if (CubeAPI.getGameManager().getTeamManager().getTeam(e.getPlayer()).equals(CubeAPI.getGameManager().getTeamManager().getTeam("Spectators"))){
                    BungeeUtil.send(e.getPlayer(), "hub");
                    return;
                }
            }
        }
    }

    public List<String> getActiveSpectators() {
        return activeSpectators;
    }
}
