package org.example.api;

import com.english.quiz.dto.Message;
import com.google.gson.Gson;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;


public class MessageEncoder implements Encoder.Text<Message> {

    private static final Logger LOGGER = Logger.getLogger(MessageEncoder.class);
    private static final Gson gson = new Gson();

    @Override
    public void init(final EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public String encode(final Message message) {
        LOGGER.log(Level.DEBUG, "Encoding message " + message.toString());
        return gson.toJson(message);
    }
}
