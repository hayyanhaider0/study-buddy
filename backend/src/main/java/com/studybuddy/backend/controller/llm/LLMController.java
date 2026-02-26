package com.studybuddy.backend.controller.llm;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studybuddy.backend.dto.ApiResponse;
import com.studybuddy.backend.dto.llm.GenerateRequest;
import com.studybuddy.backend.dto.llm.GenerateResponse;
import com.studybuddy.backend.dto.llm.QuizItemUserResponseRequest;
import com.studybuddy.backend.entity.llm.Flashcards;
import com.studybuddy.backend.entity.llm.Quiz;
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

    @GetMapping("/flashcards")
    public ResponseEntity<ApiResponse<List<Flashcards>>> getFlashcards() {
        List<Flashcards> resData = llmService.getFlashcards();
        return ResponseEntity
                .ok(new ApiResponse<List<Flashcards>>(true, resData, null, "Flashcards fetched successfully."));
    }

    @GetMapping("/quizzes")
    public ResponseEntity<ApiResponse<List<Quiz>>> getQuizzes() {
        List<Quiz> resData = llmService.getQuizzes();
        return ResponseEntity
                .ok(new ApiResponse<List<Quiz>>(true, resData, null, "Quizzes fetched successfully."));
    }

    @PatchMapping("/quizzes/response")
    public ResponseEntity<ApiResponse<Void>> updateUserResponse(@RequestBody QuizItemUserResponseRequest response) {
        llmService.updateUserResponse(response);
        return ResponseEntity.ok(new ApiResponse<Void>(true, null, null, "Quiz response updated successfully."));
    }
}
