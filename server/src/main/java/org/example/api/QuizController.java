package org.example.api;

import com.english.quiz.dto.Message;
import org.example.GameHandler;
import org.example.MessageDecoder;
import org.example.MessageEncoder;
import org.example.SessionHandler;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.UUID;

@ServerEndpoint(value = "/quiz/{userId}", encoders = MessageEncoder.class)
public class QuizController {

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
        System.out.println("New session established. User " + userId + " connected");
        sessionHandler.registerConnection(UUID.fromString(userId), session);
        gameHandler.createNewGame(session);
    }

    @OnMessage
    public void onMessage(final Session session, final String message) {
        final Message decodedMessage = messageDecoder.decode(message);
        gameHandler.processMessage(session, decodedMessage);
    }

    @OnClose
    public void onClose(final Session session) {
        System.out.println("Session closed. User " + sessionHandler.getUserId(session) + " disconnected");
        sessionHandler.closeConnection(session);
    }

    @OnError
    public void onError(final Session session, final Throwable throwable) {

    }
}
