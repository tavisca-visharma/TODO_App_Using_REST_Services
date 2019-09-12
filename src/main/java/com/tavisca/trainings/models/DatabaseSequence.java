package com.tavisca.trainings.models;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "database_sequences")
public class DatabaseSequence {

	@Id
	private String id;
	
	private long seq;
	
}
