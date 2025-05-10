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

/**
 *
 * @author Aly
 */
public class Agent extends User {
    // 3. Agent (Extends User)
    // • Responsibility: Manages bookings and assists customers
    // • Key Attributes: agentId, department, commission
    // • Key Methods: manageFlights(), createBookingForCustomer(), modifyBooking(),
    // generateReports()

    private String agentId;
    private String department;
    private double commission;

    public Agent() {
    }

    public Agent(String agentId, String department, double commission, String userId, String username, String password,
            String name, String email, String contactInfo) {
        super(userId, username, password, name, email, contactInfo);
        this.agentId = agentId;
        this.department = department;
        this.commission = commission;
        insertInDatabase(username, password, name, email, contactInfo);
    }

    private void insertInDatabase(String username, String password, String name, String email, String contactInfo) {
        String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100/users.db";
        String userSQL = "INSERT INTO Users (username, password, name, email, contact_info, role) VALUES (?, ?, ?, ?, ?, 'agent')";
        String agentSQL = "INSERT INTO Agents (user_id, department, commission) VALUES (?, ?, ?)";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            System.out.println("✅ Connected to database.");
            // Insert into Users
            PreparedStatement userStmt = conn.prepareStatement(userSQL);
            userStmt.setString(1, username);
            userStmt.setString(2, password);
            userStmt.setString(3, name);
            userStmt.setString(4, email);
            userStmt.setString(5, contactInfo);
            userStmt.executeUpdate();

            // Get generated user_id
            ResultSet generatedKeys = userStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);

                // Insert into Agents table
                PreparedStatement agentStmt = conn.prepareStatement(agentSQL);
                agentStmt.setInt(1, userId);
                agentStmt.setString(2, this.department);
                agentStmt.setDouble(3, this.commission);
                agentStmt.executeUpdate();

                System.out.println("✅ Agent inserted successfully with user ID: " + userId);
            } else {
                throw new SQLException("User ID was not returned.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error inserting agent: " + e.getMessage());
        }
    }

    @Override
    public boolean login(String username, String password) {
        String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100/users.db";
        String sql = "SELECT u.user_id, u.username, u.name, u.email, u.contact_info, c.address, c.preferences " +
                "FROM Users u " +
                "JOIN Agents c ON u.user_id = c.agent_id " +
                "WHERE u.username = ?";
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                System.out.println("Error loading database driver: " + e.getMessage());
                return false;
            }
            Connection conn = DriverManager.getConnection(url);
            System.out.println("✅ Connected to database.");
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nameFromData = rs.getString("name");
                String passwordFromData = rs.getString("password");
                System.out.println("Name: " + nameFromData + ", Password: " +
                        passwordFromData);
                if (username.equals(nameFromData) && password.equals(passwordFromData)) {
                    System.out.println("Login successful for user: " + nameFromData);
                    return true;

                } else {
                    System.out.println("Login failed for user: " + nameFromData);
                }

            }

        } catch (SQLException e) {
            System.out.println("Error selecting data: " + e.getMessage());
        }
        return false;
    }

    public String getAgentId() {
        return agentId;
    }

    public String getDepartment() {
        return department;
    }

    public double getCommission() {
        return commission;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getRole() {
        return "Agent";
    }

}
