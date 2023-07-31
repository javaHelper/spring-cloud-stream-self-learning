package com.example.demo.config;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

@Configuration
public class DynamicDestinationConfig {
	
	@Bean
	public EmitterProcessor<Message<?>> emitterProcessor(){
		return EmitterProcessor.create();
	}
	
	@Bean
	public Supplier<Flux<Message<?>>> supplier() {
		return () -> emitterProcessor();
	}
}
