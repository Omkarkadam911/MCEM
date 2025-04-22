import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MarkAttendance extends JFrame {
    public MarkAttendance() {
        setTitle("Mark Attendance");
        setSize(450, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label1 = new JLabel("Employee ID:");
        label1.setBounds(50, 50, 100, 30);
        add(label1);

        JTextField empIdField = new JTextField();
        empIdField.setBounds(180, 50, 180, 30);
        add(empIdField);

        JLabel label2 = new JLabel("Date (YYYY-MM-DD):");
        label2.setBounds(50, 90, 150, 30);
        add(label2);

        JTextField dateField = new JTextField(LocalDate.now().toString());
        dateField.setBounds(210, 90, 150, 30);
        add(dateField);

        JLabel label3 = new JLabel("Check-In Time (HH:MM:SS):");
        label3.setBounds(50, 130, 200, 30);
        add(label3);

        JTextField checkInField = new JTextField("09:00:00");
        checkInField.setBounds(230, 130, 100, 30);
        add(checkInField);

        JLabel label4 = new JLabel("Check-Out Time (HH:MM:SS):");
        label4.setBounds(50, 170, 200, 30);
        add(label4);

        JTextField checkOutField = new JTextField("17:00:00");
        checkOutField.setBounds(230, 170, 100, 30);
        add(checkOutField);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(160, 230, 100, 30);
        add(submitBtn);

        submitBtn.addActionListener(e -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employeemanagement", "root", "nigger");

                String query = "INSERT INTO attendance (EmployeeID, Date, CheckInTime, CheckOutTime) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(empIdField.getText()));
                ps.setString(2, dateField.getText());
                ps.setString(3, checkInField.getText());
                ps.setString(4, checkOutField.getText());

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Attendance marked successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to mark attendance.");
                }

                conn.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }
}
