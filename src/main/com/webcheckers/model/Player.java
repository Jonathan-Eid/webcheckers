package webcheckers.model;

/**
 * creates a template for the player object
 * created by Johnny, Disney, Andy, Ani
 */

public class Player implements Comparable<Player>{
    private String name;
    private Piece.color color;

    public Player(String name) {
        this.name = name;
    }

    /**
     * Getter for the Name
     * @return
     */
    public String getName() {
        return name;
    }


    @Override
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

    public Piece.color getColor() {
        return color;
    }

    public void setColor(Piece.color color) {
        this.color = color;
    }
}
