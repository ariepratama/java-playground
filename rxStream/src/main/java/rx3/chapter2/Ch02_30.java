package rx3.chapter2;

import io.reactivex.Observable;
import io.reactivex.Single;

public class Ch02_30 {

  public static void main(String args[]){
    // single is an observable that only emit 1 output
    Single.just("yellow")
        .map(String::length)
        .subscribe(System.out::println);

    // calling Observable.first will return single
    // because single needs to emit 1 element, then there's a default item to be returned
    // in case it's empty
    Observable<String> source = Observable.just("one", "two", "three");
    source.first("None")
        .subscribe(System.out::println);

    source = Observable.empty();
    source.first("No El")
        .subscribe(System.out::println);
  }

}
