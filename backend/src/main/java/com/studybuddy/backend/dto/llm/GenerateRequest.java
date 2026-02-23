package com.studybuddy.backend.dto.llm;

import java.util.List;

import com.studybuddy.backend.dto.llm.embedded.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateRequest {
    private String taskType;
    private String name;
    private List<Item> items;
}
