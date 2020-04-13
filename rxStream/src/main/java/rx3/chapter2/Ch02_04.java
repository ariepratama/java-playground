package rx3.chapter2;

import io.reactivex.Observable;

/**
 * Chaining call instead of saving to variable
 */
public class Ch02_04 {
  public static void main(String[] args) {
    Observable<String> source = Observable.create(emitter -> {
      try {
        System.out.println("start with: Alpha");
        emitter.onNext("Alpha");
        System.out.println("next: Beta, should be skipped");
        emitter.onNext("Beta");
        System.out.println("next: Gamma");
        emitter.onNext("Gamma");
        emitter.onComplete();
      } catch (Exception e) {
        emitter.onError(e);
      }
    });
    source.map(String::length) // do this first
          .filter(i -> i >= 5) // then this
          .subscribe(i -> System.out.println("Received: " + i)); // then this, then call onNext
  }

}
