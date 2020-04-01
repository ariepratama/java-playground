import java.util.Map;
import java.util.stream.Collectors;

public class ListToMap {

  public static void main(String[] args) {
    // we wanted to create mapping between passenger id and
    Map<String, Integer> mapNameToId =
        Constants.defaultFlights().stream()
            .flatMap(f -> f.passengers.stream())
            .collect(Collectors.toMap(p -> p.name, p -> p.id));
    // resulting in: "{A=1, B=2, C=3, D=4}"
    System.out.println(mapNameToId);
  }
}
