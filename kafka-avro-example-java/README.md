# Create simple Kafka producer and consume for avro message in Java



### Initial Setup
- intall java 11
- install Latest Intellij IDE
- install kafka, zookeeper and schema registry via 
[Confluent platform](https://docs.confluent.io/current/quickstart/ce-docker-quickstart.html?utm_medium=sem&utm_source=google&utm_campaign=ch.sem_br.brand_tp.prs_tgt.confluent-brand_mt.xct_rgn.emea_lng.eng_dv.all&utm_term=confluent%20kafka%20docker&creative=&device=c&placement=&gclid=CjwKCAjw_NX7BRA1EiwA2dpg0vd6OC-RSViIPGYHkMzocvIh0aNwL21omTBuhCiBoCTmyOj8TzCBdhoCrwsQAvD_BwE)

- create topic on kafka server

For example:
```
# run bash command with kafka container
docker exec -it broker bash

# execute create topic bash script
kafka-topics --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 5 --topic avrotest .

# check if topic is created successful
kafka-topics --zookeeper zookeeper:2181 --list

```
- Register schema in Schema Registry server

```

$ curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" --data { "schema": "{ \"type\": \"record\", \"name\": \"User\", \"namespace\": \"example.avro\", \"fields\": [ { \"name\": \"name\", \"type\": \"string\" }, { \"name\": \"favorite_number\", \"type\": [\"int\",\"null\"] }, { \"name\": \"favorite_color\", \"type\": [\"string\",\"null\"] } ]}" } \
http://localhost:8081/subjects/users/versions

```


### Build schema file
```
mvn clean
mvn compile

Then avro schema file  /src/main/java/example/avro/User.java will be automatically genereated.
```

### Run kafka producer

1. build artifacts *producer.jar* from *AvroMessageProducer.java*

2. run *producer.jar* with 3 needed input arguments

```
java -jar producer.jar <kafka server> <schemaUrl> <topic>
```
### Run kafka consumer
1. build artifacts *consumer.jar* from *AvroMessageConsumer.java*

2. run *consumer.jar* with 4 needed input arguments

```
java -jar consumer.jar <kafka server> <groupId> <schemaUrl> <topic>
```
