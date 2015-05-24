package com.cubemc.api.Core;

import com.cubemc.api.Utils.TimeUtil;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 23/05/2015
 */
public abstract class Module implements Listener {

    protected String moduleName = "MiniPluginModule";
    protected JavaPlugin plugin;

    public Module(String moduleName, JavaPlugin plugin) {
        this.moduleName = moduleName;
        this.plugin = plugin;

        onEnable();

        RegisterEvents(this);
        AddCommands();
    }

    public final void onEnable(){
        long epoch = System.currentTimeMillis();
        Log("Initializing...");
        Enable();
        Log("Enabled in " + TimeUtil.convertString(System.currentTimeMillis() - epoch, 6, TimeUtil.TimeUnit.FIT));
    }

    public JavaPlugin getPlugin(){
        return plugin;
    }

    public BukkitScheduler getScheduler(){
        return getPlugin().getServer().getScheduler();
    }

    public PluginManager getPluginManager(){
        return getPlugin().getServer().getPluginManager();
    }

    public final void onDisable(){
        Disable();

        Log("Disabled.");
    }

    public void RegisterEvents(Listener listener){
        getPluginManager().registerEvents(listener, getPlugin());
    }

    public void Enable(){

    }

    public void Disable(){

    }

    public void AddCommands(){

    }

    public void Log(String message){
        System.out.println("[" + moduleName + "]: " + message);
    }
}