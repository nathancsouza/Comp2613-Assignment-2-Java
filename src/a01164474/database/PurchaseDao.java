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
import a01164474.data.Purchase;
import a01164474.database.Database;
import a01164474.database.DbConstants;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

public class PurchaseDao extends Dao {

	public static final String TABLE_NAME = DbConstants.PURCHASE_TABLE_NAME;
	private static final Logger LOG = LogManager.getLogger();
	private static PurchaseDao instance;
	
	public PurchaseDao() throws FileNotFoundException, IOException {
		super(TABLE_NAME);
	}
	
	public static PurchaseDao getInstance() throws FileNotFoundException, IOException {
		if (instance == null)
			instance = new PurchaseDao();

		return instance;
	}

	@Override
	public void create() throws SQLException {		
		String sql = String.format(
				"create table %s(" // 1
						+ "%s VARCHAR(50), " // 2
						+ "%s VARCHAR(50), " // 3
						+ "%s VARCHAR(50), " // 4
						+ "%s VARCHAR(50), " // 5
						+ "primary key (%s) )", // 6
				tableName, // 1
				Fields.PURCHASE_ID.getName(), // 2
				Fields.CUSTOMER_ID.getName(), // 3
				Fields.BOOK_ID.getName(), // 4
				Fields.PRICE.getName(),//5
				Fields.PURCHASE_ID.getName()); //6
		LOG.debug(sql);
		super.create(sql);
	}

	public void add(Purchase purchase) throws SQLException, FileNotFoundException, IOException {
		Statement statement = null;
		try {
			Connection connection = Database.getInstance().getConnection();
			statement = connection.createStatement();
			
			String sql = String.format(
					"insert into %s values(" // 1 tableName
							+ "'%s', " // 2 purchase ID
							+ "'%s', " // 3 customer ID
							+ "'%s', " // 4 book ID
							+ "'%s')",// 5 price
					tableName, // 1
					purchase.getPurchaseID(), // 2
					purchase.getCustomerID(), // 3
					purchase.getBookID(), // 4
					purchase.getPrice()); // 5
			LOG.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}
	
	public Purchase getPurchase(String purchaseID) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		Purchase purchase = null;		
		try {
			connection = Database.getInstance().getConnection();
			statement = connection.createStatement();			
			String sql = String.format("SELECT * FROM %s WHERE %s = '%s'", tableName, Fields.PURCHASE_ID.getName(), purchaseID);		
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet = statement.executeQuery(sql);
			int count = 0;
			while (resultSet.next()) {
				count++;
				if (count > 1) {
					throw new Exception(String.format("Expected one result, got %d", count));
				}				
				purchase = new Purchase.Builder(Long.parseLong(resultSet.getString(Fields.PURCHASE_ID.getName())), 
						Double.parseDouble(resultSet.getString(Fields.PRICE.getName())))
						.setCustomerID(Long.parseLong(resultSet.getString(Fields.CUSTOMER_ID.getName())))
						.setBookID(Long.parseLong(resultSet.getString(Fields.BOOK_ID.getName())))
						.build();
			}
		} finally {
			close(statement);
		}

		return purchase;
	}

	public void update(Purchase purchase) throws SQLException, FileNotFoundException, IOException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getInstance().getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sql = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', %s='%s' WHERE %s='%s'",
					tableName, // 1
					Fields.PURCHASE_ID.getName(), purchase.getPurchaseID(), // 2
					Fields.CUSTOMER_ID.getName(), purchase.getCustomerID(), // 3
					Fields.BOOK_ID.getName(), purchase.getBookID(), // 4
					Fields.PRICE.getName(), purchase.getPrice()); // 5
			LOG.debug(sql);
			int rowcount = statement.executeUpdate(sql);
			System.out.println(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public void delete(Purchase purchase) throws SQLException, FileNotFoundException, IOException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getInstance().getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sql = String.format("DELETE from %s WHERE %s='%s'", tableName, Fields.PURCHASE_ID.getName(), String.valueOf(purchase.getPurchaseID()));
			LOG.debug(sql);
			int rowcount = statement.executeUpdate(sql);
			System.out.println(String.format("Deleted %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public ArrayList<Purchase> getPurchases() throws SQLException, FileNotFoundException, IOException {
		Connection connection;
		Statement statement = null;
		ArrayList<Purchase> listOfpurchases = new ArrayList<>();

		try {
			connection = Database.getInstance().getConnection();
			statement = connection.createStatement();
			String sql = String.format("SELECT * FROM %s", TABLE_NAME);
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				listOfpurchases.add(new Purchase.Builder(Long.parseLong(resultSet.getString(Fields.PURCHASE_ID.getName())), 
								(Double.parseDouble(resultSet.getString(Fields.PRICE.getName()))))//2
								.setCustomerID(Long.parseLong(resultSet.getString(Fields.CUSTOMER_ID.getName()))) //4
								.setBookID(Long.parseLong(resultSet.getString(Fields.BOOK_ID.getName()))) //5
								.build());
			}
		} finally {
			close(statement);
		}
		statement = connection.createStatement();
		return listOfpurchases;
	}

	public enum Fields {

		PURCHASE_ID("purchaseID", "VARCHAR", 20, 1), //2
		CUSTOMER_ID("customerID", "VARCHAR", 20, 2), //3
		BOOK_ID("bookID", "VARCHAR", 20, 3), //4
		PRICE("price", "DOUBLE", 10, 4); //5
		

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
