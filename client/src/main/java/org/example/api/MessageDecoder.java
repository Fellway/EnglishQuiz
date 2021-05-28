package org.example.api;

import com.english.quiz.dto.*;
import com.google.gson.Gson;

public class MessageDecoder {
    private final Gson gson = new Gson();

    public Message decode(final String s) {
        if (s.contains("Welcome")) {
            return gson.fromJson(s, WelcomeMessage.class);
        } else if (s.contains(MessageType.END_GAME.toString())) {
            return gson.fromJson(s, EndGameMessage.class);
        }
        return gson.fromJson(s, QuestionMessage.class);
    }
}
