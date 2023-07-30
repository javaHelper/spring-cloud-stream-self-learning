# Spring Cloud Stream - Producer and Consumer

```
docker run -d --hostname local-rabbit --name pluralsight-rmq -p 15672:15672 rabbitmq:3.9.9-management 
```


How to run:

- Simply run the program main method 

```
## Received order message Order(orderId=4, userId=80, productId=96, status=ACCEPTED) at 2023-07-30T15:42:20.789691Z
## Received order message Order(orderId=62, userId=43, productId=33, status=ACCEPTED) at 2023-07-30T15:42:21.739143Z
## Received order message Order(orderId=21, userId=73, productId=47, status=ACCEPTED) at 2023-07-30T15:42:22.747829Z
## Received order message Order(orderId=32, userId=46, productId=81, status=ACCEPTED) at 2023-07-30T15:42:23.768191Z
## Received order message Order(orderId=50, userId=40, productId=49, status=ACCEPTED) at 2023-07-30T15:42:24.766062Z
## Received order message Order(orderId=91, userId=70, productId=10, status=ACCEPTED) at 2023-07-30T15:42:25.776479Z
## Received order message Order(orderId=17, userId=47, productId=25, status=ACCEPTED) at 2023-07-30T15:42:26.787071Z
## Received order message Order(orderId=79, userId=95, productId=65, status=ACCEPTED) at 2023-07-30T15:42:27.797626Z
```

-------

# Code


```java
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
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
				.status("ACCEPTED")
				.build();
	}
	
	
	@Bean
	public Consumer<Order> readOrder(){
		return order -> System.out.println("## Received order message " + order + " at "+ Instant.now().toString());
	}
}
```

- application.yml

```yml
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
        readOrder-in-0:
          destination: ordertopic
          content-type: application/json

          
    function:
      definition: generateOrder;readOrder
```

