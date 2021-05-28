package org.example.factory;


import com.english.quiz.dto.Message;
import com.english.quiz.dto.MessageType;
import com.english.quiz.dto.QuestionMessage;
import com.english.quiz.dto.WelcomeMessage;

public class MessageFactory {

    public static Message getMessage(final MessageType messageType) {
        return buildMessage(messageType, null);
    }

    public static Message getMessage(final MessageDescriptor messageDescriptor) {
        return buildMessage(null, messageDescriptor);
    }

    private static Message buildMessage(final MessageType messageType, final MessageDescriptor messageDescriptor) {
        switch (messageType != null ? messageType : messageDescriptor.getMessageType()) {
            case WELCOME_MESSAGE:
                return new WelcomeMessage();
            case QUESTION:
                return new QuestionMessage(messageDescriptor.getQuestionEntity().getQuestion());
        }
        return null;
    }
}
