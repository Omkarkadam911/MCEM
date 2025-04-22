import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AttendanceViewer extends JFrame {
    private static final String URL      = "jdbc:mysql://localhost:3306/employeemanagement";
    private static final String USER     = "root";
    private static final String PASSWORD = "nigger"; 
    public AttendanceViewer() {
        setTitle("All Attendance Records");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // 1) build table model
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"AttendanceID","EmployeeID","Date","CheckInTime","CheckOutTime","TotalHoursWorked","Status"},
            0
        );
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM attendance")) {

                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getInt("AttendanceID"),
                        rs.getInt("EmployeeID"),
                        rs.getDate("Date"),
                        rs.getTime("CheckInTime"),
                        rs.getTime("CheckOutTime"),
                        rs.getBigDecimal("TotalHoursWorked"),
                        rs.getString("Status")
                    };
                    model.addRow(row);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Failed to load attendance:\n" + ex.getMessage(),
                "DB Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        setVisible(true);
    }
}
