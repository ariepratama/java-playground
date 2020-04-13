package rx3.chapter2;

import io.reactivex.Observable;

/**
 * onNext don't emit directly, follow sequence of observer commands before calling onNext
 */
public class Ch02_03 {
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
    Observable<Integer> lengths = source.map(String::length); // do this first
    Observable<Integer> filtered = lengths.filter(i -> i >= 5); // then this
    filtered.subscribe(i -> System.out.println("Received: " + i)); // then this, then call onNext
  }

}
