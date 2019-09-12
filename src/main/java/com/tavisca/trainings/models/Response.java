package com.tavisca.trainings.models;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@Component
public class Response {

	String statusCode;
	String message;
	Object data;

	public Response(String statusCode, String message, Object data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	private void responseContent(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	public Response ok(String message, Object data) {
		this.responseContent(message, data);
		this.statusCode = HttpStatus.OK.toString();
		return this;
	}

	public Response created(String message, Object data) {
		this.responseContent(message, data);
		this.statusCode = HttpStatus.CREATED.toString();
		return this;
	}

	public Response deleted(String message, Object data) {
		this.responseContent(message, data);
		this.statusCode = HttpStatus.NO_CONTENT.toString();
		return this;
	}

}
