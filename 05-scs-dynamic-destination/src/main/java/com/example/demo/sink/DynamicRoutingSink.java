package com.example.demo.sink;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicRoutingSink {
	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicRoutingSink.class);
	
	@Bean
	public Consumer<String> receive1() {
		return data -> LOGGER.info("== Data received from customer-1..." + data);
	}

	@Bean
	public Consumer<String> receive2() {
		return data -> LOGGER.info("## Data received from customer-2..." + data);
	}

}
