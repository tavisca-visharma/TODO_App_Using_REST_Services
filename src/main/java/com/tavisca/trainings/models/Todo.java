package com.tavisca.trainings.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Todo {

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	@NotEmpty
	private String timeStamp;

	@NotNull
	@NotEmpty
	private String content;

	@NotNull
	@NotEmpty
	private String checked;

	static int counter = 1;

	@Builder
	public Todo(String content, String checked) {
		super();
		this.id = counter++;
		this.timeStamp = new Date().toString();
		this.content = content;
		this.checked = checked;
	}
}
