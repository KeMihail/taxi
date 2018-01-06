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

import by.itacademy.keikom.taxi.dao.IOrderDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.dbmodel.Order;
import by.itacademy.keikom.taxi.dao.enums.EBodyType;
import by.itacademy.keikom.taxi.dao.enums.ECarKit;
import by.itacademy.keikom.taxi.dao.enums.EEngineType;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

public class OrderDaoImpl extends AbstractDaoImpl implements IOrderDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class);
	private static OrderDaoImpl instance = null;

	private OrderDaoImpl() {
	}

	public static synchronized OrderDaoImpl getInstance() {
		if (instance == null) {
			instance = new OrderDaoImpl();
		}
		return instance;
	}

	@Override
	public Integer create(Order order) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into \"order\" (car_id,user_id,order_time,order_begin,order_end,distance,summ,rate_id,departure_address,arrival_address,inactivity_minutes,created) \r\n"
								+ "values (?,?,?,?,?,order_sum(?,?,?),?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS)) {

			LOGGER.info("execute SQL: Create new order");
			pst.setInt(1, order.getCarId());
			pst.setInt(2, order.getUserId());
			pst.setTimestamp(3, order.getOrderBegin());
			pst.setTimestamp(4, order.getOrderEnd());
			pst.setDouble(5, order.getDistance());
			pst.setInt(6, order.getRateId());
			pst.setDouble(7, order.getDistance());
			pst.setDouble(8, order.getInactivityMinutes());
			pst.setInt(9, order.getRateId());
			pst.setString(10, order.getDepartureAddress());
			pst.setString(11, order.getArrivalAddress());
			pst.setDouble(12, order.getInactivityMinutes());
			pst.setTimestamp(13, order.getCreated());
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
				PreparedStatement pst = connect.prepareStatement("select order_delete(?);")) {
			LOGGER.info("execute SQL: Delete order");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(Order order) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select order_update(?,?,?,?,?,?,?,?,?,?,?);")) {
			LOGGER.info("execute SQL: Update order");
			pst.setInt(1, order.getId());
			pst.setInt(2, order.getCarId());
			pst.setInt(3, order.getUserId());
			pst.setTimestamp(4, order.getOrderBegin());
			pst.setTimestamp(5, order.getOrderEnd());
			pst.setDouble(6, order.getDistance());
			pst.setInt(7, order.getRateId());
			pst.setString(8, order.getDepartureAddress());
			pst.setString(9, order.getArrivalAddress());
			pst.setDouble(10, order.getInactivityMinutes());
			pst.setTimestamp(11, order.getModified());
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public Order getById(Integer id) {
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from order_getById(?)")) {

			LOGGER.info("execute SQL: show one order");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5),
						rs.getTimestamp(6), rs.getDouble(7), rs.getDouble(8), rs.getInt(9), rs.getString(10),
						rs.getString(11), rs.getInt(12), rs.getBoolean(13), rs.getTimestamp(14), rs.getTimestamp(15));
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
			LOGGER.info("execute SQL: show all order");

			ResultSet rs = st.executeQuery("select * from order_getAll();");
			while (rs.next()) {
				list.add(new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5),
						rs.getTimestamp(6), rs.getDouble(7), rs.getDouble(8), rs.getInt(9), rs.getString(10),
						rs.getString(11), rs.getInt(12), rs.getBoolean(13), rs.getTimestamp(14), rs.getTimestamp(15)));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}
}
