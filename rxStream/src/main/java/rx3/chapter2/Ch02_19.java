package rx3.chapter2;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import java.util.concurrent.TimeUnit;

public class Ch02_19 {

  private static void sleep(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    // Observable.interval will spawn another thread
    // .publish making the interval to become hot, because by default Observable.interval will be cold
    ConnectableObservable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS).publish();
    seconds.subscribe(s -> System.out.println("obs 1 :" + s));
    seconds.connect();
    // we hold main thread from termination while letting
    // subscriber 1 to consume
    sleep(2000);
    // then start subscriber 2 to consume the "hot" observable.
    seconds.subscribe(s -> System.out.println("obs 2: " + s));
    sleep(2000);

  }

}
