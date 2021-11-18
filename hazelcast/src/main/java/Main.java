import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class Main {
    public static void main(String[] args) {
        ClientConfig c = new ClientConfig();
        c.setClusterName("test");

        HazelcastInstance hz = HazelcastClient.newHazelcastClient(c);
        IMap<Long, Book> map = hz.getMap("book");

        map.addEntryListener(new BookAddEventListener(map), true);
        // this will call BookAddEventListener
        map.put(1L, new Book());
        map.put(2L, new Book());

        System.out.println(map.get(2L));

        IMap<Long, String> someOtherMap = hz.getMap("other_map");
        // this will not call BookAddEventListener
        someOtherMap.put(11L, "a");

    }
}
