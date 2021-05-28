package org.example;

import org.example.api.QuizController;

import javax.websocket.DeploymentException;
import java.util.concurrent.CountDownLatch;

public class ServerApp {
    public static void main(String[] args) throws DeploymentException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost", 8027, "/", QuizController.class);
        server.start();
        latch.await();
    }
}
