package model;

import java.io.Serializable;

public class Message implements Serializable {
    private Object object;
    private MessageType type;

    public Message() {
    }

    public Message(Object object, MessageType type) {
        this.object = object;
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public enum MessageType{
        FIND, UPDATE
    }
}


