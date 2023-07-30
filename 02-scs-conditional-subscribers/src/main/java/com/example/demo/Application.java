package com.example.demo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.example.demo.model.Order;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	public Supplier<Flux<Message<Order>>> generateThreeOrderTypes(){
		List<Message<Order>> orders = new ArrayList<>();
		orders.add(MessageBuilder.withPayload(Order.builder()
				.orderId(ThreadLocalRandom.current().nextLong(1, 100))
				.userId(ThreadLocalRandom.current().nextLong(1, 100))
				.productId(ThreadLocalRandom.current().nextLong(1, 100))
				.build())
				.setHeader("orderCountry", "IND")
				.build());	
		
		orders.add(MessageBuilder.withPayload(Order.builder()
				.orderId(ThreadLocalRandom.current().nextLong(1, 100))
				.userId(ThreadLocalRandom.current().nextLong(1, 100))
				.productId(ThreadLocalRandom.current().nextLong(1, 100))
				.build())
				.setHeader("orderCountry", "USA")
				.build());	
		
		
		orders.add(MessageBuilder.withPayload(Order.builder()
				.orderId(ThreadLocalRandom.current().nextLong(1, 100))
				.userId(ThreadLocalRandom.current().nextLong(1, 100))
				.productId(ThreadLocalRandom.current().nextLong(1, 100))
				.build())
				.setHeader("orderCountry", "IND")
				.build());	
		
		
		return () -> Flux.fromIterable(orders);
	}
	
	
	@Bean
	public Consumer<Order> readINDOrder(){
		return order -> System.out.println("== Received Order (IND): "+ order + " at "+ Instant.now().toString());
	}
	
	@Bean
	public Consumer<Order> readUSAOrder(){
		return order -> System.out.println("-- Received Order (USA): "+ order + " at "+ Instant.now().toString());
	}
	
}
