package com.english.quiz.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WelcomeMessage implements Message {

    private final MessageType messageType;
    private final String content;

    public WelcomeMessage() {
        this.messageType = MessageType.WELCOME_MESSAGE;
        this.content = "Welcome to the EnglishQuiz!";
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }
}
