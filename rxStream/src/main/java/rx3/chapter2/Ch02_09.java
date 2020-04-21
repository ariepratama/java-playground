package rx3.chapter2;

import io.reactivex.Observable;

public class Ch02_09 {
  public static void main(String[] args) {
    Observable.just("one", "two", "three")
        .map(String::length)
        .filter(l -> l >= 4)
        .subscribe(i -> System.out.println(i), Throwable::printStackTrace);
  }

}
