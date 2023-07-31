package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.EmitterProcessor;

@RestController
public class DynamicDestinationController {
	
	@Autowired
	private EmitterProcessor<Message<?>> emitterProcessor;
	
	@Autowired
	private ObjectMapper jsonMapper;

	@SuppressWarnings("unchecked")
	@PostMapping("/")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void handleRequest(@RequestBody String body, @RequestHeader(HttpHeaders.CONTENT_TYPE) Object contentType) throws Exception {
		Map<String, String> payload = jsonMapper.readValue(body, Map.class);
		
		String destinationName = payload.get("id");
		
		Message<?> message = MessageBuilder.withPayload(payload)
				.setHeader("spring.cloud.stream.sendto.destination", destinationName)
				.build();
		
		emitterProcessor.onNext(message);
	}
}
