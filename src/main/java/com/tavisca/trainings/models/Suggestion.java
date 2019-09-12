package com.tavisca.trainings.models;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Suggestion {

	@Setter(AccessLevel.NONE)
	@Id
	private int suggestionId;

	static int counter = 1;

	@NotEmpty
	@NotNull
	private String content;

	@Builder
	public Suggestion(String content) {
		super();
		this.suggestionId = counter++;
		this.content = content;
	}
}
