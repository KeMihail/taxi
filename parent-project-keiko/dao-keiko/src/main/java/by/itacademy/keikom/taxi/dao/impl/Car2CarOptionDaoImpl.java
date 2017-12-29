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
import by.itacademy.keikom.taxi.dao.dbmodel.Car2CarOption;
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
	public Integer create(Car2CarOption obj) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into car_2_car_option(car_id,car_option_id)values (?,?);",
						Statement.RETURN_GENERATED_KEYS)) {
			LOGGER.info("execute SQL: create new Car2CarOption");
			pst.setInt(1, obj.getCarId());
			pst.setInt(2, obj.getCarOptionId());
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
	public void delete(Car2CarOption obj) {
		// delete from car_2_car_option where car_id = ? and car_option_id = ?;

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select car2CarOption_delete(?,?)")) {
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
		// update car_2_car_option set car_id = ?(3), car_option_id = ?(4)
		// where car_id = ?(1) and car_option_id = ?(2);

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select car2CarOption_update(?,?,?,?);")) {
			LOGGER.info("execute SQL: update the table entity Car2CarOption");
			pst.setInt(1, obj.getCarId());
			pst.setInt(2, obj.getCarOptionId());
			pst.setInt(3, newObj.getCarId());
			pst.setInt(4, newObj.getCarOptionId());
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public Car2CarOption getById(Car2CarOption obj) {
		// select * from car_2_car_option where car_id = ? and _car_option_id = ?

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from car2CarOption_getById(?,?);")) {
			LOGGER.info("execute SQL: show one entity from the table Car2CarOption");
			pst.setInt(1, obj.getCarId());
			pst.setInt(2, obj.getCarOptionId());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new Car2CarOption(rs.getInt(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<Car2CarOption> getAll() {

		List<Car2CarOption> list = new ArrayList<Car2CarOption>();

		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			LOGGER.info("execute SQL: show all entities from the table Car2CarOption");
			ResultSet rs = st.executeQuery("select * from car2CarOption_getAll();");
			while (rs.next()) {
				list.add(new Car2CarOption(rs.getInt(1), rs.getInt(2)));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method hetAll {}", e.getMessage());
		}
		return list;
	}
}