import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RequestLeave extends JFrame {

    public RequestLeave() {
        setTitle("Submit Leave Request");
        setSize(450, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel empIdLabel = new JLabel("Employee ID:");
        empIdLabel.setBounds(50, 50, 100, 30);
        add(empIdLabel);

        JTextField empIdField = new JTextField();
        empIdField.setBounds(180, 50, 180, 30);
        add(empIdField);

        JLabel typeLabel = new JLabel("Leave Type:");
        typeLabel.setBounds(50, 90, 100, 30);
        add(typeLabel);

        JTextField typeField = new JTextField();
        typeField.setBounds(180, 90, 180, 30);
        add(typeField);

        JLabel startLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startLabel.setBounds(50, 130, 180, 30);
        add(startLabel);

        JTextField startField = new JTextField();
        startField.setBounds(230, 130, 130, 30);
        add(startField);

        JLabel endLabel = new JLabel("End Date (YYYY-MM-DD):");
        endLabel.setBounds(50, 170, 180, 30);
        add(endLabel);

        JTextField endField = new JTextField();
        endField.setBounds(230, 170, 130, 30);
        add(endField);

        JLabel reasonLabel = new JLabel("Reason:");
        reasonLabel.setBounds(50, 210, 100, 30);
        add(reasonLabel);

        JTextField reasonField = new JTextField();
        reasonField.setBounds(180, 210, 180, 30);
        add(reasonField);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(160, 270, 100, 30);
        add(submitBtn);

        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/employeemanagement",
                        "root",
                        "nigger"
                    );

                    String sql = "INSERT INTO `leaverequest` (`EmployeeID`, `Type`, `StartDate`, `EndDate`, `Status`, `Reason`) " +
                    "VALUES (?, ?, ?, ?, 'Pending', ?)";


                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(empIdField.getText()));
                    ps.setString(2, typeField.getText());
                    ps.setString(3, startField.getText());
                    ps.setString(4, endField.getText());
                    ps.setString(5, reasonField.getText());

                    int result = ps.executeUpdate();
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Leave request submitted successfully!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to submit leave request.");
                    }

                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }
}
