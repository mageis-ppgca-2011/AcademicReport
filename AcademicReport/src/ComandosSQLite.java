import java.sql.*;


public class ComandosSQLite {

	/**
	 * @param args
	 */
	public void example() {
		Connection connection = null;
	    ResultSet resultSet = null;
	    Statement statement = null;

	    try {
	        Class.forName("org.sqlite.JDBC");
	        connection = DriverManager.getConnection("jdbc:sqlite:src/zotero.sqlite");
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery("SELECT fileType FROM fileTypes");
	        while (resultSet.next()) {
	            System.out.println("fileTypes: "
	                    + resultSet.getString("fileType"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {  
	        try {
	            resultSet.close();
	            statement.close();
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

}
