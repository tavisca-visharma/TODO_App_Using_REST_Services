package com.tavisca.trainings.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tavisca.trainings.models.Todo;

@Repository
public interface TodoRepository extends MongoRepository<Todo, Long>{

}
