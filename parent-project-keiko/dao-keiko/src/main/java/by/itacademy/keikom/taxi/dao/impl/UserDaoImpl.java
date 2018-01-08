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
import by.itacademy.keikom.taxi.dao.enums.UserRole;
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

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into \"user\"(name,last_name,birthday,address,phone_number,email,created,modified,role)\r\n"
								+ "values (?,?,?,?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS)) {
			LOGGER.info("execute SQL: create new User");

			pst.setString(1, user.getName());
			pst.setString(2, user.getLastName());
			pst.setTimestamp(3, user.getBirthday());
			pst.setString(4, user.getAddress());
			pst.setString(5, user.getPhoneNumber());
			pst.setString(6, user.getEmail());
			pst.setTimestamp(7, user.getCreated());
			pst.setTimestamp(8, user.getModified());
			pst.setString(9, user.getRole().toString());
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

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("delete from \"user\" where id = ?")) {
			LOGGER.info("execute SQL: delete User");

			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(User user) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect
						.prepareStatement("update \"user\" set name = ?, last_name = ?, birthday = ?, address = ?,\r\n"
								+ "phone_number = ?, email = ?, deleted = ?, modified = ?, role = ? where id = ?")) {

			LOGGER.info("execute SQL: update User");

			pst.setString(1, user.getName());
			pst.setString(2, user.getLastName());
			pst.setTimestamp(3, user.getBirthday());
			pst.setString(4, user.getAddress());
			pst.setString(5, user.getPhoneNumber());
			pst.setString(6, user.getEmail());
			pst.setBoolean(7, user.getDeleted());
			pst.setTimestamp(8, user.getModified());
			pst.setString(9, user.getRole().toString());
			pst.setInt(10, user.getId());
			pst.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public User getById(Integer id) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from \"user\" where id = ?;")) {
			LOGGER.info("execute SQL: show one User");

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				return parseUser(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		// select * from users;

		List<User> list = new ArrayList<User>();
		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			LOGGER.info("execute SQL: show all user");
			ResultSet rs = st.executeQuery("select * from users_getAll();");

			while (rs.next()) {
				list.add(parseUser(rs));
			}

		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}

	private User parseUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt(1));
		user.setName(rs.getString(2));
		user.setLastName(rs.getString(3));
		user.setBirthday(rs.getTimestamp(4));
		user.setAddress(rs.getString(5));
		user.setPhoneNumber(rs.getString(6));
		user.setEmail(rs.getString(7));
		user.setDeleted(rs.getBoolean(8));
		user.setCreated(rs.getTimestamp(9));
		user.setModified(rs.getTimestamp(10));
		user.setRole(UserRole.valueOf(rs.getString(11)));
		return user;
	}
}
