package org.example;

import com.english.quiz.dto.AnswerMessage;
import com.english.quiz.dto.Message;
import com.english.quiz.dto.QuestionMessage;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    private final Session session;

    public Client(final Session session) {
        this.session = session;
    }

    public void proceedMessage(final Message message) throws IOException {
        System.out.println(message.getContent());
        if(message instanceof QuestionMessage) {
            final BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter answer: ");
            String answer = bufferRead.readLine();
            sendMessage(answer);
        }
    }

    private void sendMessage(final String message) {
        try {
            session.getBasicRemote().sendObject(new AnswerMessage(message));
        } catch (IOException | EncodeException e) {
            System.out.println("Cannot send the message");
        }
    }
}
