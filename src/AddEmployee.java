import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; 

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AddEmployee extends JFrame {
    
    private static final String URL      = "jdbc:mysql://localhost:3306/employeemanagement";
    private static final String USER     = "root";
    private static final String PASSWORD = "nigger"; 

    public AddEmployee() {
        super("Add New Employee");
        setSize(500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        int y = 30, h = 30, gap = 40;

        JLabel idLbl   = new JLabel("Employee ID:");        idLbl.setBounds(30, y, 150, h); add(idLbl);
        JTextField idF = new JTextField();                  idF.setBounds(200, y, 240, h); add(idF);

        y += gap;
        JLabel fnLbl   = new JLabel("First Name:");         fnLbl.setBounds(30, y, 150, h); add(fnLbl);
        JTextField fnF = new JTextField();                  fnF.setBounds(200, y, 240, h); add(fnF);

        y += gap;
        JLabel lnLbl   = new JLabel("Last Name:");          lnLbl.setBounds(30, y, 150, h); add(lnLbl);
        JTextField lnF = new JTextField();                  lnF.setBounds(200, y, 240, h); add(lnF);

        y += gap;
        JLabel dobLbl  = new JLabel("DOB (YYYY-MM-DD):");   dobLbl.setBounds(30, y, 150, h); add(dobLbl);
        JTextField dobF= new JTextField();                  dobF.setBounds(200, y, 240, h); add(dobF);

        y += gap;
        JLabel genLbl  = new JLabel("Gender:");             genLbl.setBounds(30, y, 150, h); add(genLbl);
        JTextField genF= new JTextField();                  genF.setBounds(200, y, 240, h); add(genF);

        y += gap;
        JLabel cnoLbl  = new JLabel("Contact No.:");        cnoLbl.setBounds(30, y, 150, h); add(cnoLbl);
        JTextField cnoF= new JTextField();                  cnoF.setBounds(200, y, 240, h); add(cnoF);

        y += gap;
        JLabel emailLbl= new JLabel("Email:");              emailLbl.setBounds(30, y, 150, h); add(emailLbl);
        JTextField emailF=new JTextField();                 emailF.setBounds(200, y, 240, h); add(emailF);

        y += gap;
        JLabel addrLbl = new JLabel("Address:");            addrLbl.setBounds(30, y, 150, h); add(addrLbl);
        JTextField addrF=new JTextField();                  addrF.setBounds(200, y, 240, h); add(addrF);

        y += gap;
        JLabel hireLbl = new JLabel("Hire Date (YYYY-MM-DD):"); hireLbl.setBounds(30, y, 150, h); add(hireLbl);
        JTextField hireF= new JTextField();                  hireF.setBounds(200, y, 240, h); add(hireF);

        y += gap;
        JLabel jobLbl  = new JLabel("Job ID:");             jobLbl.setBounds(30, y, 150, h); add(jobLbl);
        JTextField jobF= new JTextField();                  jobF.setBounds(200, y, 240, h); add(jobF);

        y += gap;
        JLabel deptLbl = new JLabel("Dept ID:");            deptLbl.setBounds(30, y, 150, h); add(deptLbl);
        JTextField deptF= new JTextField();                 deptF.setBounds(200, y, 240, h); add(deptF);

        y += gap;
        JLabel mgrLbl  = new JLabel("Manager ID:");         mgrLbl.setBounds(30, y, 150, h); add(mgrLbl);
        JTextField mgrF= new JTextField();                  mgrF.setBounds(200, y, 240, h); add(mgrF);

        y += gap;
        JLabel titleLbl= new JLabel("Job Title:");          titleLbl.setBounds(30, y, 150, h); add(titleLbl);
        JTextField titleF=new JTextField();                 titleF.setBounds(200, y, 240, h); add(titleF);

        y += gap;
        JLabel salLbl  = new JLabel("Salary:");             salLbl.setBounds(30, y, 150, h); add(salLbl);
        JTextField salF= new JTextField();                  salF.setBounds(200, y, 240, h); add(salF);

        y += gap;
        JLabel statLbl = new JLabel("Employment Status:");  statLbl.setBounds(30, y, 150, h); add(statLbl);
        JTextField statF= new JTextField();                 statF.setBounds(200, y, 240, h); add(statF);

        y += gap;
        JLabel typeLbl = new JLabel("Employment Type:");    typeLbl.setBounds(30, y, 150, h); add(typeLbl);
        JTextField typeF= new JTextField();                 typeF.setBounds(200, y, 240, h); add(typeF);

        y += gap + 10;
        JButton submit = new JButton("Add Employee");
        submit.setBounds(180, y, 160, 30);  
        add(submit);

        submit.addActionListener(ev -> {
            String sql = "INSERT INTO employee "
                       + "(EmployeeID, FirstName, LastName, DOB, Gender, ContactNumber, Email, Address, "
                       + "HireDate, JobID, DepartmentID, ManagerID, JobTitle, Salary, EmploymentStatus, EmploymentType) "
                       + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                     PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setInt(1, Integer.parseInt(idF.getText()));
                    ps.setString(2, fnF.getText());
                    ps.setString(3, lnF.getText());
                    ps.setString(4, dobF.getText());
                    ps.setString(5, genF.getText());
                    ps.setString(6, cnoF.getText());
                    ps.setString(7, emailF.getText());
                    ps.setString(8, addrF.getText());
                    ps.setString(9, hireF.getText());
                    ps.setInt(10, Integer.parseInt(jobF.getText()));
                    ps.setInt(11, Integer.parseInt(deptF.getText()));
                    ps.setInt(12, Integer.parseInt(mgrF.getText()));
                    ps.setString(13, titleF.getText());
                    ps.setBigDecimal(14, new BigDecimal(salF.getText()));
                    ps.setString(15, statF.getText());
                    ps.setString(16, typeF.getText());

                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(this, "Employee added successfully!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add employee.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddEmployee::new);
    }
}
