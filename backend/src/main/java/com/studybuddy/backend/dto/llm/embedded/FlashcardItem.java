package com.studybuddy.backend.dto.llm.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FlashcardItem extends Item {
    private String question;
    private String answer;
    private String explanation;
}
