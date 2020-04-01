import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import models.Flight;
import models.Passenger;

public class ListStreamTwoOperations {
  public static void main(String[] args) {
    List<Flight> flights = Constants.defaultFlights();

    /** will print A 1 B 2 C 3 D 4 */
    flights.stream()
        .flatMap(flight -> flight.passengers.stream())
        .sequential()
        .peek(p -> System.out.println(p.name))
        .peek(p -> System.out.println(p.id))
        .collect(Collectors.toList());
    System.out.println("---------");
    /**
     * will print C 3 D 4 A 1 B 2
     * the sequence might not be exact, but
     * */
    flights.stream()
        .flatMap(flight -> flight.passengers.stream())
        .parallel()
        .peek(p -> System.out.println(p.name))
        .peek(p -> System.out.println(p.id))
        .collect(Collectors.toList());
  }
}
