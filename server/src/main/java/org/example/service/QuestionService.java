package org.example.service;

import org.example.model.db.QuestionEntity;
import org.example.repository.DbMock;

import java.util.List;

public class QuestionService {
    private static volatile QuestionService INSTANCE;
    private final DbMock dbMock;

    public static QuestionService getInstance() {
        if (INSTANCE == null) {
            synchronized (QuestionService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new QuestionService();
                }
            }
        }
        return INSTANCE;
    }

    private QuestionService() {
        dbMock = DbMock.getInstance();
    }

    public List<QuestionEntity> drawQuestions(final Integer numberOfQuestions) {
        return dbMock.getQuestions(numberOfQuestions);
    }
}
