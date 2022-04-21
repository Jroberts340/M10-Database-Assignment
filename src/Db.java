import java.sql.*;
import java.util.ArrayList;

public class Db {
	
	public static Db getInstance()
	{
		return new Db();
	}
	
	public Connection getConnection() throws SQLException {

		Connection conn = null;
	try {
		String url = "jdbc:mysql://localhost:3306/word_occurrences?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "root";
		String pass = "Heaven23*";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, pass);
		System.out.println(String.format("Connection to database (%s) made " + "SUCCESSFULY.", conn.getCatalog()));

	} catch(Exception e) {
		System.out.println(e);
	}
	
		return conn;
	}

	public void addWord(String word, int freq) throws SQLException
	{ 
		Connection connect = Db.getInstance().getConnection();
		PreparedStatement prepSqlStatement = null;
		try {
			prepSqlStatement = connect.prepareStatement("INSERT INTO word (read_word, freq) VALUES (?, ?)");
			prepSqlStatement.setString(1, word);
			prepSqlStatement.setInt(2, freq);
			prepSqlStatement.executeUpdate();
		} 
		catch (SQLException ex) 
		{
			System.out.println("Word(s) could NOT be added to Database.");
			System.out.println(ex);
		}
		finally {
			System.out.println("Insertion COMPLETED");
		}
		
	}
	
	public void getWords() throws SQLException
	{
		Connection connect = Db.getInstance().getConnection();
		PreparedStatement statement = null;
		try {
			statement = connect.prepareStatement("SELECT * FROM word ORDER BY freq DESC");
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				System.out.print(String.format("Word: %s \t\t", result.getString("read_word")));
				System.out.println(String.format("Word Frequency: %s", result.getString("freq")));
			}
			
		} 
		catch (SQLException ex) {
		
		}
		finally {
		
		}
	}
}
