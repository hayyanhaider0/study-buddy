package com.studybuddy.backend.dto.llm;

import com.studybuddy.backend.entity.llm.embedded.QuizItemUserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizItemUserResponseRequest {
    private String quizId;
    private QuizItemUserResponse response;
}
