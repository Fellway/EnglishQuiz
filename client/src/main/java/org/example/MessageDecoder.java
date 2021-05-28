package org.example;

import com.google.gson.Gson;
import com.english.quiz.dto.Message;
import com.english.quiz.dto.QuestionMessage;
import com.english.quiz.dto.WelcomeMessage;

public class MessageDecoder {
    private final Gson gson = new Gson();

    public Message decode(final String s) {
        if (s.contains("Welcome")) {
            return gson.fromJson(s, WelcomeMessage.class);
        }
        return gson.fromJson(s, QuestionMessage.class);
    }
}
