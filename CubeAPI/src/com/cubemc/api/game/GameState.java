package com.cubemc.api.game;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 06/04/2015
 */
public enum GameState {

    WAITING(true, false), STARTING(true, true), WARMUP(false, false), INGAME(true, false), ENDED(false, false);

    private boolean joinable;
    private boolean priorityLogin;

    GameState(boolean joinable, boolean priorityLogin) {
        this.joinable = joinable;
        this.priorityLogin = priorityLogin;
    }

    public boolean isJoinable() {
        return joinable;
    }

    public boolean isPriorityLogin() {
        return priorityLogin;
    }
}
