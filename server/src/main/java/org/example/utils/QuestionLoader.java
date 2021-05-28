package org.example.utils;

import com.google.gson.Gson;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.model.db.QuestionEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class QuestionLoader {
    private static final Logger LOGGER = Logger.getLogger(QuestionLoader.class);
    private static final String QUESTIONS_JSON = "questions.json";
    private static final Gson gson = new Gson();

    public static List<QuestionEntity> loadQuestions() {
        try {
            final QuestionEntity[] a = gson.fromJson(Files.readString(getFile().toPath()), QuestionEntity[].class);
            return Arrays.asList(a);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Cannot read file: " + QUESTIONS_JSON);
        }
        return null;
    }

    private static File getFile() {
        ClassLoader classLoader = QuestionLoader.class.getClassLoader();
        return new File(classLoader.getResource(QUESTIONS_JSON).getFile());
    }
}
