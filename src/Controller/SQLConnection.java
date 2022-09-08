package Controller;
import java.sql.*;

public class SQLConnection {


        public static Connection connectdb()
        {
            try
            {
//                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/swingapp","root","");
                return conn;
            }
            catch(Exception e)
            {
                System.out.println(e+ "Here");
                return null;
            }
        }


}

