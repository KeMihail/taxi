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
import by.itacademy.keikom.taxi.dao.enums.ECarStatus;
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

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into car (user_id,release_year,model_id,legal_entity_id,status,created,modified)\r\n"
								+ "values (?,?,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS)) {
			LOGGER.info("execute SQL: Create new Car");

			pst.setInt(1, car.getUserId());
			pst.setInt(2, car.getReleaseYear());
			pst.setInt(3, car.getModelId());
			pst.setInt(4, car.getLegalEntityId());
			pst.setString(5, car.getStatus().toString());
			pst.setTimestamp(6, car.getCreated());
			pst.setTimestamp(7, car.getModified());
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
				PreparedStatement pst = connect.prepareStatement("delete from car where id = ?")) {
			LOGGER.info("execute SQL: delete Car");

			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(Car car) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect
						.prepareStatement("update car set user_id = ?, release_year = ?, model_id = ?, \r\n"
								+ "legal_entity_id = ?,status = ?, modified = ? where id = ?")) {
			LOGGER.info("execute SQL: update Car");

			pst.setInt(1, car.getUserId());
			pst.setInt(2, car.getReleaseYear());
			pst.setInt(3, car.getModelId());
			pst.setInt(4, car.getLegalEntityId());
			pst.setString(5, car.getStatus().toString());
			pst.setTimestamp(6, car.getModified());
			pst.setInt(7, car.getId());

			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public Car getById(Integer id) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from car where id = ?")) {
			LOGGER.info("execute SQL: show one Car");

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return parseCar(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<Car> getAll() {

		List<Car> list = new ArrayList<Car>();
		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			ResultSet rs = st.executeQuery("select * from car");
			LOGGER.info("execute SQL: show all Cars");

			while (rs.next()) {
				list.add(parseCar(rs));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}

	private Car parseCar(ResultSet rs) throws SQLException {
		Car car = new Car();
		car.setId(rs.getInt(1));
		car.setUserId(rs.getInt(2));
		car.setReleaseYear(rs.getInt(3));
		car.setModelId(rs.getInt(4));
		car.setLegalEntityId(rs.getInt(5));
		car.setStatus(ECarStatus.valueOf(rs.getString((6))));
		car.setCreated(rs.getTimestamp(7));
		car.setModified(rs.getTimestamp(8));
		return car;
	}
}
