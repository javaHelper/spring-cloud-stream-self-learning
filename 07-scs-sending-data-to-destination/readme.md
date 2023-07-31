# Sending Data to destination


```json
curl --location 'http://localhost:8080' \
--header 'Content-Type: application/json' \
--data '{
    "orderId": 1,
    "userId": 1,
    "productId": 1,
    "status": "NEW"
}'
```

Response:

```
Sending Order(orderId=1, userId=1, productId=1, status=NEW)
Order Received : Order(orderId=1, userId=1, productId=1, status=NEW)
```