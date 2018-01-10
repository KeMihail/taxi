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

import by.itacademy.keikom.taxi.dao.IRateDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

@Repository
public class RateDaoImpl extends AbstractDaoImpl implements IRateDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(RateDaoImpl.class);

	@Override
	public Integer create(Rate rate) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into rate (name,price_landing,price_kilometr,price_minute_wait,created,modified)"
								+ "values (?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS)) {

			LOGGER.info("execute SQL: create new Rate");
			pst.setString(1, rate.getName());
			pst.setDouble(2, rate.getPriceLanding());
			pst.setDouble(3, rate.getPriceKilometr());
			pst.setDouble(4, rate.getPriceMinuteWait());
			pst.setTimestamp(5, rate.getCreated());
			pst.setTimestamp(6, rate.getModified());
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
				PreparedStatement pst = connect.prepareStatement("delete from rate where id = ?")) {
			LOGGER.info("execute SQL: delete one Rate");

			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(Rate rate) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect
						.prepareStatement("update rate set name = ?, price_landing = ?, price_kilometr = ?,\r\n"
								+ "price_minute_wait = ?, modified = ? where id = ?")) {
			LOGGER.info("execute SQL: update one Rate");

			pst.setString(1, rate.getName());
			pst.setDouble(2, rate.getPriceLanding());
			pst.setDouble(3, rate.getPriceKilometr());
			pst.setDouble(4, rate.getPriceMinuteWait());
			pst.setTimestamp(5, rate.getModified());
			pst.setInt(6, rate.getId());
			pst.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public Rate getById(Integer id) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from rate where id = ?")) {
			LOGGER.info("execute SQL: show one Rate");

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return parseRate(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<Rate> getAll() {

		List<Rate> list = new ArrayList<Rate>();

		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			ResultSet rs = st.executeQuery("select * from rate");
			LOGGER.info("execute SQL: show all Rate");

			while (rs.next()) {
				list.add(parseRate(rs));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}

	private Rate parseRate(ResultSet rs) throws SQLException {
		Rate rate = new Rate();
		rate.setId(rs.getInt(1));
		rate.setName(rs.getString(2));
		rate.setPriceLanding(rs.getDouble(3));
		rate.setPriceKilometr(rs.getDouble(4));
		rate.setPriceMinuteWait(rs.getDouble(5));
		rate.setCreated(rs.getTimestamp(6));
		rate.setModified(rs.getTimestamp(7));
		return rate;
	}
}
