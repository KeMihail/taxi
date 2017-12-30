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
	public Integer create(Authentication authentication) {
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into authentication(user_id,login,password,created) values (?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS)) {
			LOGGER.info("execute SQL: create login and password to user");
			pst.setInt(1, authentication.getUserId());
			pst.setString(2, authentication.getLogin());
			pst.setString(3, authentication.getPassword());
			pst.setTimestamp(4, authentication.getCreated());
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			Integer id = rs.getInt("user_id");
			return id;
		} catch (SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	public void delete(Integer id) {
		// delete from authentication where user_id = ?;

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select authentication_delete(?);")) {
			LOGGER.info("execute SQL: delete login and password to user");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(Authentication authentication) {
		// update authentication set login = ?, password = ?, modified = ? where user_id
		// = ?(1)

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select authentication_update(?,?,?,?);")) {
			LOGGER.info("execute SQL: update login and password to user");
			pst.setInt(1, authentication.getUserId());
			pst.setString(2, authentication.getLogin());
			pst.setString(3, authentication.getPassword());
			pst.setTimestamp(4, authentication.getModified());
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public Authentication getById(Integer id) {
		// select * from authentication where user_id = ?;

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from authentication_getById(?);")) {
			LOGGER.info("execute SQL: show login and password to one user");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new Authentication(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4),
						rs.getTimestamp(5));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<Authentication> getAll() {
		// select * from authentication;

		List<Authentication> list = new ArrayList<Authentication>();
		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {

			LOGGER.info("execute SQL: show login and password to all users");
			ResultSet rs = st.executeQuery("select * from authentication_getByAll();");
			while (rs.next()) {
				list.add(new Authentication(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4),
						rs.getTimestamp(5)));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}
}
