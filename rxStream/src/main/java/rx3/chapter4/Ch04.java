package rx3.chapter4;

import io.reactivex.Observable;

public class Ch04 {
  public static Observable<String> defaultCase() {
    return Observable.just("I'M", "WALKING", "OVER", "HERE");
  }

  public static Observable<Integer> defaultIntCase() {
    return Observable.range(0, 10);
  }

  /**
   * Like left join
   * ---zip---
   * I'M-0
   * WALKING-1
   * OVER-2
   * HERE-3
   */
  public static void zip(){
    System.out.println("---zip---");
    Observable.zip(defaultCase(), defaultIntCase(), (s, i) -> String.format("%s-%s", s, i))
        .subscribe(System.out::println);
  }
  
  public static void main(String[] args) {
    zip();
  }

}
