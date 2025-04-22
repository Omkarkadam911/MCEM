import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ViewEmployees extends JFrame {
    private static final String URL      = "jdbc:mysql://localhost:3306/employeemanagement";
    private static final String USER     = "root";
    private static final String PASSWORD = "nigger";

    public ViewEmployees() {
        super("All Employees");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] cols = {
            "EmployeeID", "FirstName", "LastName", "DOB", "Gender",
            "ContactNumber", "Email", "Address", "HireDate",
            "JobID", "DepartmentID", "ManagerID", "Salary"
        };
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadEmployees(model);
        setVisible(true);
    }

    private void loadEmployees(DefaultTableModel model) {
        String sql = "SELECT EmployeeID, FirstName, LastName, DOB, Gender, "
                   + " ContactNumber, Email, Address, HireDate, JobID, DepartmentID, ManagerID, Salary "
                   + "FROM employee";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("EmployeeID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getDate("DOB"),
                        rs.getString("Gender"),
                        rs.getString("ContactNumber"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getDate("HireDate"),
                        rs.getInt("JobID"),
                        rs.getInt("DepartmentID"),
                        rs.getInt("ManagerID"),
                        rs.getBigDecimal("Salary")
                    });
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this, "Error loading employees:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewEmployees::new);
    }
}
