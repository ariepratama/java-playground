import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.IMap;
import com.hazelcast.map.listener.EntryAddedListener;

public class BookAddEventListener implements EntryAddedListener<Long, Book> {

    private final IMap<Long, Book> bookMap;

    public BookAddEventListener(IMap<Long, Book> bookMap) {
        this.bookMap = bookMap;
    }

    @Override
    public void entryAdded(EntryEvent<Long, Book> entryEvent) {
        System.out.println("id added = " +  entryEvent.getKey());
        bookMap.evictAll();
        System.out.println("new entry added, evicting all");
    }
}
