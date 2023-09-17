package cms.dbtask;
import java.sql.*;
public class dbConnection 
{
	private static Connection con;
	public static Connection openConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_db1","root","root");
		}
		catch(ClassNotFoundException|SQLException cse)
		{
			System.out.println(cse);
		}
		return(con);
	}
	

}
