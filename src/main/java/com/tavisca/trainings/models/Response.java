package com.tavisca.trainings.models;


import java.util.List;

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

	public Response ok(String message, Object data) {
		this.statusCode = HttpStatus.OK.toString();
		this.message = message;
		this.data = data;
		return this;
	}
	
	public Response badRequest(String message, Object data) {
		this.statusCode = HttpStatus.BAD_REQUEST.toString();
		this.message = message;
		this.data = data;
		return this;
	}

}
