package com.tavisca.trainings.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJsonStringConverter {

	public String toJson(Object object) {

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = null;

		try {
			jsonString = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonString;

	}

}
