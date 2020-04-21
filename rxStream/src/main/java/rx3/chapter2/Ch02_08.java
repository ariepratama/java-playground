package rx3.chapter2;

import io.reactivex.Observable;

public class Ch02_08 {
  public static void main(String[] args) {
    Observable.just("alpha", "beta", "gamma")
        .map(String::length)
        .filter(l -> l >= 5)
        .subscribe(
            i -> System.out.println(i),
            Throwable::printStackTrace,
            () -> System.out.println("Done !")
        );
  }

}
