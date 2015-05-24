package com.cubemc.api.Commands;

import com.cubemc.api.Core.Module;
import com.cubemc.api.Ranks.Rank;

import java.util.Collection;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public abstract class CommandBase<PluginType extends Module> implements CubeCommand {

    private Rank requiredRank;
    private List<String> aliases;
    protected PluginType plugin;
    protected String aliasUsed;
    protected CommandCenter commandCenter;

    protected CommandBase(PluginType plugin, Rank requiredRank, List<String> aliases) {
        this.plugin = plugin;
        this.requiredRank = requiredRank;
        this.aliases = aliases;
    }

    public Collection<String> aliases(){
        return this.aliases;
    }

    public void setAliasUsed(String aliasUsed) {
        this.aliasUsed = aliasUsed;
    }

    public Rank getRequiredRank() {
        return requiredRank;
    }

    public void setCommandCenter(CommandCenter commandCenter) {
        this.commandCenter = commandCenter;
    }
}