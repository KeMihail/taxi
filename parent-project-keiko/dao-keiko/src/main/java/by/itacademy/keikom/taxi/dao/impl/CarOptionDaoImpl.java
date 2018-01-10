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
import org.springframework.stereotype.Repository;

import by.itacademy.keikom.taxi.dao.ICarOptionDao;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

@Repository
public class CarOptionDaoImpl extends AbstractDaoImpl implements ICarOptionDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CarOptionDaoImpl.class);

	@Override
	public Integer create(CarOption carOption) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect
						.prepareStatement(
								"insert into car_option(name,created,modified) VALUES(?,?,?)",
								Statement.RETURN_GENERATED_KEYS)) {
			LOGGER.info("execute SQL: create new option to Car");

			pst.setString(1, carOption.getName());
			pst.setTimestamp(2, carOption.getCreated());
			pst.setTimestamp(3, carOption.getModified());
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			Integer id = rs.getInt("id");
			return id;
		} catch (Exception e) {
			new SQLExecutionException(e);
		}
		return null;
	}

	@Override
	public void delete(Integer id) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect
						.prepareStatement("delete from car_option where id = ?")) {
			LOGGER.info("execute SQL: delete one option to Car");

			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(CarOption carOption) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect
						.prepareStatement("update car_option set name = ?, modified = ? where id = ?")) {
			LOGGER.info("execute SQL: update one option to Car");

			pst.setString(1, carOption.getName());
			pst.setTimestamp(2, carOption.getModified());
			pst.setInt(3, carOption.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public CarOption getById(Integer id) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect
						.prepareStatement("select * from car_option where id = ?")) {
			LOGGER.info("execute SQL: show one option Car");

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				return parseCarOption(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<CarOption> getAll() {
		// select * from car_option;

		List<CarOption> list = new ArrayList<CarOption>();
		try (Connection connect = getConnection();
				Statement st = connect.createStatement()) {
			LOGGER.info("execute SQL: show all option car");
			ResultSet rs = st.executeQuery("select * from carOption_getAll();");
			while (rs.next()) {
				list.add(parseCarOption(rs));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
		return list;
	}

	private CarOption parseCarOption(ResultSet rs) throws SQLException {
		CarOption carOption = new CarOption();
		carOption.setId(rs.getInt(1));
		carOption.setName(rs.getString(2));
		carOption.setCreated(rs.getTimestamp(3));
		carOption.setModified(rs.getTimestamp(4));
		return carOption;
	}
}
