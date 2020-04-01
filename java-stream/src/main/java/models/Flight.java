package models;

import java.util.List;

public class Flight {
  public String flightCode;
  public List<Passenger> passengers;

  public Flight(String flightCode, List<Passenger> passengers) {
    this.flightCode = flightCode;
    this.passengers = passengers;
  }

}
