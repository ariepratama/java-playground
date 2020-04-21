package rx3.chapter2;

import io.reactivex.Observable;

public class Ch02_11 {

  public static void main(String[] args) {
    // example of finite "cold" observable
    Observable<String> observable = Observable.just("one", "two", "three", "threeagain");
    observable
        .subscribe(i -> System.out.println("obs 1: " + i));
    // this will restart emitting from "one" and start filtering
    observable
        .filter(s -> s.length() > 3)
        .subscribe(i -> System.out.println("obs 2: " + i));
  }

}
