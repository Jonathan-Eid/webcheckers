package com.webcheckers.model;

/**
 * Created by Juna on 10/15/2017.
 */
public class Message {
    private String text;
    public enum type {info, error}
    private type type;

    /**
     * standard constructor
     * @param messageText the text of the message, for the user to see
     * @param type whether the type of this message is INFO or ERROR
     */
    public Message(String messageText, type type){
        text = messageText;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    /**
     * returns what type of message this is, which is an enum
     * @return INFO or ERROR
     */
    public type getType() {
        return type;
    }

}
