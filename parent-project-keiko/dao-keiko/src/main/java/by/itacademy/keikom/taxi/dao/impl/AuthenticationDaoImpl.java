package by.itacademy.keikom.taxi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.IAuthenticationDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Authentication;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

public class AuthenticationDaoImpl extends AbstractDaoImpl implements IAuthenticationDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationDaoImpl.class);
	private static AuthenticationDaoImpl instance = null;

	private AuthenticationDaoImpl() {
	}

	public static synchronized AuthenticationDaoImpl getInstance() {
		if (instance == null) {
			instance = new AuthenticationDaoImpl();
		}
		return instance;
	}

	@Override
	public void create(Authentication authentication) {
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into authentication(user_id,login,password,created,modified) values (?,?,?,?,?)")) {
			LOGGER.info("execute SQL: create login and password to user");

			pst.setInt(1, authentication.getUserId());
			pst.setString(2, authentication.getLogin());
			pst.setString(3, authentication.getPassword());
			pst.setTimestamp(4, authentication.getCreated());
			pst.setTimestamp(5, authentication.getModified());
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	public void delete(Integer id) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("delete from authentication where user_id = ?")) {
			LOGGER.info("execute SQL: delete login and password to user");

			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(Authentication authentication) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"update authentication set login = ?, password = ?, modified = ? where user_id = ?")) {
			LOGGER.info("execute SQL: update login and password to user");

			pst.setString(1, authentication.getLogin());
			pst.setString(2, authentication.getPassword());
			pst.setTimestamp(3, authentication.getModified());
			pst.setInt(4, authentication.getUserId());
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public Authentication getById(Integer id) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from authentication where user_id = ?")) {
			LOGGER.info("execute SQL: show login and password to one user");

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				return parseAuthentication(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<Authentication> getAll() {

		List<Authentication> list = new ArrayList<Authentication>();
		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {

			LOGGER.info("execute SQL: show login and password to all users");
			ResultSet rs = st.executeQuery("select * from authentication");
			while (rs.next()) {
				list.add(parseAuthentication(rs));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}

	private Authentication parseAuthentication(ResultSet rs) throws SQLException {
		Authentication authentication = new Authentication();
		authentication.setUserId(rs.getInt(1));
		authentication.setLogin(rs.getString(2));
		authentication.setPassword(rs.getString(3));
		authentication.setCreated(rs.getTimestamp(4));
		authentication.setModified(rs.getTimestamp(5));

		return authentication;
	}
}
