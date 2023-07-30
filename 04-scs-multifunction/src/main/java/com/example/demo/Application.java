package com.example.demo;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Consumer<String> sink1() {
		return message -> {
			System.out.println("******************");
			System.out.println("At Sink1");
			System.out.println("******************");
			System.out.println("Received message " + message);
		};
	}

	@Bean
	public Consumer<String> sink2() {
		return message -> {
			System.out.println("******************");
			System.out.println("At Sink2");
			System.out.println("******************");
			System.out.println("Received message " + message);
		};
	}

	@Bean
	public Supplier<String> source1() {
		return () -> {
			String message = "FromSource1";
			System.out.println("******************");
			System.out.println("From Source1");
			System.out.println("******************");
			System.out.println("Sending value: " + message);
			return message;

		};
	}

	@Bean
	public Supplier<String> source2() {
		return () -> {
			String message = "FromSource2";
			System.out.println("******************");
			System.out.println("From Source2");
			System.out.println("******************");
			System.out.println("Sending value: " + message);
			return message;

		};
	}
}
