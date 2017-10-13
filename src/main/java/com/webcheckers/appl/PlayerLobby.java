package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PlayerLobby {
    private static final Logger LOG = Logger.getLogger(PlayerLobby.class.getName());

    private static List<Player> playerList;
    public enum SignInResult {SIGNED_IN, INVALID_INPUT, INVALID_PLAYER, SIGNED_OUT}

    public PlayerLobby() {
        playerList = new ArrayList<>();
    }

    public SignInResult signInPlayer(String name) {
        if (invalidInput(name)) {
            return SignInResult.INVALID_INPUT;
        } else if (isLoggedIn(name)) {
            return SignInResult.INVALID_PLAYER;
        } else {
            Player playerLogin = new Player(name);
            playerList.add(playerLogin);
            return SignInResult.SIGNED_IN;
        }
    }

    public SignInResult signOutPlayer(String name) {
        if (invalidInput(name)) {
            return SignInResult.INVALID_INPUT;
        } else if (!isLoggedIn(name)) {
            return SignInResult.INVALID_PLAYER;
        } else {
            playerList.remove(name);
            return SignInResult.SIGNED_OUT;
        }
    }

    public boolean invalidInput(String name) {
        return name.contains("\"") || name.equals("");
    }

    public Player getPlayer(String name) {
        if (!invalidInput(name)) {
            if (isLoggedIn(name)) {
                for (Player player : playerList){
                    if (player.getName().equals(name)){
                        return player;
                    }
                }
            }
        }
        return null;
    }

    public String getNumPlayers() {
        return Integer.toString(playerList.size());
    }

    private boolean isLoggedIn(String name) {
        return playerList.contains(new Player(name));
    }

    public String playerList(String name){
        String result = "";
        for (Player player : playerList){
            if (!player.getName().equals(name)) {
                result = result.concat(player.getName() + " \n");
            }
        }
        LOG.config(result);
        return result;
    }
}
