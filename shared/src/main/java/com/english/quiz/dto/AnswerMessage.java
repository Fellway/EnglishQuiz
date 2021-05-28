package com.english.quiz.dto;

public class AnswerMessage implements Message {

    private final MessageType messageType;
    private final String answer;

    public AnswerMessage(final String answer) {
        this.messageType = MessageType.ANSWER;
        this.answer = answer;
    }

    @Override
    public String getContent() {
        return answer;
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }
}
