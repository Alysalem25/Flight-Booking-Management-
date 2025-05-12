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

/**
 *
 * @author Aly
 */
public class Customer extends User {
    // 2. Customer (Extends User)
    // • Responsibility: Manages customer bookings and profiles
    // • Key Attributes: customerId, address, bookingHistory, preferences
    // • Key Methods: searchFlights(), createBooking(), viewBookings(),
    // cancelBooking()
    private String customerId;
    private String address;
    // private String bookingHistory;
    private String preferences;

    public Customer() {
    }

    public Customer(String customerId, String address, String preferences, String userId, String username,
            String password, String name, String email, String contactInfo) {
        super(userId, username, password, name, email, contactInfo);
        this.customerId = customerId;
        this.address = address;
        this.preferences = preferences;
        insertInDatabase(username, password, name, email, contactInfo);
    }

    private void insertInDatabase(String username, String password, String name, String email, String contactInfo) {
        String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100/users.db";
        String userSQL = "INSERT INTO Users (username, password, name, email, contact_info, role) VALUES (?, ?, ?, ?, ?, 'customer')";
        String custSQL = "INSERT INTO Customers (user_id, address, preferences) VALUES (?, ?, ?)";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            System.out.println("✅ Connected to database.");

            // Insert into Users
            PreparedStatement userStmt = conn.prepareStatement(userSQL, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, username);
            userStmt.setString(2, password);
            userStmt.setString(3, name);
            userStmt.setString(4, email);
            userStmt.setString(5, contactInfo);
            userStmt.executeUpdate();

            // Get generated user_id
            ResultSet rs = userStmt.getGeneratedKeys();
            int userId = -1;
            if (rs.next()) {
                userId = rs.getInt(1);
            } else {
                throw new SQLException("User ID not generated.");
            }
            this.setUsername(username);
            this.setPassword(password);
            this.setName(name);
            this.setEmail(email);
            this.setContactInfo(contactInfo);
            this.setAddress(address);
            this.setPreferences(preferences);

            // Insert into Customers
            PreparedStatement custStmt = conn.prepareStatement(custSQL);
            custStmt.setInt(1, userId);
            custStmt.setString(2, address);
            custStmt.setString(3, preferences);
            custStmt.executeUpdate();

            System.out.println("✅ Customer inserted successfully.");
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("❌ Error inserting customer: " + e.getMessage());
        }
    }

    @Override
    public boolean login(String username, String password) {
        String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100\\users.db";
        String sql = "SELECT u.user_id, u.username, u.password, u.name, u.email, u.contact_info, " +
                "c.address, c.preferences " +
                "FROM Users u " +
                "JOIN Customers c ON u.user_id = c.user_id " +
                "WHERE u.username = ?";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            System.out.println("✅ Connected to database.");

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String passwordFromDB = rs.getString("password");
                if (password.equals(passwordFromDB)) {
                    // Set fields from DB
                    setUserId(String.valueOf(rs.getInt("user_id")));
                    setUsername(rs.getString("username"));
                    setPassword(rs.getString("password"));
                    setName(rs.getString("name"));
                    setEmail(rs.getString("email"));
                    setContactInfo(rs.getString("contact_info"));
                    setAddress(rs.getString("address"));
                    setPreferences(rs.getString("preferences"));

                    System.out.println("✅ Login successful for user: " + getName());
                    return true;
                } else {
                    System.out.println("❌ Invalid password.");
                }
            } else {
                System.out.println("❌ User not found.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ SQL Error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public void updateProfile() {
        String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100\\users.db";
        String userSQL = "UPDATE Users SET username = ?, password = ?, name = ?, email = ?, contact_info = ? WHERE user_id = ?";
        String custSQL = "UPDATE Customers SET address = ?, preferences = ? WHERE user_id = ?";
        Connection conn = null; // Initialize connection outside the try block

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            // Set busy timeout immediately after establishing connection
            try (java.sql.Statement statement = conn.createStatement()) {
                statement.execute("PRAGMA busy_timeout = 3000;");
            }

            // Start a transaction to ensure atomicity of both updates
            conn.setAutoCommit(false);

            try (PreparedStatement userStmt = conn.prepareStatement(userSQL);
                    PreparedStatement custStmt = conn.prepareStatement(custSQL)) {

                // Update Users
                userStmt.setString(1, getUsername());
                userStmt.setString(2, getPassword());
                userStmt.setString(3, getName());
                userStmt.setString(4, getEmail());
                userStmt.setString(5, getContactInfo());
                userStmt.setInt(6, Integer.parseInt(getUserId()));
                userStmt.executeUpdate();

                // Update Customers
                custStmt.setString(1, getAddress());
                custStmt.setString(2, getPreferences());
                custStmt.setInt(3, Integer.parseInt(getUserId()));
                custStmt.executeUpdate();

                // Commit the transaction if both updates were successful
                conn.commit();
                System.out.println("✅ Profile updated successfully.");

            } catch (SQLException e) {
                // Rollback the transaction if any error occurred during the updates
                if (conn != null) {
                    try {
                        conn.rollback();
                        System.out.println("❌ Transaction rolled back due to error: " + e.getMessage());
                    } catch (SQLException rollbackException) {
                        System.out.println("❌ Error rolling back transaction: " + rollbackException.getMessage());
                    }
                }
                throw e; // Re-throw the original exception to be caught by the outer catch block
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("❌ Error updating profile: " + e.getMessage());
        } finally {
            // Ensure the connection is closed in the finally block
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Reset auto-commit
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("❌ Error closing connection: " + e.getMessage());
                }
            }
        }
    }

   
//    public void createBooking() {
//         // Logic for creating a booking
//     }

    public String getCustomerId() {
        return customerId;
    }

    public void setUserId(String userId) {
        super.setUserId(userId);
    }

    public void setUsername(String username) {
        super.setUsername(username);
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

    public String getRole() {
        return "Customer";
    }
}
