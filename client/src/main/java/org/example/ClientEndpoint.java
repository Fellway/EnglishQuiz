package org.example;

import com.english.quiz.dto.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.*;
import java.io.IOException;

@javax.websocket.ClientEndpoint(encoders = MessageEncoder.class)
public class ClientEndpoint {

    private final static Logger LOGGER = LogManager.getLogger(ClientEndpoint.class);
    private final MessageDecoder messageDecoder;
    private Client sender;

    public ClientEndpoint() {
        this.messageDecoder = new MessageDecoder();
    }

    @OnOpen
    public void onOpen(final Session userSession) {
        System.out.println("Successfully connected to server");
        this.sender = new Client(userSession);
    }

    @OnClose
    public void onClose(final Session userSession, final CloseReason reason) {
        System.out.println("closing websocket");
    }

    @OnMessage
    public void onMessage(final String message, final Session session) throws IOException {
        final Message decodedMessage = messageDecoder.decode(message);
        sender.proceedMessage(decodedMessage);
    }
}
