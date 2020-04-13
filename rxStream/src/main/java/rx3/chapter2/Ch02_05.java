package rx3.chapter2;

import io.reactivex.Observable;

/**
 * For constant input, use {@link Observable#just(Object)} instead
 */
public class Ch02_05 {
  public static void main(String[] args) {
    Observable.just("Alpha", "Beta", "Gamma")
        .map(String::length) // do this first
        .filter(i -> i >= 5) // then this
        .subscribe(i -> System.out.println("Received: " + i)); // then this, then call onNext
  }

}
