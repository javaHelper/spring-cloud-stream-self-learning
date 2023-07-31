package com.example.demo;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Order;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Consumer<Order> readOrder(){
		return order -> System.out.println("Order Received : "+ order);
	}
	
	
	@Bean
	public Function<Order, Order> passThroughProcessor(){
		return order -> order;
	}
}
