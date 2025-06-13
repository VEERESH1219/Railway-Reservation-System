import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class jdbcDemo {

				public static void main(String[] args) throws Exception {
					
					//1.Loading the Driver class
			         Class.forName("com.mysql.cj.jdbc.Driver");
			         //2. Creating Connection
			         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manojdb", "root", "root");
			         //3.Create Statement
			         Statement st = con.createStatement();
			         /*Scanner sc= new Scanner(System.in);
			         System.out.println("enter the query");
			         String sqlquery=sc.nextLine();
			         boolean b=st.execute(sqlquery);
			         if(b==true) {
			        	 ResultSet rs=st.getResultSet();
			        	 while(rs.next()) {
				        	  System.out.println(rs.getInt(1)+" "+rs.getString(2));
				        	  System.out.println("--------------------------------------------------------------------------");
			        	 }
			         }
			         else {
			        	 int updateCount=st.executeUpdate(sqlquery);
			        	 System.out.println(updateCount);
					}
			         //Defining the query and execute the query
			        // String sql_queryString="INSERT INTO stu VALUES(2,'kumar')";
			        // int updateCount=st.executeUpdate(sql_queryString);
			        // System.out.println(updateCount);
			        /* Scanner sc= new Scanner(System.in);
			         while(true) {
			        	 System.out.println(" Enter the student id");
			        	 
			        	 int stid=sc.nextInt();
			        	 System.out.println(" Enter the student stname");
			        	 String stname=sc.next();
			        	 
			        	 String sql_QueryString=String.format("INSERT INTO stu VALUES(%d,'%s')",stid,stname);
			        	 st.executeUpdate(sql_QueryString);
			        	 System.out.println(" Record insert succesfully");
			        	 System.out.println(" Do U want to insert one more column[YES/NO]");
			        	 String opt = sc.next();
			        	 if(opt.equalsIgnoreCase("NO"))
			        	 {
			        		 break;
			        		 
			        	 }
			         }
			          */
			          
			          //printed the o/p
			         /* while(set.next()) {
			        	  System.out.println(set.getInt(1)+" "+set.getString(2));
			        	  System.out.println("--------------------------------------------------------------------------");
			          }*/
			          
			         // set.close();
			       
			          st.close();
			        con.close();
			        


		
		
	}

}
