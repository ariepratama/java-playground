package rx3.chapter2;

import io.reactivex.Observable;
import java.util.function.Function;

public class Ch02_10 {

  public static void main(String[] args) {
    // here we omit onError function
    // but this is something that we would like to avoid
    Observable<String> observable = Observable.just("one", "two", "three", "threeagain");
    observable
        .map(String::length)
        .filter(l -> l >= 4)
        .subscribe(i -> System.out.println(i));

    // this will throw OnErrorNotImplementedException
    observable
        .map(String::length)
        .filter(l -> l >= 4)
        .map(k -> {
          if (k == 10) {
            throw new RuntimeException();
          }

          return k;
        })
        .subscribe(i -> System.out.println(i));

  }

}
