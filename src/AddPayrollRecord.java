import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddPayrollRecord extends JFrame {
  public AddPayrollRecord() {
    setTitle("Add Payroll Record");
    setSize(450, 350);
    setLayout(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    JLabel empLabel = new JLabel("Employee ID:");
    empLabel.setBounds(50, 40, 120, 25);
    add(empLabel);
    JTextField empField = new JTextField();
    empField.setBounds(180, 40, 180, 25);
    add(empField);

    JLabel basicLabel = new JLabel("Basic Salary:");
    basicLabel.setBounds(50, 80, 120, 25);
    add(basicLabel);
    JTextField basicField = new JTextField();
    basicField.setBounds(180, 80, 180, 25);
    add(basicField);

    JLabel bonusLabel = new JLabel("Bonuses:");
    bonusLabel.setBounds(50, 120, 120, 25);
    add(bonusLabel);
    JTextField bonusField = new JTextField();
    bonusField.setBounds(180, 120, 180, 25);
    add(bonusField);

    JLabel dedLabel = new JLabel("Deductions:");
    dedLabel.setBounds(50, 160, 120, 25);
    add(dedLabel);
    JTextField dedField = new JTextField();
    dedField.setBounds(180, 160, 180, 25);
    add(dedField);

    JLabel dateLabel = new JLabel("Payment Date (YYYY-MM-DD):");
    dateLabel.setBounds(50, 200, 200, 25);
    add(dateLabel);
    JTextField dateField = new JTextField(java.time.LocalDate.now().toString());
    dateField.setBounds(250, 200, 110, 25);
    add(dateField);

    JButton submit = new JButton("Add Payroll");
    submit.setBounds(150, 250, 140, 30);
    add(submit);

    submit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/employeemanagement",
            "root",
            "nigger"
          );

          BigDecimal basic   = new BigDecimal(basicField.getText().trim());
          BigDecimal bonus   = new BigDecimal(bonusField.getText().trim());
          BigDecimal ded     = new BigDecimal(dedField.getText().trim());
          BigDecimal net     = basic.add(bonus).subtract(ded);

          String sql = """
            INSERT INTO payroll
              (EmployeeID, BasicSalary, Bonuses, Deductions, NetSalary, PaymentDate, Status)
            VALUES (?,?,?,?,?,?,'Processed')
          """;

          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt        (1, Integer.parseInt(empField  .getText().trim()));
          ps.setBigDecimal (2, basic);
          ps.setBigDecimal (3, bonus);
          ps.setBigDecimal (4, ded);
          ps.setBigDecimal (5, net);
          ps.setDate       (6, java.sql.Date.valueOf(dateField.getText().trim()));

          int rows = ps.executeUpdate();
          if (rows > 0) {
            JOptionPane.showMessageDialog(
              AddPayrollRecord.this,
              "Payroll added successfully!"
            );
            dispose();
          } else {
            JOptionPane.showMessageDialog(
              AddPayrollRecord.this,
              "Failed to add payroll."
            );
          }
          con.close();
        } catch (Exception ex) {
          ex.printStackTrace();
          JOptionPane.showMessageDialog(
            AddPayrollRecord.this,
            "DB Error: " + ex.getMessage()
          );
        }
      }
    });

    setVisible(true);
  }
}
