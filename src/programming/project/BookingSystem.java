/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programming.project;

/**
 *
 * @author Aly
 */
public class BookingSystem {
//     9. BookingSystem 
// • Responsibility: Central coordinator for the booking system 
// • Key Attributes: users, flights, bookings, payments 
// • Key Methods: searchFlights(), createBooking(), processPayment(), generateTicket()
    
        private String users;
                private String flights;
        private String bookings;
        private String payments;


        public void searchFlights() {

        }
        
        public void createBooking() {

        }
        
        public void processPayment() {

        }
        
        public void generateTicket() {

        }
        

        public BookingSystem(String users, String flights, String bookings, String payments) {
            this.users = users;
            this.flights = flights;
            this.bookings = bookings;
            this.payments = payments;
        }


        
        public void setUsers(String users) {
            this.users = users;
        }

        public void setFlights(String flights) {
            this.flights = flights;
        }

        public void setBookings(String bookings) {
            this.bookings = bookings;
        }

        public void setPayments(String payments) {
            this.payments = payments;
        }

        public String getUsers() {
            return users;
        }

        public String getFlights() {
            return flights;
        }

        public String getBookings() {
            return bookings;
        }

        public String getPayments() {
            return payments;
        }
        
        
}
