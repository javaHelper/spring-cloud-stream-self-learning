# Multi Functions

- Java Code

```java
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

```


```
2023-07-30 23:45:32.442  INFO 46473 --- [           main] com.example.demo.Application             : Started Application in 4.578 seconds (JVM running for 5.704)
******************
From Source1
******************
Sending value: FromSource1
******************
From Source2
******************
Sending value: FromSource2
******************
At Sink2
******************
******************
At Sink1
******************
Received message FromSource1
Received message FromSource2
******************
From Source1
******************
Sending value: FromSource1
******************
From Source2
******************
Sending value: FromSource2
******************
At Sink2
******************
Received message FromSource2
******************
At Sink1
******************
Received message FromSource1
******************
From Source1
******************
Sending value: FromSource1
******************
From Source2
******************
Sending value: FromSource2
******************
At Sink2
******************
Received message FromSource2
******************
At Sink1
******************
Received message FromSource1
******************
From Source1
******************
Sending value: FromSource1
******************
From Source2
******************
Sending value: FromSource2
******************
At Sink1
******************
Received message FromSource1
******************
At Sink2
******************
Received message FromSource2
******************
From Source1
******************
Sending value: FromSource1
******************
From Source2
******************
Sending value: FromSource2
******************
At Sink1
******************
Received message FromSource1
******************
At Sink2
******************
Received message FromSource2
******************
From Source1
******************
Sending value: FromSource1
******************
From Source2
******************
Sending value: FromSource2
******************
At Sink1
******************
Received message FromSource1
******************
At Sink2
******************
Received message FromSource2
2023-07-30 23:45:38.438  INFO 46473 --- [on(7)-127.0.0.1] inMXBeanRegistrar$SpringApplicationAdmin : Application shutdown requested.
2023-07-30 23:45:38.478  INFO 46473 --- [on(7)-127.0.0.1] o.s.a.r.l.SimpleMessageListenerContainer : Waiting for workers to finish.
******************
From Source1
******************
Sending value: FromSource1
******************
From Source2
******************
Sending value: FromSource2
******************
At Sink2
******************
```