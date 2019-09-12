package com.tavisca.trainings.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tavisca.trainings.models.Suggestion;

@Repository
public interface SuggestionRepository extends MongoRepository<Suggestion, Integer> {

}
