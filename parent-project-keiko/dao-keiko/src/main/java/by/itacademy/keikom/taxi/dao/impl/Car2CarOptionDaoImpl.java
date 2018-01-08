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

import by.itacademy.keikom.taxi.dao.ICar2CarOptionDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.dbmodel.Car2CarOption;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

public class Car2CarOptionDaoImpl extends AbstractDaoImpl implements ICar2CarOptionDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(Car2CarOptionDaoImpl.class);
	private static Car2CarOptionDaoImpl instance = null;

	private Car2CarOptionDaoImpl() {
	}

	public static synchronized Car2CarOptionDaoImpl getInstance() {
		if (instance == null) {
			instance = new Car2CarOptionDaoImpl();
		}
		return instance;
	}

	@Override
	public Car2CarOption create(Car2CarOption obj) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into car_2_car_option(car_id,car_option_id)values (?,?);",
						Statement.RETURN_GENERATED_KEYS)) {

			LOGGER.info("execute SQL: create new Car2CarOption");
			pst.setInt(1, obj.getCarId());
			pst.setInt(2, obj.getCarOptionId());
			pst.executeUpdate();

			Car2CarOption result = new Car2CarOption();
			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			Integer carId = rs.getInt("car_id");
			Integer carOptionId = rs.getInt("car_option_id");
			result.setCarId(carId);
			result.setCarOptionId(carOptionId);
			return result;
		} catch (SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	public void delete(Car2CarOption obj) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect
						.prepareStatement("delete from car_2_car_option where car_id = ? and car_option_id = ?")) {
			LOGGER.info("execute SQL: delete the table entity Car2CarOption");

			pst.setInt(1, obj.getCarId());
			pst.setInt(2, obj.getCarOptionId());
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(Car2CarOption obj, Car2CarOption newObj) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect
						.prepareStatement("update car_2_car_option set car_id = ?, car_option_id = ?\r\n"
								+ "	where car_id = ? and car_option_id = ?")) {
			LOGGER.info("execute SQL: update the table entity Car2CarOption");

			pst.setInt(1, newObj.getCarId());
			pst.setInt(2, newObj.getCarOptionId());
			pst.setInt(3, obj.getCarId());
			pst.setInt(4, obj.getCarOptionId());
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public List<Car2CarOption> getAll() {

		List<Car2CarOption> list = new ArrayList<Car2CarOption>();

		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			LOGGER.info("execute SQL: show all entities from the table Car2CarOption");

			ResultSet rs = st.executeQuery("select * from car_2_car_option");
			while (rs.next()) {

				list.add(parseCar2Option(rs));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}

	@Override
	public List<Integer> getOptionsByCar(Integer carId) {

		List<Integer> list = new ArrayList<Integer>();
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"select car_2_car_option.car_option_id from car_2_car_option where car_id = ?")) {
			pst.setInt(1, carId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("carOptionId"));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll car {}", e.getMessage());
		}
		return list;
	}

	@Override
	public List<Integer> getCarsByOption(Integer carOptionId) {

		List<Integer> list = new ArrayList<Integer>();

		try (Connection connect = getConnection();
				PreparedStatement pst = connect
						.prepareStatement("select car_id from car_2_car_option where car_option_id = ?;")) {
			pst.setInt(1, carOptionId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("carId"));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll car {}", e.getMessage());
		}
		return list;
	}

	@Override
	public Car2CarOption getById(Car2CarOption obj) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect
						.prepareStatement("select * from car_2_car_option where car_id = ? and car_option_id = ?")) {
			pst.setInt(1, obj.getCarId());
			pst.setInt(2, obj.getCarOptionId());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return parseCar2Option(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById car {}", e.getMessage());
		}
		return null;
	}

	private Car2CarOption parseCar2Option(ResultSet rs) throws SQLException {
		Car2CarOption obj = new Car2CarOption();
		obj.setCarId(rs.getInt(1));
		obj.setCarOptionId(rs.getInt(2));
		return obj;
	}
}
