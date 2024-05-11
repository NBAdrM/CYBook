import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3308/cybook?serverTimezone=UTC";
        String user = "root";
        String pwd = "";

        try {
            Connection conn = DriverManager.getConnection(url, user, pwd);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM test");

            while (rs.next()) {
                System.out.println(rs.getString("a"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
