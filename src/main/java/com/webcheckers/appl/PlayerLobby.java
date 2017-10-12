package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerLobby {

    private static Map<String, Player> playerMap;

    public enum SignInResult {SIGNED_IN, INVALID_INPUT, INVALID_PLAYER, SIGNED_OUT}

    public PlayerLobby() {
        playerMap = new HashMap<>();
    }

    public SignInResult signInPlayer(String name) {
        if (invalidInput(name)) {
            return SignInResult.INVALID_INPUT;
        } else if (isLoggedIn(name)) {
            return SignInResult.INVALID_PLAYER;
        } else {
            Player playerLogin = new Player(name);
            playerMap.put(name, playerLogin);
            return SignInResult.SIGNED_IN;
        }
    }

    public SignInResult signOutPlayer(String name) {
        if (invalidInput(name)) {
            return SignInResult.INVALID_INPUT;
        } else if (!isLoggedIn(name)) {
            return SignInResult.INVALID_PLAYER;
        } else {
            playerMap.remove(name);
            return SignInResult.SIGNED_OUT;
        }
    }

    public boolean invalidInput(String name) {
        return name.contains("\"") || name.equals("");
    }

    public Player getPlayer(String name) {
        if (!invalidInput(name)) {
            if (isLoggedIn(name)) {
                return playerMap.get(name);
            }
        }
        return null;
    }

    public int getNumPlayers() {
        return playerMap.size();
    }

    private boolean isLoggedIn(String name) {
        return playerMap.containsKey(name);
    }
}
