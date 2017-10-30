package webcheckers.model;

/**
 * Created by Juna on 10/15/2017.
 */
public class Message {
    private String text;
    public enum type {info, error}
    private type type;

    public Message(String messageText, type type){
        text = messageText;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public type getType() {
        return type;
    }

}
