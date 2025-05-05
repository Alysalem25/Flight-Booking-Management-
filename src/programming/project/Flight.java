/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programming.project;

/**
 *
 * @author Aly
 */
public class Flight {
//     5. Flight 
// • Responsibility: Stores flight information and manages seats 
// • Key Attributes: flightNumber, airline, origin, destination, departureTime, arrivalTime, availableSeats, 
// prices 
// • Key Methods: checkAvailability(), updateSchedule(), calculatePrice(), reserveSeat() 
    private String flightNumber;
    private String airline;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;
    private double prices;

    public Flight(String flightNumber, String airline, String origin, String destination, String departureTime, String arrivalTime, int availableSeats, double prices) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
        this.prices = prices;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public void setAirline(String airline) {
        this.airline = airline;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    public void checkAvailability() {
        // Logic to check seat availability
    }
    public void updateSchedule() {
        // Logic to update flight schedule
    }
    public double calculatePrice(int numberOfSeats) {

        return numberOfSeats * prices;
    }
    public void reserveSeat() {

    }
}
