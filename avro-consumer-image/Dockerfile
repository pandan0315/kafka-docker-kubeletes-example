FROM maslick/minimalka:jdk11
ADD avro-consumer.jar avro-consumer.jar
CMD java -jar avro-consumer.jar $kafka $groupId $schemaUrl $topic
