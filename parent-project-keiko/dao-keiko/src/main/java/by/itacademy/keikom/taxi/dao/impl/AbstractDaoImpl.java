package by.itacademy.keikom.taxi.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractDaoImpl {

	private Properties dbProperties = new Properties();
	private String DB_CONNECTION_STRING;

	public AbstractDaoImpl() {

		super();
		try {
			Class.forName("org.postgresql.Driver");

			final InputStream cpResource = this.getClass().getClassLoader()
					.getResourceAsStream("db.properties");
			dbProperties.load(cpResource);

			DB_CONNECTION_STRING = String.format("jdbc:postgresql://%s:%s/%s",
					dbProperties.getProperty("host"),
					dbProperties.getProperty("port"),
					dbProperties.getProperty("dbname"));

		} catch (ClassNotFoundException | IOException e) {

			throw new by.itacademy.keikom.taxi.dao.exeption.DBConfigLoadException(
					e);
		}

		/*
		 * try { Class.forName("org.postgresql.Driver");
		 * 
		 * dbProperties.load(new FileInputStream(
		 * "D:\\Interprise\\taxi\\parent-project-keiko\\dao-keiko\\src\\main\\resources\\db.properties"
		 * )); } catch (IOException e) { System.out.println(e.getMessage());
		 * 
		 * } catch (ClassNotFoundException e) {
		 * System.out.println(e.getMessage()); }
		 * 
		 * DB_CONNECTION_STRING = String.format("jdbc:postgresql://%s:%s/%s",
		 * dbProperties.getProperty("host"), dbProperties.getProperty("port"),
		 * dbProperties.getProperty("dbname"));
		 */
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_CONNECTION_STRING,
				dbProperties.getProperty("user"),
				dbProperties.getProperty("password"));
	}
}
