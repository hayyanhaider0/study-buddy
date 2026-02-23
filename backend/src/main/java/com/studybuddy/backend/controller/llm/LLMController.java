package com.studybuddy.backend.controller.llm;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studybuddy.backend.dto.ApiResponse;
import com.studybuddy.backend.dto.llm.GenerateRequest;
import com.studybuddy.backend.dto.llm.GenerateResponse;
import com.studybuddy.backend.service.llm.LLMService;

@RestController
@RequestMapping("/api/ai")
public class LLMController {
    private LLMService llmService;

    public LLMController(LLMService llmService) {
        this.llmService = llmService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<GenerateResponse>> saveGeneratedContent(@RequestBody GenerateRequest req) {
        GenerateResponse resData = llmService.saveGeneratedContent(req);
        return ResponseEntity.ok(new ApiResponse<GenerateResponse>(true, resData, null,
                "Content " + req.getTaskType() + " saved successfully."));
    }
}
