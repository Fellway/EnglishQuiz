package org.example;


import org.example.api.ClientEndpoint;
import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.DeploymentException;
import javax.websocket.Session;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);

        final ClientManager client = ClientManager.createClient();

        try {
            final Session session = client.connectToServer(ClientEndpoint.class, new URI("ws://localhost:8027/quiz/" + UUID.randomUUID()));
            latch.await();
            session.close();
        } catch (final DeploymentException | URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
