package by.itacademy.keikom.taxi.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractDaoImpl {

	@Value("jdbc:postgresql://${host}:${port}/${dbname}")
	private String dbUrl;

	@Value("${user}")
	private String dbUser;
	@Value("${password}")
	private String dbPassword;

	@PostConstruct
	private void init() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new by.itacademy.keikom.taxi.dao.exeption.DBConfigLoadException(e);
		}

	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	}
}
