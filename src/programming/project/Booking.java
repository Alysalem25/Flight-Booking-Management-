/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programming.project;

import java.security.Key;

/**
 *
 * @author Aly
 */
public class Booking {
//     6. Booking 
// • Responsibility: Manages booking information and status 
// • Key Attributes: bookingReference, customer, flight, passengers, seatSelections, status, paymentStatus 
// • Key Methods: addPassenger(), calculateTotalPrice(), confirmBooking(), cancelBooking(), 
// generateItinerary()

    private String bookingReference;
    private String customer;
    private String flight;
    private String passengers;
    private String seatSelections;
    private String status;
    private String paymentStatus;

    public Booking(String bookingReference, String customer, String flight, String passengers, String seatSelections, String status, String paymentStatus) {
        this.bookingReference = bookingReference;
        this.customer = customer;
        this.flight = flight;
        this.passengers = passengers;
        this.seatSelections = seatSelections;
        this.status = status;
        this.paymentStatus = paymentStatus;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public String getCustomer() {
        return customer;
    }

    public String getFlight() {
        return flight;
    }

    public String getPassengers() {
        return passengers;
    }

    public String getSeatSelections() {
        return seatSelections;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public void setSeatSelections(String seatSelections) {
        this.seatSelections = seatSelections;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    

}
