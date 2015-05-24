package com.cubemc.api.Commands.Random;

import com.cubemc.api.Ranks.Rank;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class HelpData {

    private String cmd;
    private String args;
    private Rank rank;
    private String desc;

    public HelpData(String cmd, String args, Rank rank, String desc) {
        this.cmd = cmd;
        this.args = args;
        this.rank = rank;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getCmd() {
        return cmd;
    }

    public String getArgs() {
        return args;
    }

    public Rank getRank() {
        return rank;
    }
}
