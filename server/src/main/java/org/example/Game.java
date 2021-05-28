package org.example;


import com.english.quiz.dto.AnswerMessage;
import com.english.quiz.dto.MessageType;
import org.example.factory.MessageDescriptor;
import org.example.model.db.QuestionEntity;
import org.example.service.QuestionService;
import org.example.utils.ClientMessageSender;

import javax.websocket.Session;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.example.factory.MessageFactory.getMessage;


public class Game {

    private final QuestionService questionService;
    private final Session session;

    private List<QuestionEntity> questions;
    private Double points;
    private Temporal initialTime;
    private QuestionEntity askedQuestion;

    public Game(final Session session) {
        this.session = session;
        this.questionService = QuestionService.getInstance();
    }

    public void start() {
        setUpQuestions();
        points = 0.0;
        initialTime = Instant.now();
        ClientMessageSender.onServerMessage(session, getMessage(MessageType.WELCOME_MESSAGE));
        sendQuestionToClient(questions.get(0));
    }

    public void processClientAnswer(final AnswerMessage message) {
        validateAnswer(message);
        if(questions.isEmpty()) {
            endGame();
        }
        sendQuestionToClient(questions.get(0));
    }

    private void endGame() {
        Long finishTime = Duration.between(initialTime, Instant.now()).toMillis();
        final MessageDescriptor messageDescriptor = MessageDescriptor.builder()
                .messageType(MessageType.END_GAME)
                .points(points)
                .time(finishTime)
                .build();
        ClientMessageSender.onServerMessage(session, getMessage(messageDescriptor));
    }

    private void validateAnswer(final AnswerMessage message) {
        final Optional<Double> min = askedQuestion.getAnswers().stream().map(answer -> Levenshtein.calculate(answer, message.getContent())).min(Comparator.naturalOrder());
        if (min.isPresent()) {
            if (min.get() == 1.0) {
                points += 0.5;
            } else if (min.get() == 0.0) {
                points += 1;
            }
        }
    }

    private void sendQuestionToClient(final QuestionEntity questionEntity) {
        this.askedQuestion = questionEntity;
        final MessageDescriptor messageDescriptor = MessageDescriptor.builder()
                .messageType(MessageType.QUESTION)
                .questionEntity(questionEntity)
                .build();
        ClientMessageSender.onServerMessage(session, getMessage(messageDescriptor));
        questions.remove(questionEntity);
    }

    private void setUpQuestions() {
        this.questions = new ArrayList<>(questionService.drawQuestions(10));
    }

}
