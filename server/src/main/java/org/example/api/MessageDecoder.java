package org.example.api;

import com.english.quiz.dto.AnswerMessage;
import com.english.quiz.dto.Message;
import com.english.quiz.dto.MessageType;
import com.google.gson.Gson;

public class MessageDecoder {

    private final Gson gson = new Gson();

    public Message decode(final String s) {
        if (s.contains(MessageType.ANSWER.toString())) {
            return gson.fromJson(s, AnswerMessage.class);
        }
        return null;
    }

}
