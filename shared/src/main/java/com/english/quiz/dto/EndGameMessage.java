package com.english.quiz.dto;

public class EndGameMessage implements Message {

    private final MessageType messageType;
    private final Double points;
    private final Long time;

    public EndGameMessage(final Double points, final Long time) {
        this.points = points;
        this.time = time;
        this.messageType = MessageType.END_GAME;
    }

    @Override
    public String getContent() {
        return "You earned " + points + " points " + "in time: " + time + " s";
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }
}
