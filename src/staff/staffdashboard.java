package staff;

import javax.swing.*;
import java.awt.event.*;

public class staffdashboard {
    String staffid;

    staffdashboard(String name, String id) {
        this.staffid = id;

        JFrame frame2 = new JFrame();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLayout(null);
        frame2.setSize(800, 800);

        // Show new requests button
        JButton bn = new JButton("Show new requests");
        bn.setBounds(400, 100, 180, 30);
        frame2.add(bn);
        bn.addActionListener(e -> {
            // Open new request viewer (not defined here)
            frame2.setVisible(false);
        });

        // Generate report button
        JButton bn3 = new JButton("Generate Report");
        bn3.setBounds(400, 200, 180, 30);
        frame2.add(bn3);
        bn3.addActionListener(e -> {
            // Open report generation (not defined here)
            frame2.dispose();
        });

        // Show all requests button
        JButton bn2 = new JButton("Show all requests");
        bn2.setBounds(400, 300, 180, 30);
        frame2.add(bn2);
        bn2.addActionListener(e -> {
            // Open all requests viewer (not defined here)
            frame2.setVisible(false);
        });

        // Staff Leave Request button
        JButton bn4 = new JButton("Staff Leave Request");
        bn4.setBounds(400, 400, 180, 30);
        frame2.add(bn4);
        bn4.addActionListener(e -> {
            // Open the Leave Request Form when clicked
            new Stafflogin(); // Pass staff name and id to the form
            frame2.dispose(); // Close the staff dashboard
        });

        // Log out button
        JButton back = new JButton("Log out");
        back.setBounds(700, 30, 80, 30);
        frame2.add(back);
        back.addActionListener(e -> {
            new dashboard(); // Open the main dashboard
            frame2.dispose(); // Close the staff dashboard
        });

        frame2.setVisible(true);
    }
}
