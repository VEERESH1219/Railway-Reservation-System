import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class preparedStmt {
	public static void main(String[] args) throws Exception {
		
		//1.Loading the Driver class
         Class.forName("com.mysql.cj.jdbc.Driver");
         //2. Creating Connection
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manojdb", "root", "root");
         //3.Create Statement
         
}
