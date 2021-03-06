package org.example.handler;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionHandler {

    private static final Logger LOGGER = Logger.getLogger(SessionHandler.class);
    private static volatile SessionHandler INSTANCE;
    private final Map<Session, UUID> sessionMap;

    private SessionHandler() {
        this.sessionMap = new ConcurrentHashMap<>();
    }

    public static SessionHandler getInstance() {
        if (INSTANCE == null) {
            synchronized (SessionHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SessionHandler();
                }
            }
        }
        return INSTANCE;
    }

    public void registerConnection(final UUID userId, final Session session) {
        LOGGER.log(Level.INFO, "Registered new connection for userId: " + userId + " and session: " + session.getId());
        sessionMap.put(session, userId);
    }

    public void closeConnection(final Session session) {
        try {
            session.close();
            sessionMap.remove(session);
        } catch (final IOException e) {
            LOGGER.log(Level.ERROR, "Cannot close the connection");
        }
    }

    public UUID getUserId(final Session session) {
        return sessionMap.get(session);
    }

    public void closeAllConnections() {
        sessionMap.keySet().forEach(this::closeConnection);
    }
}
