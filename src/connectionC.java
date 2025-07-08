import java.sql.Connection;
import java.sql.DriverManager;

public class connectionC {

    public Connection conn(String dbName, String User, String pw)
    {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=Users" + dbName, User, pw);
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
