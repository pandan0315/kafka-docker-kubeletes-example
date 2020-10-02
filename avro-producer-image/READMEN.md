# Create docker image for *avro-producer.jar*


###  Build docker image locally
-------------
```
docker build -t pandan0315/avro-producer .
```



### Push docker image to docker hub
----------
```
docker push pandan0315/avro-producer
```

### Go to [Docker Hub ](https://hub.docker.com/) check if image push successfully
------------


### Verify if image works
------------
```
docker run -d \
 --net=cp-all-in-one_default \
 -e kafka=broker:9092 \
 -e schemaUrl=http://schema-registry:8081 \
 -e topic="avrotest" \
 pandan0315/avro-producerr


```