import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.Properties;

public class SampleConsumer1 implements Runnable{
    private KafkaStreams kStreams;

    public SampleConsumer1() {
        kStreams = new KafkaStreams(initTopology(), initProperties());
    }

    private Topology initTopology() {
        SampleConsumer1EventProcessorSupplier processorSupplier = new SampleConsumer1EventProcessorSupplier();

        Topology topology = new Topology();
        topology.addSource("event1_source_node", "event1");
        topology.addProcessor("event1_root_processor", processorSupplier, "event1_source_node");
        return topology;
    }

    private Properties initProperties() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "sample_consumer1");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass());
        properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 2);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "sample_consumer_1");
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return properties;
    }

    public static void main(String[] args) {
        SampleConsumer1 sampleConsumer1 = new SampleConsumer1();
        sampleConsumer1.run();
    }

    @Override
    public void run() {
        kStreams.start();
    }
}
