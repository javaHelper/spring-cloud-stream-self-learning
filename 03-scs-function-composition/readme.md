# Spring Cloud Stream - Function Definition


goto: http://localhost:15672/#/ and click on `Exchange: uppercase-in` and publish messages from there



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