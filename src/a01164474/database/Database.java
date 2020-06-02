package a01164474.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import a01164474.database.Database;
import a01164474.database.DbConstants;

/**
 * Project: Lab7
 * @author Nathan Souza, A01164474
 */
public class Database {
	
	private static Logger LOG = LogManager.getLogger();

	private static Connection connection;
	private final Properties properties = new Properties();
	private static Database instance;

	public Database() throws FileNotFoundException, IOException {
		LOG.debug("Loading database properties from db.properties");
		properties.load(new FileInputStream(DbConstants.DB_PROPERTIES_FILENAME));
	}
	
	public static Database getInstance() throws FileNotFoundException, IOException {
		if (instance == null)
			instance = new Database();
		return instance;
	}

	public Connection getConnection() throws SQLException {
		if (connection != null) {
			return connection;
		}

		try {
			connect();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}

		return connection;
	}

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName(properties.getProperty(DbConstants.DB_DRIVER_KEY));
		System.out.println("Driver loaded");
		connection = DriverManager.getConnection(properties.getProperty(DbConstants.DB_URL_KEY), properties.getProperty(DbConstants.DB_USER_KEY),
				properties.getProperty(DbConstants.DB_PASSWORD_KEY));
		LOG.debug("Database connected");
	}

	public void shutdown() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean tableExists(String tableName) throws SQLException, FileNotFoundException, IOException {
		DatabaseMetaData databaseMetaData = Database.getInstance().getConnection().getMetaData();
		ResultSet resultSet = null;
		String rsTableName = null;

		try {
			resultSet = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
			while (resultSet.next()) {
				rsTableName = resultSet.getString("TABLE_NAME");
				if (rsTableName.equalsIgnoreCase(tableName)) {
					return true;
				}
			}
		} finally {
			resultSet.close();
		}

		return false;
	}	
}
