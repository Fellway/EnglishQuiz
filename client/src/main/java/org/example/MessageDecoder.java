package org.example;

import com.english.quiz.dto.Message;
import com.english.quiz.dto.QuestionMessage;
import com.english.quiz.dto.WelcomeMessage;
import com.google.gson.Gson;

public class MessageDecoder {
    private final Gson gson = new Gson();

    public Message decode(final String s) {
        if (s.contains("Welcome")) {
            return gson.fromJson(s, WelcomeMessage.class);
        }
        return gson.fromJson(s, QuestionMessage.class);
    }
}
