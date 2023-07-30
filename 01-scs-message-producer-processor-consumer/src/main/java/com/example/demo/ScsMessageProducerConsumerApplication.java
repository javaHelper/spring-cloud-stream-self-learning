package com.example.demo;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Order;

@SpringBootApplication
public class ScsMessageProducerConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScsMessageProducerConsumerApplication.class, args);
	}
	
	@Bean
	public Supplier<Order> generateOrder(){
		return () -> Order.builder()
				.orderId(ThreadLocalRandom.current().nextLong(1, 100))
				.userId(ThreadLocalRandom.current().nextLong(1, 100))
				.productId(ThreadLocalRandom.current().nextLong(1, 100))
				.build();
	}
	

	@Bean
	public Function<Order, Order> processOrder(){
		return order -> {
			System.out.println("--- Processing Orders");
			order.setStatus("PROCESSED");
			return order;
		};
	}
	
	@Bean
	public Consumer<Order> readOrder(){
		return order -> System.out.println("## Received order message " + order + " at "+ Instant.now().toString());
	}
}
