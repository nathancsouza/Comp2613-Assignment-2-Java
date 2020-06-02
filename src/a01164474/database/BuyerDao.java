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
import a01164474.data.Buyer;
import a01164474.database.Database;
import a01164474.database.DbConstants;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

public class BuyerDao extends Dao {

	public static final String TABLE_NAME = DbConstants.BUYER_TABLE_NAME;
	private static final Logger LOG = LogManager.getLogger();
	private static BuyerDao instance;
	
	public BuyerDao() throws FileNotFoundException, IOException {
		super(TABLE_NAME);
	}
	
	public static BuyerDao getInstance() throws FileNotFoundException, IOException {
		if (instance == null)
			instance = new BuyerDao();

		return instance;
	}

	@Override
	public void create() throws SQLException {
		String sql = String.format(
				"create table %s("
						+ "%s VARCHAR(50), " //
						+ "%s VARCHAR(50), " //
						+ "%s VARCHAR(90), " //
						+ "%s VARCHAR(50), " //
						+ "%s VARCHAR(50)) ", //
				tableName, 
				Fields.FIRST_NAME.getName(),
				Fields.LAST_NAME.getName(),
				Fields.TITLE.getName(),
				Fields.PRICE.getName(),
				Fields.CUSTOMER_ID.getName());
				LOG.debug(sql);
		super.create(sql);
	}
	
	public void add(Buyer buyer) throws SQLException, FileNotFoundException, IOException {
		Statement statement = null;
		try {
			Connection connection = Database.getInstance().getConnection();
			statement = connection.createStatement();
			
			String sql = String.format(
					"insert into %s values(" // 1
							+ "'%s', " // 2
							+ "'%s', " // 3
							+ "'%s', " // 4
							+ "'%s'," // 5
							+ "'%s')", // 5 
					tableName, // 1
					buyer.getFirstName(), // 2
					buyer.getLastName(), // 3
					buyer.getTitle().replaceAll("'","''"), // 4
					buyer.getPrice(), // 5
					buyer.getCustomerID()); // 6
			LOG.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}
	
	public Buyer getBuyerByID(String buyerID) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		Buyer buyer = null;		
		try {
			connection = Database.getInstance().getConnection();
			statement = connection.createStatement();
			// Execute a statement
			
			String sql = String.format("SELECT * FROM %s WHERE %s = '%s'", tableName, Fields.CUSTOMER_ID.getName(), buyerID);		
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {								
				buyer = new Buyer.Builder()
						.setFirstName(resultSet.getString(Fields.FIRST_NAME.getName()))
						.setLastName(resultSet.getString(Fields.LAST_NAME.getName()))
						.setTitle(resultSet.getString(Fields.TITLE.getName()))
						.setPrice(Double.parseDouble(resultSet.getString(Fields.PRICE.getName())))
						.setCustomerID(Long.parseLong(resultSet.getString(Fields.CUSTOMER_ID.getName())))
						.build();
			}
		} finally {
			close(statement);
		}

		return buyer;
	}

	public ArrayList<Buyer> getPurchases() throws SQLException, FileNotFoundException, IOException {
		Connection connection;
		Statement statement = null;
		ArrayList<Buyer> buyers = new ArrayList<>();

		try {
			connection = Database.getInstance().getConnection();
			statement = connection.createStatement();
			String sql = String.format("SELECT * FROM %s", TABLE_NAME);
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				buyers.add(new Buyer.Builder()
						.setFirstName(resultSet.getString(Fields.FIRST_NAME.getName()))
						.setLastName(resultSet.getString(Fields.LAST_NAME.getName()))
						.setTitle(resultSet.getString(Fields.TITLE.getName()))
						.setPrice(Double.parseDouble(resultSet.getString(Fields.PRICE.getName())))
						.setCustomerID(Long.parseLong(resultSet.getString(Fields.CUSTOMER_ID.getName())))
						.build());
			}
		} finally {
			close(statement);
		}
		statement = connection.createStatement();
		return buyers;
	}

	public enum Fields {

		FIRST_NAME("firstName", "VARCHAR", 50, 1), //2
		LAST_NAME("lastName", "VARCHAR", 50, 2), //3
		TITLE("title", "VARCHAR", 50, 3), //4
		PRICE("price", "VARCHAR", 50, 4), //5
		CUSTOMER_ID("customerID", "VARCHAR", 50, 5); //6
		

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
