package rx3.chapter2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Ch02_07 {
  public static void main(String[] args) {
    Observer<Integer> myObserver = new Observer<Integer>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(Integer integer) {
        System.out.println("RECEIVED: " + integer);
      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onComplete() {
        // this will be printed after all finished
        System.out.println("DONE !");
      }
    };
    Observable.just("alpha", "beta", "gamma")
        .map(String::length)
        .filter(l -> l >= 5)
        .subscribe(myObserver);
  }

}
