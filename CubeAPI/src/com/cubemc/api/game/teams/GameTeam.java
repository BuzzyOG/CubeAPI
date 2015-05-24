package com.cubemc.api.game.teams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 06/04/2015
 */
public class GameTeam {

    private String name;
    private String color;
    private boolean visible = true;
    // -1 = Unlimited members
    private int maxSize = -1;

    private List<String> members = new ArrayList<String>();

    public GameTeam(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<String> getMembers() {
        return members;
    }
}
