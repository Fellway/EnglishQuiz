package org.example.api;

import com.english.quiz.dto.*;
import com.google.gson.Gson;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MessageDecoder {
    private static final Logger LOGGER = Logger.getLogger(MessageDecoder.class);
    private final Gson gson = new Gson();

    public Message decode(final String s) {
        LOGGER.log(Level.DEBUG, "Decoding message: " + s);
        if (s.contains(MessageType.WELCOME_MESSAGE.toString())) {
            return gson.fromJson(s, WelcomeMessage.class);
        } else if (s.contains(MessageType.END_GAME.toString())) {
            return gson.fromJson(s, EndGameMessage.class);
        }
        return gson.fromJson(s, QuestionMessage.class);
    }
}
