package a01164474.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import a01164474.database.Database;

/**
 * Project: Lab7
 * @author Nathan Souza, A01164474
 */

public abstract class Dao {

	protected final Database database;
	protected final String tableName;

	private static final Logger LOG = LogManager.getLogger();

	protected Dao(String tableName) throws FileNotFoundException, IOException {
		database = Database.getInstance();
		this.tableName = tableName;
	}

	public abstract void create() throws SQLException;

	protected void create(String sql) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			LOG.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}

	protected void add(String sql) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			LOG.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}

	public void drop() throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String sql = "drop table " + tableName;
			LOG.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}

	protected void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
