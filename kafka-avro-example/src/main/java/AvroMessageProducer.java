import example.avro.User;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import producer.AvroProducer;

import java.util.concurrent.ExecutionException;


public class AvroMessageProducer {

    static String TOPIC = "avrotest";

    public static void main(String[] args) {

        if (args.length < 3){
            System.out.println("3 input parameters needed, the order should be bootstrapServer,schemaUrl,topic");
            System.exit(0);

        } else {
            String bootstrapServer = args[0];
            String schemaUrl = args[1];
            String topic = args[2];
            runProducer(bootstrapServer,schemaUrl,topic);
        }

    }

    public static void runProducer(String bootstrapServer,String schemaUrl,String topic){
        Producer avroProducer = AvroProducer.createProducer(bootstrapServer,schemaUrl);

        try{

            for (int i = 1;i<10;i++) {
                User user = User.newBuilder().setName("Anna"+ i).setFavoriteNumber(i).setFavoriteColor("blue"+ i).build();
                ProducerRecord<String,User> record = new ProducerRecord<>(topic,user);
                RecordMetadata metadata = (RecordMetadata) avroProducer.send(record).get();
                System.out.println("Producer:Record " + metadata.toString() + " send to partition " + metadata.partition()
                        + " with offset " + metadata.offset());
            }


        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e) {
            e.printStackTrace();
        }

        avroProducer.flush();
        avroProducer.close();


    }






}
