package rx3.chapter2;

import io.reactivex.Observable;

public class Ch02_02 {

  public static void main(String[] args) {
    Observable<String> source = Observable.create(emitter -> {
      try {
        emitter.onNext("one");
        emitter.onNext("two");
        emitter.onNext("three");
        emitter.onNext("four");
        emitter.onNext("five");
        emitter.onComplete();
      } catch (Throwable e) {
        // observable will call onError when there is error in emitting next value
        emitter.onError(e);
      }
    });
    source.subscribe(s -> System.out.println("RECEIVED: " + s));
  }

}
