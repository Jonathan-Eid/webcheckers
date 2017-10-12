package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerLobby {

	private static Map<String, Player> playerMap;
	public enum SignInResult {SIGNEDIN, INVALIDINPUT, INVALIDPLAYER, SIGNEDOUT}

    public PlayerLobby(){
        playerMap = new HashMap<>();
    }

    public SignInResult signInPlayer(String name){
        if (invalidInput(name)){
            return SignInResult.INVALIDINPUT;
        }
        else if (isLoggedIn(name)){
            return SignInResult.INVALIDPLAYER;
        }
        else{
            Player playerLogin = new Player(name);
            playerMap.put(name, playerLogin);
            return SignInResult.SIGNEDIN;
        }
    }

    public SignInResult signOutPlayer(String name){
        if (invalidInput(name)){
            return SignInResult.INVALIDINPUT;
        }
        else if (!isLoggedIn(name)){
            return SignInResult.INVALIDPLAYER;
        }
        else{
            playerMap.remove(name);
            return SignInResult.SIGNEDOUT;
        }
    }

    public boolean invalidInput(String name){
        return name.contains("\"");
    }

    public Player getPlayer(String name) {
        if (!invalidInput(name)) {
            if (isLoggedIn(name)) {

                return playerMap.get(name);
            }
        }
    }

    public int getNumPlayers(){
        return playerMap.size();
    }

    private boolean isLoggedIn(String name){
        return playerMap.containsKey(name);
    }
}
