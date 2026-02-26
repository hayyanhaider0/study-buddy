package com.studybuddy.backend.dto.llm.embedded;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QuizItem extends Item {
    private String question;
    private List<String> options;
    private String answer;
    private String explanation;
}
