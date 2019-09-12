package com.tavisca.trainings.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tavisca.trainings.models.Suggestion;

@Repository
public interface SuggestionRepository extends MongoRepository<Suggestion, Integer> {
	
	Optional<Suggestion> findByContent(String content);
	
}
