package com.studybuddy.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.studybuddy.backend.dto.llm.Flashcards;

public interface FlashcardsRepository extends MongoRepository<Flashcards, String> {
    Optional<Flashcards> findById(String id);

    List<Flashcards> findByUserId(String userId);
}
