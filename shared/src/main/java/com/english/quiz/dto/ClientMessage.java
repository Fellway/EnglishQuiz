package com.english.quiz.dto;

public class ClientMessage implements Message {

    private MessageType messageType;
    private String answer;

    @Override
    public String getContent() {
        return answer;
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }
}
