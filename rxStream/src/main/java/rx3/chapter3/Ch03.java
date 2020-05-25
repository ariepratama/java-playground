package rx3.chapter3;

import io.reactivex.Observable;

public class Ch03 {

  public static Observable<String> defaultCase() {
    return Observable.just("One", "Two", "Three");
  }

  public static Observable<String> alternative() {
    return Observable.just("Four", "Five");
  }

  public static Observable<String> duplicated() {
    return Observable.just("One", "One", "Two", "Two");
  }

  public static Observable<Integer> containsZeroCase() {
    return Observable.just(3, 2, 1, 0, -1);
  }

  /**
   * -------conditional--------
   * One
   * Two
   */
  public static void conditional() {
    System.out.println("-------conditional--------");
    defaultCase()
      .takeWhile(s -> s.length() == 3)
      .subscribe(System.out::println);
  }

  /**
   * -----conditional_whenEmpty-------
   * No Item
   */
  public static void conditional_whenEmpty(){
    System.out.println("-----conditional_whenEmpty-------");
    defaultCase()
        .filter(s -> s.startsWith("x"))
        .defaultIfEmpty("No Item")
        .subscribe(System.out::println);
  }

  /**
   * ---------conditional_switchWhenEmpty---------
   * Four
   * Five
   */
  public static void conditional_switchWhenEmpty() {
    System.out.println("---------conditional_switchWhenEmpty---------");
    defaultCase()
        .filter(s -> s.startsWith("x"))
        .switchIfEmpty(alternative())
        .subscribe(System.out::println);
  }

  /**
   * ---------skip----------
   * Two
   * Three
   */
  public static void skip() {
    System.out.println("---------skip----------");
    defaultCase()
        .skip(1)
        .subscribe(System.out::println);
  }

  /**
   * ----------distinct--------
   * One
   * Two
   */
  public static void distinct() {
    System.out.println("----------distinct--------");

    duplicated()
        .distinct()
        .subscribe(System.out::println);
  }

  /**
   *----------retry-----------
   * Received: 33
   * Received: 50
   * Received: 100
   * Received: 33
   * Received: 50
   * Received: 100
   * Received: 33
   * Received: 50
   * Received: 100
   * Error: java.lang.ArithmeticException: / by zero
   */
  public static void retry() {
    System.out.println("----------retry-----------");
    containsZeroCase()
        .map(i -> 100  / i)
        .retry(2)
        .subscribe(
            i -> System.out.println("Received: " + i),
            e -> System.out.println("Error: " + e.toString())
        );
  }

  public static void main(String[] args) {
    conditional();
    conditional_whenEmpty();
    conditional_switchWhenEmpty();
    skip();
    distinct();
    retry();
  }

}
