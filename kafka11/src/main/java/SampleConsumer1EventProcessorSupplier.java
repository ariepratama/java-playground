import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protos.Event1Proto;

public class SampleConsumer1EventProcessorSupplier implements ProcessorSupplier<String, byte[]> {
    private static final Logger LOG = LoggerFactory.getLogger(SampleConsumer1EventProcessorSupplier.class);
    public static class Event1Processor extends AbstractProcessor<String, byte[]> {

        @Override
        public void process(String s, byte[] bytes) {
            try {
                Event1Proto.Event1 event1 = Event1Proto.Event1.parseFrom(bytes);
                doSomething(event1);
                System.out.println(String.format("event content: %s", event1.toString()));
                LOG.info("event content: {}", event1.toString());
            } catch (InvalidProtocolBufferException e) {
                LOG.error("Unexpected error !!!", e);
            } finally {
                context().commit();
            }
        }

        public void doSomething(Event1Proto.Event1 event1) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Processor<String, byte[]> get() {
        return new Event1Processor();
    }
}
