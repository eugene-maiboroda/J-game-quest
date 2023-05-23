package com.web.application.gamequest.model;

public class Question extends QuestEntity {
    private Type type;

    public Question(Integer id, String content, Type type) {
        super(id, content);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        USUAL,
        WON,
        LOST
    }
}
