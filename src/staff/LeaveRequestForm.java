// leaverequestform.java
package staff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class LeaveRequestForm {
    public LeaveRequestForm(String username, String password) {
        JFrame frame = new JFrame("Leave Request Form");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(7, 2, 10, 10));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(username);
        userField.setEditable(false);

        JLabel idLabel = new JLabel("Staff ID:");
        JTextField idField = new JTextField(password);
        idField.setEditable(false);

        JLabel dateLabel = new JLabel("Leave Date:");
        JTextField dateField = new JTextField(LocalDate.now().toString());

        JCheckBox reason1 = new JCheckBox("Personal");
        JCheckBox reason2 = new JCheckBox("Medical");
        JCheckBox reason3 = new JCheckBox("Vacation");

        JLabel otherReasonLabel = new JLabel("Other Reason:");
        JTextField otherReasonField = new JTextField();

        JButton submitButton = new JButton("Submit");

        frame.add(userLabel);
        frame.add(userField);
        frame.add(idLabel);
        frame.add(idField);
        frame.add(dateLabel);
        frame.add(dateField);
        frame.add(reason1);
        frame.add(reason2);
        frame.add(reason3);
        frame.add(otherReasonLabel);
        frame.add(otherReasonField);
        frame.add(new JLabel()); // Empty space
        frame.add(submitButton);

        submitButton.addActionListener(e -> {
            String leaveDate = dateField.getText().trim();
            String reason = "";
            if (reason1.isSelected()) reason += "Personal ";
            if (reason2.isSelected()) reason += "Medical ";
            if (reason3.isSelected()) reason += "Vacation ";
            if (!otherReasonField.getText().trim().isEmpty()) reason += otherReasonField.getText().trim();

            if (!reason.isEmpty()) {
                submitLeaveRequest(username, leaveDate, reason);
                JOptionPane.showMessageDialog(null, "Leave Request Submitted Successfully");
                frame.dispose();
                new Stafflogin(); // Redirect to login after submitting leave request
            } else {
                JOptionPane.showMessageDialog(null, "Please select or enter a reason", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private void submitLeaveRequest(String username, String leaveDate, String reason) {
        // Insert a new leave request into the leave_requests table
        String insertLeaveRequestQuery = "INSERT INTO leave_requests (username, leave_date, reason, is_viewed) VALUES (?, ?, ?, false)";
        // Update the leave count in the staff table (increment by 1)
        String updateLeaveCountQuery = "UPDATE staff SET leave_count = leave_count + 1 WHERE username = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement pstmtInsert = con.prepareStatement(insertLeaveRequestQuery);
             PreparedStatement pstmtUpdate = con.prepareStatement(updateLeaveCountQuery)) {

            // Insert the leave request into the leave_requests table
            pstmtInsert.setString(1, username);
            pstmtInsert.setString(2, leaveDate);
            pstmtInsert.setString(3, reason);
            pstmtInsert.executeUpdate(); // This inserts a new row into leave_requests

            // Increment the leave count by 1 in the staff table
            pstmtUpdate.setString(1, username);
            pstmtUpdate.executeUpdate(); // This increments the leave count by 1 for the specific user

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
