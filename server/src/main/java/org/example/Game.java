package org.example;


import com.english.quiz.dto.Message;
import com.english.quiz.dto.MessageType;
import org.example.factory.MessageDescriptor;
import org.example.factory.MessageFactory;
import org.example.model.db.QuestionEntity;
import org.example.service.QuestionService;

import javax.websocket.Session;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class Game {

    private final QuestionService questionService;
    private final Session session;

    private List<QuestionEntity> questions;
    private Integer points;
    private Timestamp initialTime;
    private QuestionEntity askedQuestion;

    public Game(final Session session) {
        this.session = session;
        this.questionService = QuestionService.getInstance();
        setUpQuestions();
    }

    public void start() {
        ClientMessageSender.onServerMessage(session, MessageFactory.getMessage(MessageType.WELCOME_MESSAGE));
        sendQuestionToClient(questions.get(0));
    }

    public void processClientAnswer(final Message message) {
        sendQuestionToClient(questions.get(0));
    }

    private void sendQuestionToClient(final QuestionEntity questionEntity) {
        this.askedQuestion = questionEntity;
        final MessageDescriptor messageDescriptor = MessageDescriptor.builder()
                .messageType(MessageType.QUESTION)
                .questionEntity(questionEntity)
                .build();
        ClientMessageSender.onServerMessage(session, MessageFactory.getMessage(messageDescriptor));
        questions.remove(questionEntity);
    }

    private void setUpQuestions() {
        this.questions = new ArrayList<>(questionService.drawQuestions(10));
    }

}
