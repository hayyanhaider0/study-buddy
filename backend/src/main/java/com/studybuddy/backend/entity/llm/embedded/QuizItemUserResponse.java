package com.studybuddy.backend.entity.llm.embedded;

import java.time.Instant;

import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizItemUserResponse {
    private String itemId;
    private Integer selectedIndex;
    private Boolean correct;
    @LastModifiedDate
    private Instant updatedAt;
}
