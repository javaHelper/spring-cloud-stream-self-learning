# Producer Processor Consumer

# Pre-requisites

- Create exchange `orderprocessor` of type = TOPIC
- Create queue name = `statusqueue` and bind to `orderprocessor` exchange with routing key #


```

2023-07-30 22:05:23.621  INFO 19430 --- [           main] c.s.b.r.p.RabbitExchangeQueueProvisioner : declaring queue for inbound: orderprocessor.anonymous.OfnxqkfuQUexhnJNvA5vrg, bound to: orderprocessor
2023-07-30 22:05:23.640  INFO 19430 --- [           main] o.s.c.stream.binder.BinderErrorChannel   : Channel 'orderprocessor.anonymous.OfnxqkfuQUexhnJNvA5vrg.errors' has 1 subscriber(s).
2023-07-30 22:05:23.640  INFO 19430 --- [           main] o.s.c.stream.binder.BinderErrorChannel   : Channel 'orderprocessor.anonymous.OfnxqkfuQUexhnJNvA5vrg.errors' has 2 subscriber(s).
2023-07-30 22:05:23.651  INFO 19430 --- [           main] o.s.i.a.i.AmqpInboundChannelAdapter      : started bean 'inbound.orderprocessor.anonymous.OfnxqkfuQUexhnJNvA5vrg'
--- Processing Orders
## Received order message Order(orderId=9, userId=44, productId=49, status=PROCESSED) at 2023-07-30T16:35:23.663152Z
2023-07-30 22:05:23.672  INFO 19430 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-07-30 22:05:23.686  INFO 19430 --- [           main] .d.ScsMessageProducerConsumerApplication : Started ScsMessageProducerConsumerApplication in 4.683 seconds (JVM running for 10.609)
--- Processing Orders
## Received order message Order(orderId=63, userId=92, productId=8, status=PROCESSED) at 2023-07-30T16:35:24.595549Z
--- Processing Orders
## Received order message Order(orderId=1, userId=85, productId=41, status=PROCESSED) at 2023-07-30T16:35:25.609291Z
--- Processing Orders
## Received order message Order(orderId=22, userId=74, productId=73, status=PROCESSED) at 2023-07-30T16:35:26.620730Z
--- Processing Orders
## Received order message Order(orderId=20, userId=42, productId=55, status=PROCESSED) at 2023-07-30T16:35:27.624569Z
--- Processing Orders
## Received order message Order(orderId=65, userId=26, productId=64, status=PROCESSED) at 2023-07-30T16:35:28.637071Z
--- Processing Orders
## Received order message Order(orderId=33, userId=61, productId=7, status=PROCESSED) at 2023-07-30T16:35:29.660095Z
--- Processing Orders
## Received order message Order(orderId=47, userId=2, productId=40, status=PROCESSED) at 2023-07-30T16:35:30.667158Z
--- Processing Orders
## Received order message Order(orderId=88, userId=13, productId=34, status=PROCESSED) at 2023-07-30T16:35:31.674624Z
```

# Java Code


```java
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

```

application.yml

```
spring:
  rabbitmq:
    host: localhost
    username: guest
    password: guest
    port: 5672
    
    
  cloud:
    stream:
      bindings:
        generateOrder-out-0:
          destination: ordertopic
          content-type: application/json
          
        processOrder-in-0: 
          destination: ordertopic
          content-type: application/json  
        
        processOrder-out-0: 
          destination: orderprocessor
          content-type: application/json  
        
        readOrder-in-0: 
          destination: orderprocessor
          content-type: application/json

          
    function:
      definition: generateOrder;processOrder;readOrder
```