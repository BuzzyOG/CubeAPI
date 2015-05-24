package com.cubemc.api.Commands.Random;

import com.cubemc.api.Ranks.Rank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class CMDh {

    public static List<HelpData> info = new ArrayList<HelpData>();
    public static List<HelpPage> pages = new ArrayList<HelpPage>();

    public static void register(String name, String args, Rank rank, String desc){
        info.add(new HelpData(name, args, rank, desc));
        if (pages.isEmpty()){
            List<HelpData> page = new ArrayList<HelpData>();
            page.add(new HelpData(name, args, rank, desc));
            pages.add(new HelpPage(page));
            return;
        }else{
            if (pages.get(pages.size() - 1).getCommands().size() >= 5){
                List<HelpData> page = new ArrayList<HelpData>();
                page.add(new HelpData(name, args, rank, desc));
                pages.add(new HelpPage(page));
                return;
            }else{
                pages.get(pages.size() - 1).getCommands().add(new HelpData(name, args, rank, desc));
                return;
            }
        }
    }

}
