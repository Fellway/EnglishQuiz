package org.example.repository;

import org.example.model.db.QuestionEntity;
import org.example.utils.QuestionLoader;

import java.util.List;

public class DbMock {
    private static volatile DbMock INSTANCE;
    private List<QuestionEntity> questions;

    public static DbMock getInstance() {
        if (INSTANCE == null) {
            synchronized (DbMock.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DbMock();
                }
            }
        }
        return INSTANCE;
    }

    private DbMock() {
        questions = QuestionLoader.loadQuestions();
    }

    public List<QuestionEntity> getAllQuestions() {
        return questions;
    }

    public List<QuestionEntity> getQuestions(final Integer numberOfQuestions) {
        return questions.subList(0, numberOfQuestions);
    }
}
