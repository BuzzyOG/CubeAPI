package com.cubemc.api.game.maps;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 06/04/2015
 */
public class GameMap {

    private String name;
    private String author;
    private String folderName;
    private int votes = 0;

    public GameMap(String name, String author, String folderName) {
        this.name = name;
        this.author = author;
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getVotes() {
        return votes;
    }

    public void addVoteNum(int i){
        votes = votes + i;
    }

    public void removeVoteNum(int i){
        votes = votes - i;
        if (votes < 0) votes = 0;
    }
}
