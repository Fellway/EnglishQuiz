package org.example;

import com.english.quiz.dto.ClientMessage;
import com.english.quiz.dto.Message;
import com.google.gson.Gson;

public class MessageDecoder {

    private final Gson gson = new Gson();

    public Message decode(final String s) {
        if (s.contains("answer")) {
            return gson.fromJson(s, ClientMessage.class);
        }
        return null;
    }

}
