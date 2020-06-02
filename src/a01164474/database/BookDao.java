package a01164474.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import a01164474.data.Book;
import a01164474.database.Database;
import a01164474.database.DbConstants;

/**
 * Project: Lab7
 * @author Nathan Souza, A01164474
 */

public class BookDao extends Dao {

	public static final String TABLE_NAME = DbConstants.BOOK_TABLE_NAME;
	private static final Logger LOG = LogManager.getLogger();
	private static BookDao instance;
	
	public BookDao() throws FileNotFoundException, IOException {
		super(TABLE_NAME);
	}
	
	public static BookDao getInstance() throws FileNotFoundException, IOException {
		if (instance == null)
			instance = new BookDao();

		return instance;
	}

	@Override
	public void create() throws SQLException {		
		String sql = String.format(
				"create table %s(" // 1
						+ "%s VARCHAR(90), " // 2
						+ "%s VARCHAR(90), " // 3
						+ "%s VARCHAR(90), " // 4
						+ "%s VARCHAR(90), " // 5
						+ "%s VARCHAR(90), " // 6
						+ "%s VARCHAR(90), " // 7
						+ "%s VARCHAR(90), " // 8
						+ "%s VARCHAR(90), " // 9
						+ "primary key (%s) )", // 10
				tableName, // 1
				Fields.BOOK_ID.getName(), // 2
				Fields.ISBN.getName(), // 3
				Fields.AUTHORS.getName(), // 4
				Fields.TITLE.getName(), // 5
				Fields.PUBLICATION_YEAR.getName(), // 6
				Fields.AVERAGE_RATING.getName(), // 7
				Fields.RATINGS_COUNT.getName(), // 8
				Fields.IMAGE_URL.getName(), // 9
				Fields.BOOK_ID.getName()); // 10
		LOG.debug(sql);
		super.create(sql);
	}

	public void add(Book book) throws SQLException, FileNotFoundException, IOException {
		Statement statement = null;
		try {
			Connection connection = Database.getInstance().getConnection();
			statement = connection.createStatement();
			
			String sql = String.format(
					"insert into %s values(" // 1
							+ "'%s', " // 2
							+ "'%s', " // 3
							+ "'%s', " // 4
							+ "'%s', " // 5
							+ "'%s', " // 6 
							+ "'%s', " // 7
							+ "'%s', " // 8
							+ "'%s') ", // 9 
					tableName, // 1
					book.getId(), // 2
					book.getIsbn(), // 3
					book.getAuthors().replaceAll("'","''"), // 4
					book.getTitle().replaceAll("'","''"), // 5
					book.getPublicationYear(), // 6
					book.getAverageRating(), // 7
					book.getRatingsCount(), // 8
					book.getImageURL()); // 9
			LOG.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}

	public void delete(Book book) throws SQLException, FileNotFoundException, IOException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getInstance().getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sql = String.format("DELETE from %s WHERE %s='%s'", tableName, Fields.BOOK_ID.getName(), String.valueOf(book.getId()));
			LOG.debug(sql);
			int rowcount = statement.executeUpdate(sql);
			System.out.println(String.format("Deleted %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public ArrayList<Book> getBooks() throws SQLException, FileNotFoundException, IOException {
		Connection connection;
		Statement statement = null;
		ArrayList<Book> bookIds = new ArrayList<>();

		try {
			connection = Database.getInstance().getConnection();
			statement = connection.createStatement();
			String sql = String.format("SELECT * FROM %s", TABLE_NAME);
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				bookIds.add(new Book.Builder(Long.parseLong(resultSet.getString(Fields.BOOK_ID.getName())),//2
						resultSet.getString(Fields.ISBN.getName())) //3
								.setAuthors(resultSet.getString(Fields.AUTHORS.getName())) //4
								.setTitle(resultSet.getString(Fields.TITLE.getName())) //5
								.setPublicationYear(Integer.parseInt(resultSet.getString(Fields.PUBLICATION_YEAR.getName()))) //6
								.setAverageRating(Double.parseDouble(resultSet.getString(Fields.AVERAGE_RATING.getName()))) //7
								.setRatingsCount(Integer.parseInt(resultSet.getString(Fields.RATINGS_COUNT.getName()))) //8
								.setImageURL(resultSet.getString(Fields.IMAGE_URL.getName()))//10
								.build());
			}
		} finally {
			close(statement);
		}
		statement = connection.createStatement();
		return bookIds;
	}

	public enum Fields {

		BOOK_ID("bookID", "VARCHAR", 50, 1), //2
		ISBN("isbn", "VARCHAR", 50, 2), //3
		AUTHORS("authors", "VARCHAR", 50, 3), //4
		TITLE("title", "VARCHAR", 80, 4), //5
		PUBLICATION_YEAR("publicationYear", "VARCHAR", 50, 5), //6
		AVERAGE_RATING("averageRating", "VARCHAR", 50, 6), //7
		RATINGS_COUNT("ratingsCount", "VARCHAR", 50, 7), //8
		IMAGE_URL("imageURL", "VARCHAR", 70, 8); //9

		private final String name;
		private final String type;
		private final int length;
		private final int column;

		Fields(String name, String type, int length, int column) {
			this.name = name;
			this.type = type;
			this.length = length;
			this.column = column;
		}

		public String getType() {
			return type;
		}

		public String getName() {
			return name;
		}

		public int getLength() {
			return length;
		}

		public int getColumn() {
			return column;
		}
	}
}
