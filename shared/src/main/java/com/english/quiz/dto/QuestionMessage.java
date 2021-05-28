package com.english.quiz.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuestionMessage implements Message {

    private final MessageType messageType;
    private final String question;

    public QuestionMessage(final String question) {
        this.question = question;
        this.messageType = MessageType.QUESTION;
    }

    @Override
    public String getContent() {
        return question;
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }
}
