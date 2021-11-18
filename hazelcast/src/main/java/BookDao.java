import com.hazelcast.map.IMap;

import java.util.HashMap;
import java.util.Map;

public class BookDao {
    private static Map<Long, Book> internalDb = new HashMap<>();

    private final IMap<Long, Book> bookMap;

    public BookDao(IMap<Long, Book> bookMap) {
        this.bookMap = bookMap;
    }


    Book find(long id) {
        if (internalDb.containsKey(id)) {
            return null;
        }

        return internalDb.get(id);
    }

    void insert(Book book) {
        internalDb.put(book.id, book);
        bookMap.put(book.id, book);
    }
}
