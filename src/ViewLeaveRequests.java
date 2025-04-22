import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ViewLeaveRequests extends JFrame {
    private static final String URL      = "jdbc:mysql://localhost:3306/employeemanagement";
    private static final String USER     = "root";
    private static final String PASSWORD = "nigger"; 

    private final DefaultTableModel model;
    private final JTable table;

    public ViewLeaveRequests() {
        super("Pending Leave Requests");
        setSize(800, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        String[] cols = {"LeaveID","EmployeeID","Type","StartDate","EndDate","Status","Reason"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton approveBtn = new JButton("Approve");
        JButton declineBtn = new JButton("Decline");
        buttons.add(approveBtn);
        buttons.add(declineBtn);
        add(buttons, BorderLayout.SOUTH);

        loadRequests();

        approveBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a request first.");
                return;
            }
            int leaveId = (int) model.getValueAt(row, 0);
            updateStatus(leaveId, "Approved");
        });

        declineBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a request first.");
                return;
            }
            int leaveId = (int) model.getValueAt(row, 0);
            updateStatus(leaveId, "Declined");
        });

        setVisible(true);
    }

    private void loadRequests() {
        model.setRowCount(0);
        String sql = "SELECT LeaveID, EmployeeID, Type, StartDate, EndDate, Status, Reason"
                   + " FROM leaverequest WHERE Status = 'Pending'";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("LeaveID"),
                        rs.getInt("EmployeeID"),
                        rs.getString("Type"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getString("Status"),
                        rs.getString("Reason")
                    });
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error loading leave requests:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStatus(int leaveId, String newStatus) {
        String sql = "UPDATE leaverequest SET Status = ? WHERE LeaveID = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, newStatus);
                ps.setInt(2, leaveId);
                int updated = ps.executeUpdate();
                if (updated > 0) {
                    JOptionPane.showMessageDialog(this,
                        "Leave ID " + leaveId + " marked " + newStatus + ".");
                    loadRequests();
                } else {
                    JOptionPane.showMessageDialog(this,
                        "No rows updated. Maybe it was already processed?");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error updating status:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewLeaveRequests::new);
    }
}
