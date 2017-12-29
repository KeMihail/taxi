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

import by.itacademy.keikom.taxi.dao.ICarDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

public class CarDaoImpl extends AbstractDaoImpl implements ICarDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarDaoImpl.class);
	private static CarDaoImpl instance = null;

	private CarDaoImpl() {
	}

	public static synchronized CarDaoImpl getInstance() {
		if (instance == null) {
			instance = new CarDaoImpl();
		}
		return instance;
	}

	@Override
	public Integer create(Car car) {
		LOGGER.debug("Create new Car");
		try (Connection connect = getConnection();
				PreparedStatement pst = connect
						.prepareStatement("insert into car (user_id,release_year,model_id,legal_entity_id,created)\r\n"
								+ "values (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)) {
			pst.setInt(1, car.getUserId());
			pst.setInt(2, car.getReleaseYear());
			pst.setInt(3, car.getModelId());
			pst.setInt(4, car.getLegalEntityId());
			pst.setTimestamp(5, car.getCreated());
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
		// delete from car where id = ?;
		LOGGER.debug("Delete Car");
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select car_delete(?);")) {
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(Car car) {
		// --update car set user_id = _user_id, release_year = _release_year, model_id
		// =_model_id,
		// legal_entity_id = _legal_entity_id, modified = _modified where id = _id;

		LOGGER.debug("Update Car");
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select car_update(?,?,?,?,?,?);")) {
			pst.setInt(1, car.getId());
			pst.setInt(2, car.getUserId());
			pst.setInt(3, car.getReleaseYear());
			pst.setInt(4, car.getModelId());
			pst.setInt(5, car.getLegalEntityId());
			pst.setTimestamp(6, car.getModified());
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public Car getById(Integer id) {
		// select * from car where id = ?;

		LOGGER.debug("show one Car");
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from car_getById(?);")) {
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new Car(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getBoolean(6),
						rs.getBoolean(7), rs.getTimestamp(8), rs.getTimestamp(9));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<Car> getAll() {
		// select * from car;

		LOGGER.debug("show all ars");
		List<Car> list = new ArrayList<Car>();
		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			ResultSet rs = st.executeQuery("select * from car_getAll();");
			while (rs.next()) {
				list.add(new Car(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getBoolean(6),
						rs.getBoolean(7), rs.getTimestamp(8), rs.getTimestamp(9)));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}
}
