package com.tavisca.trainings.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tavisca.trainings.exceptions.SuggestionAlreadyExistsException;
import com.tavisca.trainings.exceptions.SuggestionDoesNotExistsException;
import com.tavisca.trainings.models.Suggestion;
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

	public Suggestion add(Suggestion suggestion) throws SuggestionAlreadyExistsException {
		if (suggestionRepository.findByContent(suggestion.getContent()).isPresent())
			throw new SuggestionAlreadyExistsException("Suggestion Already Exists !!!");
		return suggestionRepository.save(suggestion);
	}

	public Suggestion delete(Suggestion suggestion) throws SuggestionDoesNotExistsException {
		if (suggestionRepository.findByContent(suggestion.getContent()).isPresent())
			throw new SuggestionDoesNotExistsException();
		suggestionRepository.delete(suggestion);
		return suggestion;
	}

	public Suggestion update(Suggestion suggestion) throws SuggestionDoesNotExistsException {
		if (suggestionRepository.findByContent(suggestion.getContent()).isPresent())
			throw new SuggestionDoesNotExistsException();
		return suggestionRepository.save(suggestion);
	}

	public Suggestion updateById(int id, Suggestion suggestion) throws SuggestionDoesNotExistsException {
		Suggestion updateSuggestion = suggestionRepository.findById(id)
				.orElseThrow(SuggestionDoesNotExistsException::new);
		updateSuggestion.setContent(suggestion.getContent());
		return suggestionRepository.save(updateSuggestion);
	}

	public Suggestion deleteById(int id) throws SuggestionDoesNotExistsException {
		Suggestion deletedSuggestion = suggestionRepository.findById(id)
				.orElseThrow(SuggestionDoesNotExistsException::new);
		suggestionRepository.deleteById(id);
		return deletedSuggestion;
	}

}
