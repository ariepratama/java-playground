package rx3.chapter2;

import io.reactivex.Observable;

import java.util.List;

/**
 * For constant input, or use {@link Observable#fromIterable(Iterable)} instead
 */
public class Ch02_06 {
  public static void main(String[] args) {
//    Observable.fromIterable(List.of("Alpha", "Beta", "Gamma"))
//        .map(String::length) // do this first
//        .filter(i -> i >= 5) // then this
//        .subscribe(i -> System.out.println("Received: " + i)); // then this, then call onNext
  }

}
