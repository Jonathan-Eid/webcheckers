package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerLobby {

	private static Map<String, Player> playerMap;
	public enum SignInResult {SIGNEDIN, INVALIDINPUT, INVALIDPLAYER};

    public PlayerLobby(){
        playerMap = new HashMap<>();
    }

    public boolean signInPlayer(String name){
        if (isLoggedIn(name)){
            return false;
        }
        else{
            Player playerLogin = new Player(name);
            playerMap.put(name, playerLogin);
            return true;
        }
    }

    public boolean signOutPlayer(String name){
        if (!isLoggedIn(name)){
            return false;
        }
        else{
            playerMap.remove(name);
            return true;
        }
    }

    public boolean invalidInput(String name){
        return name.contains('"')
    }

    public Player getPlayer(String name) {
        if (!invalidInput()) {
            if (isLoggedIn(name)) {

                return playerMap.get(name);
            }
        }
    }

    public int getNumPlayers(){
        playerMap.size();
    }

    private boolean isLoggedIn(String name){
        return playerMap.containsKey(name);
    }
}
