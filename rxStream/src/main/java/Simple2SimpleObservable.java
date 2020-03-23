import io.reactivex.Observable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Simple2SimpleObservable {

    public static void main(String[] args) {
        List<String> strList = IntStream.range(0, 50).boxed().map(i -> i + "").collect(Collectors.toList());
        Observable<String> o = Observable.fromIterable(strList);
        // this does not execute anything
        Observable<String> transformed = o.skip(10).take(5).map(x -> x + "_ppp");
        // this will do all those above
        transformed.subscribe(System.out::println);
    }

}
