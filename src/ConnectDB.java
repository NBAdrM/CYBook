import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * This class is for have a connexion to a MySQL database
 */
public class ConnectDB {
    private String url;
    private String user;
    private String pwd;

    /**
     * initilised parametre
     * @throws Exception
     */
    public ConnectDB() throws Exception{
        /*
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("config.properties");
        properties.load(fis);

        url = properties.getProperty("database.url");
        user = properties.getProperty("database.user");
        pwd = properties.getProperty("database.password");
        */
        url = "jdbc:mysql://localhost:3308/cybook?serverTimezone=UTC";
        user = "root";
        pwd = "";
    }

    /**
     * Do a request to a database
     * @param request SQL request
     * @return String of return request
     * @throws Exception
     */
    public String RequestSelectDB(String request) throws Exception{
        Connection conn = DriverManager.getConnection(url, user, pwd);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(request);

        String output="";
        while (rs.next()) {
            output += rs.getString("a");
            output += "/";
        }

        rs.close();
        stmt.close();
        conn.close();

        return output;
    }

    public void RequestInsertDB(String request) throws Exception{
        Connection conn = DriverManager.getConnection(url, user, pwd);
        Statement stmt = conn.createStatement();

        stmt.executeUpdate(request);

        stmt.close();
        conn.close();
    }

}
