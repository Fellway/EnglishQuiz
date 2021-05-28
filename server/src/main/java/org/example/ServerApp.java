package org.example;

import com.english.quiz.dto.WelcomeMessage;
import org.example.api.QuizController;

import javax.websocket.DeploymentException;
import java.util.concurrent.CountDownLatch;

public class ServerApp {
    public static void main( String[] args ) throws DeploymentException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost", 8027, "/", QuizController.class);
        DbMock dbMock = DbMock.getInstance();
        WelcomeMessage welcomeMessage = new WelcomeMessage();
        System.out.println(welcomeMessage);
        server.start();
        latch.await();
    }
}
