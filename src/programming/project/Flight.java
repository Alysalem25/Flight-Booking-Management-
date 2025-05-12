package programming.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

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
    // • Key Methods: checkAvailability() 1, updateSchedule(), calculatePrice() 1,
    // reserveSeat()

    //UPDATE Users
// SET email = 'newemail@example.com',
//     contact_info = '0111222333'
// WHERE username = 'john_doe';

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

    public Flight(String flightNumber, String airline, String origin, String destination, String departureTime,
            String arrivalTime, int availableSeats, double prices) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
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

        // Set the custom cell renderer for the table
        table.setDefaultRenderer(Object.class, new FlightRowRenderer());

        // Load data from DB
        loadFlightsIntoTable(tableModel);

        frame.setVisible(true);
    }

    public void loadFlightsIntoTable(DefaultTableModel tableModel) {
        // Fix this line in BOTH loadFlightsIntoTable() and addFlight()
        String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100\\flight";
        String selectSQL = "SELECT flight_number, airline, origin, destination, departure_time, arrival_time, available_seats, prices FROM Flights";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(selectSQL);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("flight_number"),
                        rs.getString("airline"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("departure_time"),
                        rs.getString("arrival_time"),
                        rs.getInt("available_seats"),
                        rs.getDouble("prices")
                };
                tableModel.addRow(rowData);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "⚠️ Error loading flight data: " + e.getMessage());
        }

        // ==================================================
        this.prices = prices;
    }

    public void addFlight() {
        JFrame frame = new JFrame("Add Flight");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Don't close entire app
        frame.setLayout(null);

        String[] labels = {
                "Flight Number", "Airline", "Origin", "Destination",
                "Departure Time", "Arrival Time", "Available Seats", "Distance"
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
                    double distance = Double.parseDouble(fields[7].getText());
                    double prices = calculatePrice(distance); 

                    // Connect to database
                    // Fix this line in BOTH loadFlightsIntoTable() and addFlight()
                    String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100\\flight";
                    Class.forName("org.sqlite.JDBC");
                    try (Connection conn = DriverManager.getConnection(url);
                            PreparedStatement pstmt = conn.prepareStatement(
                                    "INSERT INTO Flights (flight_number, airline, origin, destination, departure_time, arrival_time, available_seats, distance, prices) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

                        pstmt.setString(1, flightNumber);
                        pstmt.setString(2, airline);
                        pstmt.setString(3, origin);
                        pstmt.setString(4, destination);
                        pstmt.setString(5, departureTime);
                        pstmt.setString(6, arrivalTime);
                        pstmt.setInt(7, availableSeats);
                        pstmt.setDouble(8, distance);
                        pstmt.setDouble(9, prices);

                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "✅ Flight added successfully!");
                        frame.dispose(); // Optional: close form after success
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "⚠️ Invalid input for seats or distance.");
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "⚠️ SQLite JDBC driver not found.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "⚠️ Database error: " + ex.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }

    // Custom TableCellRenderer to highlight rows with 0 available seats
    static class FlightRowRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Get the number of available seats for the current row (it's in the 6th
            // column, index 6)
            Object availableSeatsObj = table.getValueAt(row, 6);
            if (availableSeatsObj instanceof Integer) {
                int availableSeats = (Integer) availableSeatsObj;
                if (availableSeats == 0) {
                    c.setBackground(Color.RED);
                    c.setForeground(Color.WHITE); // Optional: Set text color to white for better contrast
                } else {
                    // Reset background and foreground for other rows
                    c.setBackground(table.getBackground());
                    c.setForeground(table.getForeground());
                }
            } else {
                // Handle cases where the "Available Seats" value might not be an Integer
                c.setBackground(table.getBackground());
                c.setForeground(table.getForeground());
            }

            return c;
        }
    }

    public double calculatePrice(double distance) {
        return distance * 2.1;
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