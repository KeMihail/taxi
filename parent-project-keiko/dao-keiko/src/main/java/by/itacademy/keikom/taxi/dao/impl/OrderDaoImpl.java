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

import by.itacademy.keikom.taxi.dao.IOrderDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Order;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

@Repository
public class OrderDaoImpl extends AbstractDaoImpl implements IOrderDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class);

	@Override
	public Integer create(Order order) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into \"order\"(car_id,user_id,departure_address,arrival_address,order_begin,order_end,distance_order,\r\n"
								+ "distance_paid,inactivity_minutes,rate_id,summ,created,modified) \r\n"
								+ "values(?,?,?,?,?,?,?,?,?,?,order_summ(?,?,?),?,?)",
						Statement.RETURN_GENERATED_KEYS)) {

			LOGGER.info("execute SQL: Create new Order");
			pst.setInt(1, order.getCarId());
			pst.setInt(2, order.getUserId());
			pst.setString(3, order.getDepartureAddress());
			pst.setString(4, order.getArrivalAddress());
			pst.setTimestamp(5, order.getOrderBegin());
			pst.setTimestamp(6, order.getOrderEnd());
			pst.setDouble(7, order.getDistanceOrder());
			pst.setDouble(8, order.getDistancePaid());
			pst.setInt(9, order.getInactivityMinutes());
			pst.setInt(10, order.getRateId());
			pst.setInt(11, order.getRateId());
			pst.setDouble(12, order.getDistancePaid());
			pst.setInt(13, order.getInactivityMinutes());
			pst.setTimestamp(14, order.getCreated());
			pst.setTimestamp(15, order.getModified());

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
				PreparedStatement pst = connect.prepareStatement("delete from \"order\" where id = ?")) {
			LOGGER.info("execute SQL: delete Order");

			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(Order order) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("update \"order\" \r\n"
						+ "set car_id = ?, user_id = ?, departure_address = ?,arrival_address = ?,\r\n"
						+ "order_begin = ?,order_end = ?,  distance_order = ?, distance_paid = ?, \r\n"
						+ "inactivity_minutes = ?, rate_id = ?, summ = order_summ(?,?,?),deleted = ?, modified = ?\r\n"
						+ "where id = ?")) {
			LOGGER.info("execute SQL: update Order");

			pst.setInt(1, order.getCarId());
			pst.setInt(2, order.getUserId());
			pst.setString(3, order.getDepartureAddress());
			pst.setString(4, order.getArrivalAddress());
			pst.setTimestamp(5, order.getOrderBegin());
			pst.setTimestamp(6, order.getOrderEnd());
			pst.setDouble(7, order.getDistanceOrder());
			pst.setDouble(8, order.getDistancePaid());
			pst.setInt(9, order.getInactivityMinutes());
			pst.setInt(10, order.getRateId());
			pst.setInt(11, order.getRateId());
			pst.setDouble(12, order.getDistancePaid());
			pst.setInt(13, order.getInactivityMinutes());
			pst.setBoolean(14, order.getDeleted());
			pst.setTimestamp(15, order.getModified());
			pst.setInt(16, order.getId());

			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public Order getById(Integer id) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from \"order\" where id = ?")) {
			LOGGER.info("execute SQL: show one Order");

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				return parseOrder(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<Order> getAll() {

		List<Order> list = new ArrayList<Order>();
		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			LOGGER.info("execute SQL: show all Order");

			ResultSet rs = st.executeQuery("select * from \"order\"");
			while (rs.next()) {

				list.add(parseOrder(rs));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}

	private Order parseOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getInt(1));
		order.setCarId(rs.getInt(2));
		order.setUserId(rs.getInt(3));
		order.setDepartureAddress(rs.getString(4));
		order.setArrivalAddress(rs.getString(5));
		order.setOrderBegin(rs.getTimestamp(6));
		order.setOrderEnd(rs.getTimestamp(7));
		order.setDistanceOrder(rs.getDouble(8));
		order.setDistancePaid(rs.getDouble(9));
		order.setInactivityMinutes(rs.getInt(10));
		order.setRateId(rs.getInt(11));
		order.setSumm(rs.getDouble(12));
		order.setDeleted(rs.getBoolean(13));
		order.setCreated(rs.getTimestamp(14));
		order.setModified(rs.getTimestamp(15));
		return order;
	}
}
