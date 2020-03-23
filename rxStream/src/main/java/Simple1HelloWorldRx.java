import io.reactivex.Flowable;

public class Simple1HelloWorldRx {
    public static void main(String[] args) {
        String[] arrayOfString = new String[]{
            "one",
            "two",
            "three"
        };
        Flowable.fromArray(arrayOfString).subscribe(System.out::println);
    }

}
