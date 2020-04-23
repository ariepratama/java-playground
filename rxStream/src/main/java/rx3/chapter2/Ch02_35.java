package rx3.chapter2;

import static java.lang.Thread.sleep;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

public class Ch02_35 {

  public static void main(String[] args) throws InterruptedException {
    Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
    Disposable d1 = source.subscribe(l -> System.out.println("subs 1: " + l));
    Disposable d2 = source.subscribe(l -> System.out.println("subs 2: " + l));

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    compositeDisposable.addAll(d1, d2);
    // let them run for 5s
    sleep(5000);
    System.out.println("waiting to dispose...");
    compositeDisposable.dispose();
    // remember this program is not concurrent
    // this will be printed after both subscription has been disposed
    System.out.println("waiting for another 2s");
    sleep(2000);
  }

}
