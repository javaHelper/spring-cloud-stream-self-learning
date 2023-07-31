# Spring Cloud Stream - Dynamic Destination

- Sample Request 1

```json
curl --location 'http://localhost:8080' \
--header 'Content-Type: application/json' \
--data '{
    "id": "customerId-1",
    "bill-pay": 100
}'
```


```
2023-07-31 09:25:05.619  INFO 2176 --- [oundedElastic-1] o.s.amqp.rabbit.core.RabbitAdmin         : Auto-declaring a non-durable, auto-delete, or exclusive Queue (customerId-2.anonymous.xRVY027dRyeJbBMeqJBFTQ) durable:false, auto-delete:true, exclusive:true. It will be redeclared if the broker stops and is restarted while the connection factory is alive, but all messages will be lost.
2023-07-31 09:25:06.927  INFO 2176 --- [Yqs3odhnVRVVA-1] c.example.demo.sink.DynamicRoutingSink   : == Data received from customer-1...{"id":"customerId-1","bill-pay":100}

```

-------

Sample Request 2

```json
curl --location 'http://localhost:8080' \
--header 'Content-Type: application/json' \
--data '{
    "id": "customerId-2",
    "bill-pay": "150"
}'
```



```
2023-07-31 09:26:44.175  INFO 2176 --- [oundedElastic-1] o.s.c.s.m.DirectWithAttributesChannel    : Channel 'unknown.channel.name' has 1 subscriber(s).
2023-07-31 09:26:45.803  INFO 2176 --- [yeJbBMeqJBFTQ-1] c.example.demo.sink.DynamicRoutingSink   : ## Data received from customer-2...{"id":"customerId-2","bill-pay":"150"}

```

