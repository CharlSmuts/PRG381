package lib;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

    public Connection conn()
    {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Users?currentSchema=users","postgres","1234");
            if (conn != null)
            {
                System.out.println("connected");
            }
            else
            {
                System.out.println("failed");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return conn;
    }
}

