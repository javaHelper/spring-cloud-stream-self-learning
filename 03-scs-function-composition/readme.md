# Spring Cloud Stream - Function Definition


goto: http://localhost:15672/#/ and click on `Exchange: uppercase-in` and publish messages from there

<img width="815" alt="Screenshot 2023-07-30 at 11 39 41 PM" src="https://github.com/javaHelper/spring-cloud-stream-self-learning/assets/54174687/cac4be19-7564-42a4-a8d9-7d1bbf932ebc">

You can see output below:

```
2023-07-30 23:38:23.780  INFO 45652 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-07-30 23:38:23.797  INFO 45652 --- [           main] com.example.demo.Application             : Started Application in 4.371 seconds (JVM running for 5.563)
Uppercase: HELLO, HOW ARE YOU DOING ?
Echo: HELLO, HOW ARE YOU DOING ?
2023-07-30 23:39:35.074  INFO 45652 --- [cCUPcMNwamhLg-1] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: [localhost:5672]
2023-07-30 23:39:35.107  INFO 45652 --- [cCUPcMNwamhLg-1] o.s.a.r.c.CachingConnectionFactory       : Created new connection: rabbitConnectionFactory.publisher#36cff453:0/SimpleConnection@45ac9525 [delegate=amqp://guest@127.0.0.1:5672/, localPort= 57718]
2023-07-30 23:39:35.113  INFO 45652 --- [cCUPcMNwamhLg-1] o.s.amqp.rabbit.core.RabbitAdmin         : Auto-declaring a non-durable, auto-delete, or exclusive Queue (uppercase-in.anonymous.E3eAdv2tRcCUPcMNwamhLg) durable:false, auto-delete:true, exclusive:true. It will be redeclared if the broker stops and is restarted while the connection factory is alive, but all messages will be lost.

```


```
curl --location 'http://localhost:8080/uppercase' \
--header 'Content-Type: text/plain' \
--data 'prateek
'
```



```
curl --location 'http://localhost:8080/echo' \
--header 'Content-Type: text/plain' \
--data 'prateek
'
```


```
curl --location 'http://localhost:8080/' \
--header 'Content-Type: text/plain' \
--data 'prateek
'
```
