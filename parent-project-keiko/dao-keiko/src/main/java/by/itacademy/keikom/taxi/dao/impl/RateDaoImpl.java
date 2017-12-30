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

import by.itacademy.keikom.taxi.dao.IRateDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

public class RateDaoImpl extends AbstractDaoImpl implements IRateDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(RateDaoImpl.class);
	private static RateDaoImpl instance = null;

	private RateDaoImpl() {
	}

	public static RateDaoImpl getInstance() {
		if (instance == null) {
			instance = new RateDaoImpl();
		}
		return instance;
	}

	@Override
	public Integer create(Rate rate) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into rate (name,price_landing,price_kilometr,price_minute_wait,created)"
								+ "values (?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS)) {
			LOGGER.info("execute SQL: create new rate");
			pst.setString(1, rate.getName());
			pst.setDouble(2, rate.getPriceLanding());
			pst.setDouble(3, rate.getPriceKilometr());
			pst.setDouble(4, rate.getPriceMinuteWait());
			pst.setTimestamp(5, rate.getCreated());
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
		// delete from rate where id = ?;

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select rate_delete(?);")) {
			LOGGER.info("execute SQL: delete one rate");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(Rate rate) {
		// update rate set name = ?, price_landing = ?, price_kilometr = ?,
		// price_minute_wait = ?, modified = ? where id = ?
		;
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select rate_update(?,?,?,?,?,?);")) {
			LOGGER.info("execute SQL: update one rate");
			pst.setInt(1, rate.getId());
			pst.setString(2, rate.getName());
			pst.setDouble(3, rate.getPriceLanding());
			pst.setDouble(4, rate.getPriceKilometr());
			pst.setDouble(5, rate.getPriceMinuteWait());
			pst.setTimestamp(6, rate.getModified());
			pst.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public Rate getById(Integer id) {
		// select * from rate where id = ?;

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from rate_getById(?);")) {
			LOGGER.info("execute SQL: show one rate");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new Rate(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5),
						rs.getTimestamp(6), rs.getTimestamp(7));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<Rate> getAll() {
		// select * from rate;

		List<Rate> list = new ArrayList<Rate>();

		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			ResultSet rs = st.executeQuery("select * from rate_getAll();");
			while (rs.next()) {
				list.add(new Rate(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5),
						rs.getTimestamp(6), rs.getTimestamp(7)));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}
}
