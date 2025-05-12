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
public class Administrator extends User {
    // 4. Administrator (Extends User)
    // • Responsibility: Manages system settings and user access
    // • Key Attributes: adminId, securityLevel
    // • Key Methods: createUser(), modifySystemSettings(), viewSystemLogs(),
    // manageUserAccess()

    private String adminId;
    private String securityLevel;

    public Administrator() {
    }

    public Administrator(String userId, String username, String password, String name, String email, String contactInfo,
            String adminId, String securityLevel) {
        super(userId, username, password, name, email, contactInfo); // this calls the User constructor
        this.adminId = adminId;
        this.securityLevel = securityLevel;
        insertIntoDatabase(username, password, name, email, contactInfo);
    }

       private void insertIntoDatabase(String username, String password, String name, String email, String contactInfo) {
        String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100\\users.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            Class.forName("org.sqlite.JDBC");

            // Insert into Users table
            String userSQL = "INSERT INTO Users (username, password, name, email, contact_info, role) VALUES (?, ?, ?, ?, ?, 'admin')";
            PreparedStatement userStmt = conn.prepareStatement(userSQL, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, username);
            userStmt.setString(2, password);
            userStmt.setString(3, name);
            userStmt.setString(4, email);
            userStmt.setString(5, contactInfo);
            userStmt.executeUpdate();

            ResultSet generatedKeys = userStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);

                // Insert into Administrators table
                String adminSQL = "INSERT INTO Administrators (user_id, security_level) VALUES (?, ?)";
                PreparedStatement adminStmt = conn.prepareStatement(adminSQL);
                adminStmt.setInt(1, userId);
                adminStmt.setString(2, this.securityLevel);
                adminStmt.executeUpdate();

                System.out.println("✅ Administrator inserted successfully with user ID: " + userId);
            } else {
                throw new SQLException("User ID was not returned.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error inserting administrator: " + e.getMessage());
        }
    }
    @Override
   public boolean login(String username, String password) {
    String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100\\users.db";
    String sql = "SELECT u.user_id, u.username, u.password, u.name, u.email, u.contact_info " +
                 "FROM Users u " +
                 "JOIN Administrators a ON u.user_id = a.user_id " +
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
                System.out.println("✅ Login successful for user: " + rs.getString("name"));
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
        // Logic for updating administrator profile
        System.out.println("Updating administrator profile...");
    }
    public String getRole() {
        return "Administrator";
    }
}
