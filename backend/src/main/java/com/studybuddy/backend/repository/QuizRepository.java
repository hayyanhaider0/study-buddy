package com.studybuddy.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.studybuddy.backend.dto.llm.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, String> {
    Optional<Quiz> findById(String id);

    List<Quiz> findAllByUserId(String userId);
}
