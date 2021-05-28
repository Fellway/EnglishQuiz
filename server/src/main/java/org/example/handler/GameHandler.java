package org.example.handler;

import com.english.quiz.dto.AnswerMessage;
import com.english.quiz.dto.Message;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.Game;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameHandler {
    private static final Logger LOGGER = Logger.getLogger(GameHandler.class);
    private static volatile GameHandler INSTANCE;
    private final Map<Session, Game> gameMap;

    private GameHandler() {
        this.gameMap = new ConcurrentHashMap<>();
    }

    public static GameHandler getInstance() {
        if (INSTANCE == null) {
            synchronized (GameHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GameHandler();
                }
            }
        }
        return INSTANCE;
    }

    public void createNewGame(final Session session) {
        LOGGER.log(Level.INFO, "Created new game for session: " + session.getId());
        final Game game = new Game(session);
        gameMap.put(session, game);
        game.start();
    }

    public void processMessage(final Session session, final Message message) {
        LOGGER.log(Level.DEBUG, "Processing message " + message.getContent());
        if (message instanceof AnswerMessage) {
            Game game = gameMap.get(session);
            game.processClientAnswer((AnswerMessage) message);
        }
    }
}
