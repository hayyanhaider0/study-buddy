package com.studybuddy.backend.service.llm;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studybuddy.backend.dto.llm.GenerateRequest;
import com.studybuddy.backend.dto.llm.GenerateResponse;
import com.studybuddy.backend.dto.llm.QuizItemUserResponseRequest;
import com.studybuddy.backend.entity.llm.Flashcards;
import com.studybuddy.backend.entity.llm.Quiz;
import com.studybuddy.backend.entity.llm.embedded.FlashcardItem;
import com.studybuddy.backend.entity.llm.embedded.QuizItem;
import com.studybuddy.backend.entity.llm.embedded.QuizItemUserResponse;
import com.studybuddy.backend.exception.InvalidRequestException;
import com.studybuddy.backend.repository.FlashcardsRepository;
import com.studybuddy.backend.repository.QuizRepository;
import com.studybuddy.backend.utility.auth.AuthUtil;

@Service
public class LLMService {
    private FlashcardsRepository flashcardsRepository;
    private QuizRepository quizRepository;
    private AuthUtil authUtil;

    public LLMService(FlashcardsRepository flashcardsRepository, QuizRepository quizRepository, AuthUtil authUtil) {
        this.flashcardsRepository = flashcardsRepository;
        this.quizRepository = quizRepository;
        this.authUtil = authUtil;
    }

    public GenerateResponse saveGeneratedContent(GenerateRequest req) {
        String userId = authUtil.getCurrentUserId();
        GenerateResponse resData = null;
        String taskType = req.getTaskType().toLowerCase().trim();

        switch (taskType) {
            case "flashcards":
                Flashcards flashcards = mapToFlashcards(req, userId);
                flashcardsRepository.save(flashcards);
                resData = new GenerateResponse(flashcards.getId());
                break;
            case "quiz":
                Quiz quiz = mapToQuiz(req, userId);
                quizRepository.save(quiz);
                resData = new GenerateResponse(quiz.getId());
                break;
            case "default":
                throw new InvalidRequestException("Task type: " + taskType + " not supported.");
        }

        return resData;
    }

    public List<Flashcards> getFlashcards() {
        String userId = authUtil.getCurrentUserId();
        return flashcardsRepository.findAllByUserId(userId);
    }

    public List<Quiz> getQuizzes() {
        String userId = authUtil.getCurrentUserId();
        return quizRepository.findAllByUserId(userId);
    }

    public void updateUserResponse(QuizItemUserResponseRequest req) {
        Quiz quiz = quizRepository.findById(req.getQuizId())
                .orElseThrow(() -> new InvalidRequestException("Quiz with id: " + req.getQuizId() + " not found."));

        QuizItemUserResponse userResponse = req.getResponse();

        for (QuizItem item : quiz.getItems()) {
            if (item.getId().equals(userResponse.getItemId())) {
                item.setResponse(userResponse);
                break;
            }
        }

        quizRepository.save(quiz);
    }

    private Flashcards mapToFlashcards(GenerateRequest req, String userId) {
        Flashcards flashcards = new Flashcards();
        flashcards.setUserId(userId);
        flashcards.setName(req.getName());
        flashcards.setItems(req.getItems().stream().map(item -> (FlashcardItem) item).toList());
        return flashcards;
    }

    private Quiz mapToQuiz(GenerateRequest req, String userId) {
        Quiz quiz = new Quiz();
        quiz.setUserId(userId);
        quiz.setName(req.getName());
        quiz.setItems(req.getItems().stream().map(item -> (QuizItem) item).toList());
        return quiz;
    }
}
