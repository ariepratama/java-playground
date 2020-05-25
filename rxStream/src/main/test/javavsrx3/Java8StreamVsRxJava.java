package javavsrx3;

import io.reactivex.Observable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.testng.annotations.Test;

public class Java8StreamVsRxJava {


  @Test
  void case1EasyToCombineStreamJava() {
    List<Integer> list1 = IntStream.range(0, 10)
            .boxed()
            .collect(Collectors.toList());
    List<Integer> list2 = IntStream.range(1, 11)
            .boxed()
            .collect(Collectors.toList());

    for (int i = 0; i < list1.size(); i++) {
      System.out.println(list1.get(i) + list2.get(i));
    }
  }


  @Test
  void case1EasyToCombineStreamRx() {
    Observable.zip(
            Observable.range(0, 10),
            Observable.range(1, 11),
            (x, y) -> x + y)
        .subscribe(System.out::println);
  }

  @Test
  void case2EasyReuseJava() {
    Stream<Integer> intStream = IntStream.range(0, 10).boxed();
    intStream.forEach(System.out::println);
    intStream.forEach(System.out::println);
  }

  @Test
  void case2EasyReuseRx() {
    Observable<Integer> intObservable =  Observable.range(0, 10);
    intObservable.subscribe(System.out::println);
    intObservable =  Observable.range(0, 10);
    intObservable.subscribe(System.out::println);
  }

  @Test
  void case3EasyErrorHandlingJava(){
    IntStream.range(-4, 5)
        .map(i -> 1 / i)
        .forEach(System.out::println);
  }

  @Test
  void case3EasyErrorHandlingRx(){
    Observable.range(-4, 5)
        .map(i -> 1 / i)
        .subscribe(
                System.out::println,
                t -> System.out.println("some error happened"));

    System.out.println("---");

    Observable.range(-4, 5)
        .map(i -> 1 / i)
        .onErrorResumeNext(x -> Observable.range(1, 5))
        .subscribe(
                System.out::println,
                t -> System.out.println("some error happened"));
  }


}
