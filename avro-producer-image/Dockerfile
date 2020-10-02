FROM maslick/minimalka:jdk11
ADD avro-producer.jar avro-producer.jar
CMD java -jar avro-producer.jar $kafka $schemaUrl $topic
