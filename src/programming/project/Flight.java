/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programming.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author Aly
 */
public class Flight {
    // 5. Flight
    // • Responsibility: Stores flight information and manages seats
    // • Key Attributes: flightNumber, airline, origin, destination, departureTime,
    // arrivalTime, availableSeats,
    // prices
    // • Key Methods: checkAvailability(), updateSchedule(), calculatePrice(),
    // reserveSeat()
    private String flightNumber;
    private String airline;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;
    private double prices;

    public Flight() {
    }

    public void show() {
        SwingUtilities.invokeLater(() -> new Flight().createAndShowGUI());
        // SwingUtilities.invokeLater(() -> new Flight().addflight());
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Flight List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        // Table setup
        String[] columnNames = {
                "Flight Number", "Airline", "Origin", "Destination",
                "Departure Time", "Arrival Time", "Available Seats", "Price"
        };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        // Load data from DB
        loadFlightsIntoTable(tableModel);

        frame.setVisible(true);
    }

    public void loadFlightsIntoTable(DefaultTableModel tableModel) {
        String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100\\users.db";
        String selectSQL = "SELECT flight_number, airline, origin, destination, departure_time, arrival_time, available_seats, prices FROM Flights";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Object[] row = {
                        rs.getString("flight_number"),
                        rs.getString("airline"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("departure_time"),
                        rs.getString("arrival_time"),
                        rs.getInt("available_seats"),
                        rs.getDouble("prices")
                };
                tableModel.addRow(row);
            }

            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "⚠️ Error loading flight data: " + e.getMessage());
        }
    }

    // ==================================================
    public Flight(String flightNumber, String airline, String origin, String destination, String departureTime,
            String arrivalTime, int availableSeats, double prices) {
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

   public void addFlight() {
       JFrame frame = new JFrame("Add Flight");
       frame.setSize(400, 500);
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Don't close entire app
       frame.setLayout(null);

       String[] labels = {
               "Flight Number", "Airline", "Origin", "Destination",
               "Departure Time", "Arrival Time", "Available Seats", "Price"
       };

    JTextField[] fields = new JTextField[labels.length];

    for (int i = 0; i < labels.length; i++) {
        JLabel label = new JLabel(labels[i] + ":");
        label.setBounds(20, 30 + i * 40, 120, 25);
        frame.add(label);

        fields[i] = new JTextField();
        fields[i].setBounds(150, 30 + i * 40, 200, 25);
        frame.add(fields[i]);
    }

    JButton addButton = new JButton("Add Flight");
    addButton.setBounds(130, 370, 120, 30);
    frame.add(addButton);

    addButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                // Get input values
                String flightNumber = fields[0].getText();
                String airline = fields[1].getText();
                String origin = fields[2].getText();
                String destination = fields[3].getText();
                String departureTime = fields[4].getText();
                String arrivalTime = fields[5].getText();
                int availableSeats = Integer.parseInt(fields[6].getText());
                double prices = Double.parseDouble(fields[7].getText());

                // Connect to database
                String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100\\users.db";
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection(url);

                // Use PreparedStatement safely
                String insertSQL = "INSERT INTO Flights (flight_number, airline, origin, destination, departure_time, arrival_time, available_seats, prices) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertSQL);
                pstmt.setString(1, flightNumber);
                pstmt.setString(2, airline);
                pstmt.setString(3, origin);
                pstmt.setString(4, destination);
                pstmt.setString(5, departureTime);
                pstmt.setString(6, arrivalTime);
                pstmt.setInt(7, availableSeats);
                pstmt.setDouble(8, prices);

                pstmt.executeUpdate();
                conn.close();

                JOptionPane.showMessageDialog(null, "✅ Flight added successfully!");
                frame.dispose(); // Optional: close form after success
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "⚠️ Error: " + ex.getMessage());
            }
        }
    });

    frame.setVisible(true);
}
}
