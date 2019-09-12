package com.tavisca.trainings.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.tavisca.trainings.models.Todo;
import com.tavisca.trainings.services.SequenceGeneratorService;

@Component
public class TodoModelListener extends AbstractMongoEventListener<Todo> {

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	/*
	 * @Override public void onBeforeConvert(BeforeConvertEvent<Todo> event) {
	 * if(event.getSource().getId() < 1){
	 * event.getSource().setId(sequenceGeneratorService.generateSequence(Todo.
	 * SEQUENCE_NAME)); } }
	 */

}
