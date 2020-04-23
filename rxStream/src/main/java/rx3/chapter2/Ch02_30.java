package rx3.chapter2;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Ch02_30 {

  public static void main(String args[]){
    // Maybe is an observable that return 1 OR 0 elements
    // pretty much like java's Optional
    Maybe<String> source = Maybe.just("100");
    // On complete will not be called when there is an element on MaybeSource / Observable
    source.subscribe(
        x-> System.out.println("1: on Success " + x),
        x -> System.out.println("1: on Error " + x),
        () -> System.out.println("1: on Complete")
    );

    source = Maybe.empty();
    source.subscribe(
        x-> System.out.println("2: on Success" + x),
        x -> System.out.println("2: on Error " + x),
        () -> System.out.println("2: on Complete")
    );

    // in this similar settings as the first scenario, but now with Observable
    // the subscriber will call onSuccess, but also on complete
    Observable<String> anotherSource = Observable.just("200");
    anotherSource.subscribe(
        x -> System.out.println("3: on success " + x),
        x -> System.out.println("3: on Error " + x),
        () -> System.out.println("3: on Complete")
    );

    anotherSource = Observable.empty();
    anotherSource.subscribe(
        x -> System.out.println("3: on success " + x),
        x -> System.out.println("3: on Error " + x),
        () -> System.out.println("3: on Complete")
    );

    // function firstElement() yield Maybe
    // then again will not call on complete
    anotherSource = Observable.just("3", "2", "1");
    anotherSource.firstElement()
        .subscribe(
          x -> System.out.println("4: on success " + x),
          x -> System.out.println("4: on error " + x),
            () -> System.out.println("4: on complete")
        );

  }

}
