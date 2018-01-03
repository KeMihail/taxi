package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Order;
import by.itacademy.keikom.taxi.dao.impl.OrderDaoImpl;
import by.itacademy.keikom.taxi.services.IOrder;

public class OrderServicesImpl implements IOrder {

	private static OrderDaoImpl dao = OrderDaoImpl.getInstance();
	private static OrderServicesImpl instance = null;

	private OrderServicesImpl() {
	}

	public static synchronized OrderServicesImpl getInstance() {
		if (instance == null) {
			instance = new OrderServicesImpl();
		}
		return instance;
	}

	@Override
	public Order save(Order order) {

		if (order.getId() != null) {
			order.setModified(new Timestamp(new Date().getTime()));
			dao.update(order);
		} else {
			order.setCreated(new Timestamp(new Date().getTime()));
			Integer id = dao.create(order);
			order.setId(id);
		}
		return order;
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public Order getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<Order> getAll() {
		return dao.getAll();
	}
}
