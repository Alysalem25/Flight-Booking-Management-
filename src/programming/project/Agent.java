package programming.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Agent extends User {
    private String agentId;
    private String department;
    private double commission;

    private String currentUsername = null; // For session tracking

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
            // PreparedStatement userStmt = conn.prepareStatement(userSQL,
            // PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement userStmt = conn.prepareStatement(userSQL, Statement.RETURN_GENERATED_KEYS);

            userStmt.setString(1, username);
            userStmt.setString(2, password);
            userStmt.setString(3, name);
            userStmt.setString(4, email);
            userStmt.setString(5, contactInfo);
            userStmt.executeUpdate();

            ResultSet generatedKeys = userStmt.getGeneratedKeys();
            int userId = -1;

            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("User ID not returned.");
            }
            // Insert into Agents
            PreparedStatement agentStmt = conn.prepareStatement(agentSQL);
            agentStmt.setInt(1, userId); // agent_id = user_id
            agentStmt.setString(2, this.department);
            agentStmt.setDouble(3, this.commission);
            agentStmt.executeUpdate();

            System.out.println("✅ Agent inserted with user ID: " + userId);
            conn.close();

        } catch (Exception e) {
            System.out.println("❌ Error inserting agent: " + e.getMessage());
        }
    }

    @Override
    public boolean login(String username, String password) {
        String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100/users.db";
      String sql = "SELECT u.user_id, u.username, u.password, u.name, u.email, u.contact_info, " +
             "a.department, a.commission " +
             "FROM Users u JOIN Agents a ON u.user_id = a.user_id " +  
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
                setDepartment(rs.getString("department"));
                setCommission(rs.getDouble("commission"));

                System.out.println("✅ Login successful for user: " + getName());
                return true;
            } else {
                System.out.println("❌ Invalid password.");
            }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error connecting to database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found: " + e.getMessage());
        }
        return false;

        // try {
        // Class.forName("org.sqlite.JDBC");
        // try (Connection conn = DriverManager.getConnection(url);
        // PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // pstmt.setString(1, username);
        // ResultSet rs = pstmt.executeQuery();

        // while (rs.next()) {
        // String usernameFromDB = rs.getString("username");
        // String passwordFromDB = rs.getString("password");

        // if (username.equals(usernameFromDB) && password.equals(passwordFromDB)) {
        // this.currentUsername = usernameFromDB; // set session info
        // System.out.println("✅ Login successful for: " + usernameFromDB);
        // return true;
        // }
        // }
        // }
        // } catch (Exception e) {
        // System.out.println("❌ Login error: " + e.getMessage());
        // }
        // return false;
    }

    public void logout() {
        System.out.println("🔒 User " + currentUsername + " logged out.");
        this.currentUsername = null;
    }

    @Override
    public void updateProfile() {
        String url = "jdbc:sqlite:D:\\Sqlite\\sqlite-tools-win-x64-3490100/users.db";
        String sql = "UPDATE Users SET username = ?, password = ?, name = ?, email = ?, contact_info = ? WHERE username = ?";

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(url);
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, this.getUsername());
                pstmt.setString(2, this.getPassword());
                pstmt.setString(3, this.getName());
                pstmt.setString(4, this.getEmail());
                pstmt.setString(5, this.getContactInfo());
                pstmt.setString(6, this.currentUsername);
                pstmt.executeUpdate();

                System.out.println("✅ Profile updated for: " + this.currentUsername);
            }
        } catch (Exception e) {
            System.out.println("❌ Error updating profile: " + e.getMessage());
        }
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
