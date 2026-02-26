package com.studybuddy.backend.entity.llm;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.studybuddy.backend.entity.llm.embedded.FlashcardItem;

import lombok.Data;

@Document(collection = "flashcards")
@Data
public class Flashcards {
    @Id
    private String id;
    private String userId;
    private String notebookId;
    private String name;
    private List<FlashcardItem> items;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant lastViewedAt;
}
