package com.studybuddy.backend.entity.llm;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.studybuddy.backend.entity.llm.embedded.QuizItem;

import lombok.Data;

@Document(collection = "quizzes")
@Data
public class Quiz {
    @Id
    private String id;
    private String userId;
    private String notebookId;
    private String name;
    private List<QuizItem> items;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant lastViewedAt;
}
