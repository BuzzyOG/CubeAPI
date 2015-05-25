package com.cubemc.api.game.maps;

import com.cubemc.api.Core.Module;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Utils.M;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 06/04/2015
 */
public class MapManager extends Module {

    private List<GameMap> suppliedMaps;

    public HashMap<String, GameMap> playerVotes = new HashMap<String, GameMap>();

    private boolean votingEnabled = false;

    public MapManager(JavaPlugin plugin) {
        super("Map Manager", plugin);
    }

    public void giveSuppliedMaps(List<GameMap> maps){
        suppliedMaps = maps;
        return;
    }

    public List<GameMap> getSuppliedMaps() {
        return suppliedMaps;
    }

    public void checkForVoting(){
        if (suppliedMaps.size() == 1){
            CubeAPI.getGameManager().getGame().setCurrentMap(suppliedMaps.get(0));
            setupMap(CubeAPI.getGameManager().getGame().getCurrentMap(), 0);
            return;
        }else{
            votingEnabled = true;
            return;
        }
    }

    public boolean isVotingEnabled() {
        return votingEnabled;
    }

    public void giveItem(Player p){
        ItemStack i = new ItemStack(Material.BOOK, 1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName("§6§lMAP SELECTOR §7(Click to open)");
        im.setLore(Arrays.asList("§eOpens the map voting menu."));
        i.setItemMeta(im);
        p.getInventory().addItem(i);
    }

    public void openSelector(Player p){
        if (!(isVotingEnabled())){
            p.sendMessage(M.game("§cMap voting is not enabled for this game."));
            return;
        }

        if (suppliedMaps.size() == 0){
            p.sendMessage(M.fatal("No maps have been added to the selector."));
            return;
        }

        if (CubeAPI.getGameManager().getGame().getTicks() < 15){
            p.sendMessage(M.game("§cMap voting has ended."));
            return;
        }

        Inventory inv = Bukkit.createInventory(null, 54, "Map Selector");

        for (GameMap map : suppliedMaps){

            ItemStack i = new ItemStack(Material.MAP, 1);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName("§9§lMAP §a" + map.getName());
            im.setLore(Arrays.asList("§eby " + map.getAuthor(), "", "§7Votes: §6" + getVotes(map)));
            i.setItemMeta(im);

            inv.addItem(i);
        }

        p.openInventory(inv);
    }

    public void setupMap(GameMap map, int votes){
        CubeAPI.getGameManager().getGame().setCurrentMap(map);
        Bukkit.broadcastMessage(M.reg("Map §e" + map.getName() + " §7has won the voting with §e" + votes + " votes§7."));
        Bukkit.broadcastMessage(M.game("§oStarting map setup..."));
        Bukkit.broadcastMessage(M.game("§oCopying folder from maps directory..."));
        try {
            copyFolder(new File(CubeAPI.getPlugin().getDataFolder() + File.separator + "maps" + File.separator + map.getFolderName()), new File(Bukkit.getWorldContainer() + File.separator + map.getFolderName()));
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.broadcastMessage(M.game("§cMAP SETUP FAILED."));
            Bukkit.shutdown();
            return;
        }
        Bukkit.broadcastMessage(M.game("§oFolder copied."));
        Bukkit.broadcastMessage(M.game("§oLoading world into server...."));
        Bukkit.createWorld(new WorldCreator(map.getFolderName()));
        Bukkit.broadcastMessage(M.game("§oLoaded world."));
        Bukkit.broadcastMessage(M.game("§aComplete!"));
    }

    public int getVotes(GameMap map){
        int votes = 0;
        for (GameMap amap : playerVotes.values()){
            if (map==amap){
                votes++;
            }
        }
        return votes;
    }

    public void setupVotedMap(){
        if (votingEnabled == false) return;

        GameMap map = suppliedMaps.get(0);
        int highestVotes = getVotes(map);

        for (GameMap m : suppliedMaps){
            if (getVotes(m) > highestVotes){
                map = m;
                highestVotes = getVotes(m);
            }
        }

        setupMap(map, highestVotes);
    }

    //RESET MAPS.

    public void deleteOldMap(final GameMap map){
        Bukkit.unloadWorld(map.getFolderName(), false);
        //Now wait 60 ticks for it to be unloaded.
        Bukkit.getScheduler().scheduleSyncDelayedTask(CubeAPI.getPlugin(), new Runnable() {
            @Override
            public void run() {
                deleteFile(new File(Bukkit.getWorldContainer() + File.separator + map.getFolderName()));
            }
        }, 60L);
    }

    public void deleteFile(File f){
        if (f.isDirectory()){
            for (File file : f.listFiles()){
                deleteFile(file);
            }
        }else{
            f.delete();
        }
    }

    public static void copyFolder(File src, File dest)
            throws IOException {

        if(src.isDirectory()){

            //if directory not exists, create it
            if(!dest.exists()){
                dest.mkdir();
                System.out.println("Directory copied from "
                        + src + "  to " + dest);
            }

            //list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolder(srcFile,destFile);
            }

        }else{
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
            System.out.println("File copied from " + src + " to " + dest);
        }
    }

}
