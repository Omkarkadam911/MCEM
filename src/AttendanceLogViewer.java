import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class AttendanceLogViewer extends JFrame {
    private static final String URL      = "jdbc:mysql://localhost:3306/employeemanagement";
    private static final String USER     = "root";
    private static final String PASSWORD = "nigger"; 

    private final JTextField empIdField;
    private final DefaultTableModel model;

    public AttendanceLogViewer() {
        super("View Attendance Log");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        JPanel top = new JPanel();
        top.add(new JLabel("Employee ID:"));
        empIdField = new JTextField(8);
        top.add(empIdField);
        JButton showBtn = new JButton("Show");
        top.add(showBtn);
        add(top, BorderLayout.NORTH);

        String[] cols = {
            "AttendanceID", "EmployeeID", "Date",
            "CheckInTime", "CheckOutTime", "TotalHoursWorked", "Status"
        };
        model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        showBtn.addActionListener(e -> {
            model.setRowCount(0);            
            String text = empIdField.getText().trim();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an Employee ID.");
                return;
            }
            try {
                int empId = Integer.parseInt(text);
                loadData(empId);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format.");
            }
        });

        setVisible(true);
    }

    private void loadData(int employeeId) {
        String sql = "SELECT AttendanceID, EmployeeID, Date, "
                   + " CheckInTime, CheckOutTime, TotalHoursWorked, Status"
                   + " FROM attendance WHERE EmployeeID = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setInt(1, employeeId);
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        model.addRow(new Object[]{
                            rs.getInt("AttendanceID"),
                            rs.getInt("EmployeeID"),
                            rs.getDate("Date"),
                            rs.getTime("CheckInTime"),
                            rs.getTime("CheckOutTime"),
                            rs.getBigDecimal("TotalHoursWorked"),
                            rs.getString("Status")
                        });
                    }
                }
            }
        } catch (ClassNotFoundException cnf) {
            JOptionPane.showMessageDialog(this,
                "Driver not found:\n" + cnf.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(this,
                "DB Error:\n" + sqle.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AttendanceLogViewer::new);
    }
}
