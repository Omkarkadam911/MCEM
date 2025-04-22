import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employeemanagement", 
                "root",
                "nigger"
            );

            System.out.println(" Connected to the database!");
            con.close();

        } catch (Exception e) {
            System.out.println(" Connection failed:");
            e.printStackTrace();
        }
    }
}
