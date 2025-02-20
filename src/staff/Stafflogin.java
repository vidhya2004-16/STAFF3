package staff;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import database.DBConnect;
import java.sql.*;

public class Stafflogin {
    public Stafflogin() {
        JFrame frame = new JFrame("Staff Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel userLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        frame.add(userLabel);
        frame.add(usernameField);
        frame.add(passLabel);
        frame.add(passwordField);
        frame.add(new JLabel()); // Empty space
        frame.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (validateLogin(username, password)) {
                JOptionPane.showMessageDialog(null, "Login Successful");
                // After successful login, open the LeaveRequestForm
                new LeaveRequestForm(username, password);; // Pass the username to the LeaveRequestForm
                frame.dispose(); // Close the login window
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM staff WHERE username=? AND password=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Return true if a record is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        new Stafflogin();
    }
}
