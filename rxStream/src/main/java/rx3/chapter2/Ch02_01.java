package rx3.chapter2;

import io.reactivex.Observable;

public class Ch02_01 {

  public static void main(String[] args) {
    Observable<String> source = Observable.create(emitter -> {
      emitter.onNext("one");
      emitter.onNext("two");
      emitter.onNext("three");
      emitter.onComplete();
    });
    source.subscribe(s -> System.out.println("RECEIVED: " + s));
  }

}
