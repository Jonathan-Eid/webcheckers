package main.java.com.webcheckers.model;

/**
 * Created by Juna on 10/15/2017.
 */
public class Square {
    private int x;
    private int y;
    public enum spotType{RED, WHITE,RED_KING,WHITE_KING}
    private spotType spot;

    public Square(int x_location, int y_location,spotType spot){
        this.x=x_location;
        this.y=y_location;
        this.spot = spot;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public spotType getSpot() {
        return spot;
    }
}
