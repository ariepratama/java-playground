import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import models.Flight;
import models.Passenger;

public class Constants {
  public static List<Flight> defaultFlights() {
    List<Flight> flights = new ArrayList<>();
    flights.add(
        new Flight(
            "FLIGHT_1",
            Stream.of(new Passenger(1, "A"), new Passenger(2, "B")).collect(Collectors.toList())));
    flights.add(
        new Flight(
            "FLIGHT_2",
            Stream.of(new Passenger(3, "C"), new Passenger(4, "D")).collect(Collectors.toList())));
    return flights;
  }
}
