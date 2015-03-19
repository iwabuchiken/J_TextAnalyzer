package ta.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class TA {

	private static String JDBC_CONNECTION_URL = 
            "jdbc:oracle:thin:SCOTT/TIGER@localhost:1500:MyDB";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] done", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber());

		System.out.println(msg);
		
		msg = String.format(Locale.JAPAN, "[%s : %d] msg", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber());

		System.out.println(msg);
	
		CSVLoader loader = new CSVLoader(getCon());
		
	}

	private static Connection getCon() {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(JDBC_CONNECTION_URL);
 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return connection;
    }
	
}
