package com.studybuddy.backend.dto.llm.embedded;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FlashcardItem.class, name = "flashcard"),
        @JsonSubTypes.Type(value = QuizItem.class, name = "quiz"),
// @JsonSubTypes.Type(value = NotesItem.class, name = "notes"),
// @JsonSubTypes.Type(value = ExamItem.class, name = "exam")
})
public abstract class Item {
    private String id;
}
