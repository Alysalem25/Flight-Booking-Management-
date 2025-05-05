/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programming.project;

/**
 *
 * @author Aly
 */
public class Customer extends User {
//     2. Customer (Extends User) 
// • Responsibility: Manages customer bookings and profiles 
// • Key Attributes: customerId, address, bookingHistory, preferences 
// • Key Methods: searchFlights(), createBooking(), viewBookings(), cancelBooking() 
    private String customerId;
    private String address;
//    private String bookingHistory;
    private String preferences;

    public Customer(String customerId, String address, String preferences, String userId, String username, String password, String name, String email, String contactInfo) {
        super(userId, username, password, name, email, contactInfo);
        this.customerId = customerId;
        this.address = address;
        this.preferences = preferences;
    }
    public String getCustomerId() {
        return customerId;
    }
    public String getAddress() {
        return address;
    }
    public String getPreferences() {
        return preferences;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
    public void searchFlights() {
        // Logic for searching flights
    }
    public void createBooking() {

    }
    public void viewBookings() {
        // Logic for viewing bookings
    }
    public void cancelBooking() {
        // Logic for canceling a booking
    }
}
