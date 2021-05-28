package org.example.utils;


import com.english.quiz.dto.Message;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;

public class ClientMessageSender {

    public static void onServerMessage(final Session session, final Message message) {
        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            System.out.println("Cannot send message to server");
        }
    }
}
