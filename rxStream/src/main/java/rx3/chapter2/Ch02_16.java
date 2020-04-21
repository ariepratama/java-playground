package rx3.chapter2;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class Ch02_16 {

  public static void main(String[] args) {
    Observable.range(5, 3)
        .subscribe(i -> System.out.println("obs 1: " + i));
  }
}
