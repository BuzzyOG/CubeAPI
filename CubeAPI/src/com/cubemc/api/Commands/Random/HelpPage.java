package com.cubemc.api.Commands.Random;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class HelpPage {

    private List<HelpData> commands = new ArrayList<HelpData>();

    public HelpPage(List<HelpData> commands) {
        this.commands = commands;
    }

    public List<HelpData> getCommands() {
        return commands;
    }
    
}
