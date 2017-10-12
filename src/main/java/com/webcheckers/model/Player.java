package com.webcheckers.model;

public class Player {
	private String name;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Player){
			Player other = (Player) obj;
			return name.equals(other.getName());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
