# 


Request

```curl
curl -H "Content-Type: text/plain" -X POST -d "hello from the other side" http://localhost:8080/
```


Response:

```
Sending hello from the other side
2023-07-31 14:01:14.314  INFO 29240 --- [nio-8080-exec-1] o.s.c.s.m.DirectWithAttributesChannel    : Channel 'unknown.channel.name' has 1 subscriber(s).
2023-07-31 14:01:14.328  INFO 29240 --- [nio-8080-exec-1] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: [localhost:5672]
2023-07-31 14:01:14.354  INFO 29240 --- [nio-8080-exec-1] o.s.a.r.c.CachingConnectionFactory       : Created new connection: rabbitConnectionFactory.publisher#55328f8d:0/SimpleConnection@32f51ed7 [delegate=amqp://guest@127.0.0.1:5672/, localPort= 51464]
2023-07-31 14:01:14.356  INFO 29240 --- [nio-8080-exec-1] o.s.amqp.rabbit.core.RabbitAdmin         : Auto-declaring a non-durable, auto-delete, or exclusive Queue (myDestination.anonymous.OWU5y0PKRHqilmR4_dk6PA) durable:false, auto-delete:true, exclusive:true. It will be redeclared if the broker stops and is restarted while the connection factory is alive, but all messages will be lost.
Message Received : hello from the other side
```