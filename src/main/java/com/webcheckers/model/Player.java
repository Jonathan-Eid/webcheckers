package com.webcheckers.model;

import java.util.Objects;

/**
 * creates a template for the player object
 * created by Johnny, Disney, Andy, Ani
 */

public class Player implements Comparable<Player>{
    private String name;

    /**
     * standard constructor
     * @param name the player's name, which can be any String
     */
    public Player(String name) {
        this.name = name;
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
