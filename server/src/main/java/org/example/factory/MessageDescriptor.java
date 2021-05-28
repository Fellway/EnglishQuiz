package org.example.factory;

import com.english.quiz.dto.MessageType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.model.db.QuestionEntity;

@Data
@Builder
@Getter
@Setter
public class MessageDescriptor {
    private MessageType messageType;
    private QuestionEntity questionEntity;
}
