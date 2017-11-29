package com.webcheckers.model;

import java.util.Objects;

/**
 * creates a template for the player object
 * created by Johnny, Disney, Andy, Ani
 */

public class Player implements Comparable<Player>{
    String name;
    String password;
    boolean signedIn;

    /**
     * standard constructor
     * @param name the player's name, which can be any String
     */
    public Player(String name) {
        this.signedIn = true;
        this.name = name;
    }

    /**
     * constructor that set's the password aswell./
     * @param name tne player's name
     * @param password the player's password
     */
    public Player(String name, String password) {
        this.signedIn = true;
        this.name = name;
        this.password = password;
    }

    /**
     * Validate a given password. Should be called whenever a user is trying to sign in.
     * @param password
     * @return
     */
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public boolean isSignedIn(){
        return this.signedIn;
    }

    /**
     * Store that the player haa signed out.
     */
    public void signOut(){
        this.signedIn = false;
    }

    /**
     * Store that the player has signedIn
     */
    public void signIn(){
        this.signedIn = true;
    }

    public String getName() {
        return name;
    }

    @Override
    /**
     * equality is determined by the name of the player
     */
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            Player other = (Player) obj;
            return name.equals(other.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Player player) {
        return this.name.compareTo(player.name);
    }

    public boolean nullName(){
        return this.name.equals("\"");
    }
}
