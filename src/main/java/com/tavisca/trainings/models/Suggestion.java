package com.tavisca.trainings.models;

import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Suggestion {

	@Transient
	public static final String SEQUENCE_NAME = "suggestion_sequence";

	@Id
	private long id;

	@NotEmpty
	@NotNull
	private String content;

	@Builder
	public Suggestion(String content) {
		super();
		this.content = content;
	}
}
