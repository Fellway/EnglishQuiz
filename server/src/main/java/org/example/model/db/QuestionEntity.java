package org.example.model.db;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class QuestionEntity {
    private final String question;
    private final List<String> answers;
}


