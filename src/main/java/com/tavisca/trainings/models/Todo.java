package com.tavisca.trainings.models;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Todo {

	@Transient
	public static final String SEQUENCE_NAME = "todo_sequence";

	@Id
	private long id;

	@NotNull
	@NotEmpty
	private String timeStamp;

	@NotNull
	@NotEmpty
	private String content;

	@NotNull
	@NotEmpty
	private String checked;

	@Builder
	public Todo(String content, String checked) {
		super();
		this.timeStamp = new Date().toString();
		this.content = content;
		this.checked = checked;
	}
}
