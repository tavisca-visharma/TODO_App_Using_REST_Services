package com.tavisca.trainings.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tavisca.trainings.models.Suggestion;
import com.tavisca.trainings.models.Todo;
import com.tavisca.trainings.repositories.SuggestionRepository;

@Component
public class SuggestionDAO {

	List<Suggestion> suggestions;

	public SuggestionDAO() {
		suggestions = new ArrayList<Suggestion>();
	}

	@Autowired
	SuggestionRepository suggestionRepository;

	public List<Suggestion> getAll() {
		return suggestionRepository.findAll();
	}

	public Suggestion add(Suggestion suggestion) {
		return suggestionRepository.save(suggestion);
	}

	public Suggestion delete(Suggestion suggestion) {
		suggestionRepository.delete(suggestion);
		return suggestion;
	}

	public Suggestion update(Suggestion suggestion) {
		return suggestionRepository.save(suggestion);
	}

	public Suggestion updateById(int id, Suggestion suggestion) {
		Suggestion updateSuggestion = suggestionRepository.findById(id).orElseThrow();
		updateSuggestion.setContent(suggestion.getContent());
		return suggestionRepository.save(updateSuggestion);
	}

	public Suggestion deleteById(int id) {
		Suggestion deletedSuggestion = suggestionRepository.findById(id).orElseThrow();
		suggestionRepository.deleteById(id);
		return deletedSuggestion;
	}

}
