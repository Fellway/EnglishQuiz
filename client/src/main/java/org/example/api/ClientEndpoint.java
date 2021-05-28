package org.example.api;

import com.english.quiz.dto.Message;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.Client;

import javax.websocket.*;
import java.io.IOException;

@javax.websocket.ClientEndpoint(encoders = MessageEncoder.class)
public class ClientEndpoint {

    private static final Logger LOGGER = Logger.getLogger(ClientEndpoint.class);
    private final MessageDecoder messageDecoder;
    private Client sender;

    public ClientEndpoint() {
        this.messageDecoder = new MessageDecoder();
    }

    @OnOpen
    public void onOpen(final Session userSession) {
        LOGGER.log(Level.INFO, "Successfully connected to server");
        this.sender = new Client(userSession);
    }

    @OnClose
    public void onClose(final Session userSession, final CloseReason reason) {
        LOGGER.log(Level.INFO, "Closing connection");
    }

    @OnMessage
    public void onMessage(final String message, final Session session) throws IOException {
        LOGGER.log(Level.INFO, "Received message: " + message);
        final Message decodedMessage = messageDecoder.decode(message);
        sender.proceedMessage(decodedMessage);
    }
}
