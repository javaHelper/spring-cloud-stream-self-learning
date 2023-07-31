package com.example.demo.config;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class DynamicDestinationConfig {
	
	@Bean
	public Sinks.Many<Message<?>> emitterProcessor(){
		return Sinks.many().multicast().onBackpressureBuffer();
	}
	
	@Bean
	public Supplier<Flux<Message<?>>> supplier() {
		return () -> emitterProcessor().asFlux();
	}
}
