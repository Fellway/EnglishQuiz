package org.example.utils;


import com.english.quiz.dto.Message;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;

public class ClientMessageSender {

    private static final Logger LOGGER = Logger.getLogger(ClientMessageSender.class);

    public static void onServerMessage(final Session session, final Message message) {
        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            LOGGER.log(Level.ERROR, "Cannot send message to server");
        }
    }
}
