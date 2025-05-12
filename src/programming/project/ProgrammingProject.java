package programming.project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProgrammingProject {

    public static void main(String[] args) {
        // Administrator a1 = new Administrator("1", "ahmed", "123", "ahmed", "ahmed",
        // "contactInfo" , "1", "high");
        // Customer c1 = new Customer("23", "address", "preferences", "23", "baraa",
        // "123", "baraa", "baraa", "contactInfo");
        // Agent a2 = new Agent("22", "admin", 123.0, "22", "jana25",
        // "123", "jana25", "jana32@gmail.com", "additionalInfo");
        // Customer c1 = new Customer("4", "address", "preferences", "4", "hazem",
        // "123", "hazem", "hazem", "contactInfo");
        Administrator a1 = new Administrator();
        Customer c1 = new Customer();
        Agent g2 = new Agent();
        Flight f1 = new Flight();

        JFrame frame = new JFrame("Login");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // // Load and set background image
        // ImageIcon backgroundImage = new
        // ImageIcon(ProgrammingProject.class.getResource("/programming/project/assets/10601216_41822.jpg"));
        // JLabel backgroundLabel = new JLabel(backgroundImage);
        // backgroundLabel.setBounds(0, 0, 1, 1);
        ImageIcon originalIcon = new ImageIcon(
                ProgrammingProject.class.getResource("/programming/project/assets/10601216_41822.jpg"));
        Image originalImage = originalIcon.getImage();

        // Scale the image to fit the screen size
        Image scaledImage = originalImage.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);

        ImageIcon backgroundImage = new ImageIcon(scaledImage);
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 900, 900);

        backgroundLabel.setLayout(null); // for absolute positioning

        // Username label and field
        int centerX = screenWidth / 2;

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(centerX - 150, 200, 80, 25);
        backgroundLabel.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(centerX - 60, 200, 165, 25);
        backgroundLabel.add(userText);

        // Password label and field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(centerX - 150, 240, 80, 25);
        backgroundLabel.add(passLabel);

        JPasswordField passText = new JPasswordField();
        passText.setBounds(centerX - 60, 240, 165, 25);
        backgroundLabel.add(passText);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(centerX - 30, 280, 80, 25);
        backgroundLabel.add(loginButton);

        frame.setContentPane(backgroundLabel);

        // Login button logic
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passText.getPassword());

                if (a1.login(username, password)) {
                    showRoleScreen(a1.getRole(), username, a1);

                    frame.dispose();
                } else if (c1.login(username, password)) {
                    showRoleScreen(c1.getRole(), username, c1);

                    frame.dispose();
                } else if (g2.login(username, password)) {
                    showRoleScreen(g2.getRole(), username, g2);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed! Please try again.");
                }
            }

            private void showRoleScreen(String role, String username, User loggedInUser) {
                JFrame dashboard = new JFrame(role + " Dashboard");
                dashboard.setSize(400, 300);
                dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dashboard.setLayout(new FlowLayout());

                JLabel welcomeLabel = new JLabel("Welcome " + username + " (" + role + ")");
                dashboard.add(welcomeLabel);

                if (role.equalsIgnoreCase("Customer")) {
                    JButton showFlights = new JButton("Show Flights");
                    showFlights.addActionListener(ev -> {
                        JOptionPane.showMessageDialog(dashboard, "Showing flights...");
                        f1.createAndShowGUI();
                    });
                    dashboard.add(showFlights);

                    JButton update = new JButton("Update Profile");
                    update.addActionListener(e -> {
                        ((Customer) loggedInUser).updateProfile();
                    });
                    dashboard.add(update);
            }else if (role.equalsIgnoreCase("Agent")) {
                    JButton manageBookings = new JButton("Manage Bookings");
                    manageBookings.addActionListener(ev -> {
                        JOptionPane.showMessageDialog(dashboard, "Managing bookings...");
                    });
                    dashboard.add(manageBookings);

                    JButton addflight = new JButton("Add flight");
                    addflight.addActionListener(ev -> {
                        f1.addFlight();
                    });
                    dashboard.add(addflight);

                    JButton update = new JButton("Update Profile");
                    update.addActionListener(e -> {
                        ((Agent) loggedInUser).updateProfile();
                    });
                    dashboard.add(update);
                } else if (role.equalsIgnoreCase("Administrator")) {
                    JButton manageUsers = new JButton("Manage Users");
                    manageUsers.addActionListener(ev -> {
                        JOptionPane.showMessageDialog(dashboard, "Managing users...");
                    });
                    dashboard.add(manageUsers);

                    JButton update = new JButton("Update Profile");
                    update.addActionListener(e -> {
                        ((Administrator) loggedInUser).updateProfile();
                    });
                    dashboard.add(update);
                } else if (role.equalsIgnoreCase("Administrator")) {
                    JButton manageUsers = new JButton("Manage Users");
                    manageUsers.addActionListener(ev -> {
                        JOptionPane.showMessageDialog(dashboard, "Managing users...");
                    });
                    dashboard.add(manageUsers);

                    JButton update = new JButton("Update Profile");
                    update.addActionListener(e -> {
                        ((Administrator) loggedInUser).updateProfile();
                    });
                    dashboard.add(update);
                }

                JButton logoutBtn = new JButton("Logout");
                logoutBtn.addActionListener(ev -> {
                    loggedInUser.logout();
                    JOptionPane.showMessageDialog(dashboard, "You have been logged out.");
                    dashboard.dispose();
                    main(null); // restart the app
                });
                dashboard.add(logoutBtn);

                dashboard.setLocationRelativeTo(null);
                dashboard.setVisible(true);
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
