import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.Initializer;
import protos.Event1Proto;

import java.util.Properties;

public class SampleConsumer12 implements Runnable{
    private KafkaStreams kStreams;

    public SampleConsumer12() {
        kStreams = new KafkaStreams(initTopology(), initProperties());
    }

    private Topology initTopology() {
        SampleConsumer1EventProcessorSupplier processorSupplier = new SampleConsumer1EventProcessorSupplier();
        final StreamsBuilder builder = new StreamsBuilder();
        builder.<Void, byte[]>stream("event1")
                .map((k, v) -> {
                    Event1Proto.Event1 event1;
                    try {
                        event1 = Event1Proto.Event1.parseFrom(v);
                        return new KeyValue<>(event1.getEventType() + "", event1.toByteArray());
                    } catch (InvalidProtocolBufferException e) {
                        e.printStackTrace();
                    }
                    return new KeyValue<>(null, null);
                })
                .groupByKey()
                .aggregate(new Initializer<byte[]>() {
                    @Override
                    public byte[] apply() {
                        return Event1Proto.Event1.newBuilder().build().toByteArray();
                    }
                }, (x, e, agg) -> {
                    // get max id for each eventType
                    Event1Proto.Event1 event1, agg1;
                    try {
                        event1 = Event1Proto.Event1.parseFrom(e);
                        agg1 = Event1Proto.Event1.parseFrom(agg);
                        if (agg1.getId() < event1.getId())
                            return event1.toByteArray();
                    } catch (InvalidProtocolBufferException ex) {
                        ex.printStackTrace();
                    }

                    return agg;
                })
//                .toStream()
                .foreach((k, e) -> {
                    System.out.println(e);
                });

        Topology topology = builder.build();

        System.out.println(topology.describe());
//        topology.addSource("event1_source_node", "event1");
//        topology.addProcessor("event1_root_processor", processorSupplier, "event1_source_node");

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
        SampleConsumer12 sampleConsumer1 = new SampleConsumer12();
//        sampleConsumer1.run();
    }

    @Override
    public void run() {
        kStreams.start();
    }
}
