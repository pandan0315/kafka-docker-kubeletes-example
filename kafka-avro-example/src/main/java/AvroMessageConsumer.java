
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.Arrays;


public class AvroMessageConsumer {

    static int MAX_NO_MESSAGE_FOUND_COUNT = 20;
    static String TOPIC = "avrotest";

    public static void main(String[] args) {
        if (args.length < 4){
            System.out.println("4 input parameters needed, the order should be bootstrapServer,groupId,schemaUrl and topic");
            System.exit(0);

        } else {
            String bootstrapServer = args[0];
            String groupId = args[1];
            String schemaUrl = args[2];
            String topic = args[3];
            runConsumer(bootstrapServer,groupId,schemaUrl,topic);
        }
        }

    public static void runConsumer(String bootstrapServer,String groupId, String schemaUrl,String topic){

        Consumer<String, GenericRecord> avroConsumer = consumer.AvroConsumer.createConsumer(bootstrapServer,groupId,schemaUrl);

        avroConsumer.subscribe(Arrays.asList(topic));
        int noMessageToFetch = 0;
        while (true) {

            ConsumerRecords<String,GenericRecord> consumerRecords = avroConsumer.poll(Duration.ofMillis(1000));
            if (consumerRecords.count() == 0) {
                System.out.println(noMessageToFetch + "message(es) are not fetched");
                noMessageToFetch++;
                if (noMessageToFetch > MAX_NO_MESSAGE_FOUND_COUNT)
                    break;
                else
                    continue;
            }

            consumerRecords.forEach(record -> {
                GenericRecord user1 = record.value();
                System.out.println("Consumer: Record Key " + record.key() + ";"+"Record value " + user1+";" +
                        "Record partition " + record.partition()+";" +
                        "Record offset " + record.offset());
            });
            avroConsumer.commitAsync();

        }

        avroConsumer.close();

        }





}
