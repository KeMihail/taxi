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

import by.itacademy.keikom.taxi.dao.ICarOptionDao;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

public class CarOptionDaoImpl extends AbstractDaoImpl implements ICarOptionDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarOptionDaoImpl.class);
	private static CarOptionDaoImpl instance = null;

	private CarOptionDaoImpl() {
	}

	public synchronized static CarOptionDaoImpl getInstance() {
		if (instance == null) {
			instance = new CarOptionDaoImpl();
		}
		return instance;
	}

	@Override
	public Integer create(CarOption carOption) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("insert into car_option(name,created) VALUES(?,?);",
						Statement.RETURN_GENERATED_KEYS)) {
			LOGGER.info("execute SQL: create new option to car");
			pst.setString(1, carOption.getName());
			pst.setTimestamp(2, carOption.getCreated());
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
		// delete from car_option where id = ?;

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select carOption_delete(?);")) {
			LOGGER.info("execute SQL: delete one option to car");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(CarOption carOption) {
		// update car_option set name = ?, modified = ? where id = ?;

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select carOption_update(?,?,?);")) {
			LOGGER.info("execute SQL: update one option to car");
			pst.setInt(1, carOption.getId());
			pst.setString(2, carOption.getName());
			pst.setTimestamp(3, carOption.getModified());
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public CarOption getById(Integer id) {
		// select * from car_option where id = ?;

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from carOption_getById(?);")) {
			LOGGER.info("execute SQL: show one option car");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new CarOption(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4));
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
		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			LOGGER.info("execute SQL: show all option car");
			ResultSet rs = st.executeQuery("select * from carOption_getAll();");
			while (rs.next()) {
				list.add(new CarOption(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4)));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
		return list;
	}

}
