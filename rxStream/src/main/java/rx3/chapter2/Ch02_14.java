package rx3.chapter2;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class Ch02_14 {

  public static void main(String[] args) {
    // example of finite "cold" observable -> hot, by using .publish
    // this will convert Observable to ConnectableObservables
    ConnectableObservable<String> observable = Observable.just("one", "two", "three", "threeagain")
        .publish();

    observable
        .subscribe(i -> System.out.println("obs 1: " + i));
    observable
        .subscribe(i -> System.out.println("obs 2: " + i));
    observable.connect();
//    resulting in:
//    obs 1: one
//    obs 2: one
//    obs 1: two
//    obs 2: two
//    obs 1: three
//    obs 2: three
//    obs 1: threeagain
//    obs 2: threeagain
//    instead of printing from obs 1 then obs 2, this hot observable notice all the subscriber before continuing onNext()
//    or is known as multicasting
  }
}
