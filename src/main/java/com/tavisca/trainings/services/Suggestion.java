package com.tavisca.trainings.services;

public class Suggestion {
	private String content;

	public Suggestion(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Suggestions [content=" + content + "]";
	}

}
