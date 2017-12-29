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

import by.itacademy.keikom.taxi.dao.IUserDao;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

public class UserDaoImpl extends AbstractDaoImpl implements IUserDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	private static UserDaoImpl instance = null;

	private UserDaoImpl() {
	}

	public synchronized static UserDaoImpl getInstance() {
		if (instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}

	@Override
	public Integer create(User user) {
		LOGGER.debug("Create new User");
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into users(name,last_name,birthday,address,phone_number,email,created)\r\n"
								+ "values (?,?,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS)) {

			pst.setString(1, user.getName());
			pst.setString(2, user.getLastName());
			pst.setTimestamp(3, user.getBirthday());
			pst.setString(4, user.getAddress());
			pst.setString(5, user.getPhoneNumber());
			pst.setString(6, user.getEmail());
			pst.setTimestamp(7, user.getCreated());
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			Integer id = rs.getInt("id");

			return id;
		} catch (SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	public void delete(Integer id) {
		// delete from users where id = _id;

		LOGGER.debug("Delete User");
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select users_delete(?);")) {
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(User user) {
		// update users set name = ?, last_name = ?, birthday = ?, address = ?,
		// phone_number = ?, email = ?, modified = ? where id = ?(1);

		LOGGER.debug("Update user");
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select user_update(?,?,?,?,?,?,?,?);")) {

			pst.setInt(1, user.getId());
			pst.setString(2, user.getName());
			pst.setString(3, user.getLastName());
			pst.setTimestamp(4, user.getBirthday());
			pst.setString(5, user.getAddress());
			pst.setString(6, user.getPhoneNumber());
			pst.setString(7, user.getEmail());
			pst.setTimestamp(8, user.getModified());
			pst.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public User getById(Integer id) {
		// select * from user where id = ?;

		LOGGER.debug("show one user");
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from users_getById(?)")) {
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt(10), rs.getString(1), rs.getString(2), rs.getTimestamp(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getTimestamp(8), rs.getTimestamp(9));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		// select * from users;

		LOGGER.debug("show all Users");
		List<User> list = new ArrayList<User>();
		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			ResultSet rs = st.executeQuery("select * from users_getAll();");
			while (rs.next()) {
				list.add(new User(rs.getInt(10), rs.getString(1), rs.getString(2), rs.getTimestamp(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getTimestamp(8), rs.getTimestamp(9)));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}
}
