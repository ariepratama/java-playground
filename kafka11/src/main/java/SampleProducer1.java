import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import protos.Event1Proto;

import java.util.Properties;
import java.util.Random;
import java.util.UUID;

public class SampleProducer1 {
    KafkaProducer<String, byte[]> producer;
    Random random = new Random();

    public SampleProducer1() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");


        this.producer = new KafkaProducer<>(
                properties,
                new StringSerializer(),
                new ByteArraySerializer());
    }

    public void publishRandomMessages(int n) {
        for (int i = 0; i < n ; i++) {
            producer.send(new ProducerRecord<>(Constants.TOPIC_EVENT1, generateRandomMessage(n).toByteArray()));
        }
    }

    public Event1Proto.Event1 generateRandomMessage(int n) {
        return Event1Proto.Event1.newBuilder()
                .setUsername(UUID.randomUUID().toString())
                .setComment(UUID.randomUUID().toString())
                .setSomeMoreComments(UUID.randomUUID().toString())
                .setFirstName(UUID.randomUUID().toString())
                .setLastName(UUID.randomUUID().toString())
                .setId(random.nextInt(n))
                .setEventType(random.nextInt(n))
                .setUsername(UUID.randomUUID().toString())
                .build();
    }

    public static void main(String[] args) {
        SampleProducer1 producer = new SampleProducer1();
        producer.publishRandomMessages(10000);
    }

}
