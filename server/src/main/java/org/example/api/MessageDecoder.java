package org.example.api;

import com.english.quiz.dto.AnswerMessage;
import com.english.quiz.dto.Message;
import com.english.quiz.dto.MessageType;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

public class MessageDecoder {

    private static final Logger LOGGER = Logger.getLogger(MessageDecoder.class);
    private final Gson gson = new Gson();

    public Message decode(final String s) {
        LOGGER.info("Decoding message " + s);
        if (s.contains(MessageType.ANSWER.toString())) {
            return gson.fromJson(s, AnswerMessage.class);
        }
        return null;
    }

}
