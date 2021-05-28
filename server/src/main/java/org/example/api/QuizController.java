package org.example.api;

import com.english.quiz.dto.Message;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.handler.GameHandler;
import org.example.handler.SessionHandler;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.UUID;

@ServerEndpoint(value = "/quiz/{userId}", encoders = MessageEncoder.class)
public class QuizController {

    private static final Logger LOGGER = Logger.getLogger(QuizController.class);
    private final SessionHandler sessionHandler;
    private final GameHandler gameHandler;
    private final MessageDecoder messageDecoder;

    public QuizController() {
        this.sessionHandler = SessionHandler.getInstance();
        this.gameHandler = GameHandler.getInstance();
        this.messageDecoder = new MessageDecoder();
    }

    @OnOpen
    public void onOpen(final Session session, @PathParam("userId") final String userId) {
        LOGGER.log(Level.INFO, "New session established. User " + userId + " connected");
        sessionHandler.registerConnection(UUID.fromString(userId), session);
        gameHandler.createNewGame(session);
    }

    @OnMessage
    public void onMessage(final Session session, final String message) {
        LOGGER.log(Level.INFO, "Received new message " + message + " for session: " + session);
        final Message decodedMessage = messageDecoder.decode(message);
        gameHandler.processMessage(session, decodedMessage);
    }

    @OnClose
    public void onClose(final Session session) {
        LOGGER.log(Level.INFO, "Session " + session.getId() + "closed. User " + sessionHandler.getUserId(session) + " disconnected");
        sessionHandler.closeConnection(session);
    }

    @OnError
    public void onError(final Session session, final Throwable throwable) {
        //TODO
    }
}
