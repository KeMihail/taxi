package by.itacademy.keikom.taxi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Car2CarOption obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Car2CarOption getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car2CarOption> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
