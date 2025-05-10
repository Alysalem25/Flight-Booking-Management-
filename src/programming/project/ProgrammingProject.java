/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programming.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Aly
 */
public class ProgrammingProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Administrator a1 = new Administrator("1", "admin", "123", "name", "ema1il",
        // "contactInfo" , "1", "high");
        // Customer c1 = new Customer("2", "address", "preferences", "2", "username",
        // "123", "name", "ema1il", "contactInfo");
        // Agent a2 = new Agent("1", "admin", 123.0, "name", "ema1il",
        // "contactInfo", "1", "high", "additionalInfo");
        // Customer c1 = new Customer("4", "address", "preferences", "4", "hazem",
        // "123", "hazem", "hazem", "contactInfo");
        Administrator a1 = new Administrator();
        Customer c1 = new Customer();
        Agent g2 = new Agent();
        Flight f1 = new Flight();
        // =================================================
        JFrame frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Username label and text field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(20, 20, 80, 25);
        frame.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        frame.add(userText);

        // Password label and field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(20, 60, 80, 25);
        frame.add(passLabel);

        JPasswordField passText = new JPasswordField();
        passText.setBounds(100, 60, 165, 25);
        frame.add(passText);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 100, 80, 25);
        frame.add(loginButton);

        // On button click
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passText.getPassword());

                // Call your login method
                if (a1.login(username, password)) {
                    String role = a1.getRole(); // Assuming you have a method to get the role
                    JOptionPane.showMessageDialog(frame, "Login successful! Role: " + role);
                    // Proceed to the next screen or functionality
                } else if (c1.login(username, password)) {
                    String role = c1.getRole(); // Assuming you have a method to get the role
                    JOptionPane.showMessageDialog(frame, "Login successful! Role: " + role);

                    JButton showFlights = new JButton("Show Flights");
                    showFlights.setBounds(120, 120, 120, 30); // Adjust size for better visibility
                    showFlights.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Show flights logic here
                            JOptionPane.showMessageDialog(frame, "Showing flights...");
                            f1.createAndShowGUI();
                        }
                    });

                    frame.add(showFlights);
                    frame.revalidate(); // Refresh layout
                    frame.repaint(); // Redraw frame
                } else if (g2.login(username, password)) {
                    String role = g2.getRole(); // Assuming you have a method to get the role
                    JOptionPane.showMessageDialog(frame, "Login successful! Role: " + role);
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed! Please try again.");
                }
            }
        });

        frame.setVisible(true);
        // =================================================
        // u1.login(username, password);
        // User user = new User("1", "alysalem@25t", "123", "name", "email",
        // "contactInfo");
        // user.login("alysalem@25t", "123");
        // JFrame frame = new JFrame("Form Example");
        // frame.setSize(400, 200);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLayout(new FlowLayout());

        // JLabel label = new JLabel("Enter name:");
        // JTextField textField = new JTextField(20);
        // JButton button = new JButton("Say Hello");

        // // Add action listener to button
        // button.addActionListener(e -> {
        // String name = textField.getText();
        // JOptionPane.showMessageDialog(frame, "Hello, " + name + "!");
        // });

        // // Add components to the frame
        // frame.add(label);
        // frame.add(textField);
        // frame.add(button);

        // frame.setVisible(true);

        // ---------------------------------------------------------------------

        // ---------------------------------------------------------------------
        // String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100/users.db";
        // try {
        // Class.forName("org.sqlite.JDBC");
        // Connection conn = DriverManager.getConnection(url);
        // System.out.println("✅ Connected to database.");

        // Statement stmt = conn.createStatement();

        // // Insert a user
        // stmt.executeUpdate("INSERT INTO Users (username, password, name, email,
        // contact_info, role) " +
        // "VALUES ('traveeffe2eewwer22', 'trip20323', 'Ahmed Ta3rek',
        // 'ahmvefvfefef8222d@example.com', '01234536789', 'customer')");

        // // Insert a customer linked to user
        // stmt.executeUpdate("INSERT INTO Customers (customer_id, address, preferences)
        // " +
        // "VALUES (20233, '1233 Street, Cairo', 'Window seat')");

        // // Insert a flight
        // stmt.executeUpdate(
        // "INSERT INTO Flights (flight_number, airline, origin, destination,
        // departure_time, arrival_time) " +
        // "VALUES ('EG21091', 'EgyptAir', 'Cairo', 'London', '2025-04-01 12:00',
        // '2025-04-01 16:00')");

        // // Insert flight seat class
        // stmt.executeUpdate("INSERT INTO FlightSeats (flight_id, seat_class,
        // total_seats, available_seats, price) " +
        // "VALUES (3, 'Economy', 1030, 929, 500.0)");

        // // Insert a booking
        // stmt.executeUpdate(
        // "INSERT INTO Bookings (booking_reference, customer_id, flight_id, seat_class,
        // status, payment_status) "
        // +
        // "VALUES ('BOOK1223', 20233, 3, 'Economy', 'Reserved', 'Pending')");

        // // Insert a passenger
        // stmt.executeUpdate(
        // "INSERT INTO Passengers (booking_id, name, passport_number, date_of_birth,
        // special_requests) " +
        // "VALUES (12, 'Ahmed Tarek', 'A12345678', '2000-01-01', 'Vegetarian meal')");

        // // Insert payment
        // stmt.executeUpdate("INSERT INTO Payments (booking_id, amount, method, status,
        // transaction_date) " +
        // "VALUES (12, 500.0, 'Credit card', 'Success', '2025-05-08 10:00')");

        // // Print data
        // ResultSet rs = stmt
        // .executeQuery("SELECT u.username, b.booking_reference, f.flight_number,
        // p.name, py.amount " +
        // "FROM Users u " +
        // "JOIN Customers c ON u.user_id = c.customer_id " +
        // "JOIN Bookings b ON c.customer_id = b.customer_id " +
        // "JOIN Flights f ON b.flight_id = f.flight_id " +
        // "JOIN Passengers p ON b.booking_id = p.booking_id " +
        // "JOIN Payments py ON b.booking_id = py.booking_id");

        // System.out.println("=== Booking Info ===");
        // while (rs.next()) {
        // System.out.printf("User: %s | Booking Ref: %s | Flight: %s | Passenger: %s |
        // Paid: $%.2f\n",
        // rs.getString("username"),
        // rs.getString("booking_reference"),
        // rs.getString("flight_number"),
        // rs.getString("name"),
        // rs.getDouble("amount"));
        // }

        // } catch (SQLException e) {
        // System.out.println("❌ SQL Error: " + e.getMessage());
        // } catch (ClassNotFoundException e) {
        // System.out.println("❌ JDBC class not found: " + e.getMessage());
        // }
    }
}
