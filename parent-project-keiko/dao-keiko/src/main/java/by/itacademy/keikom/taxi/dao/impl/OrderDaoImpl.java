package by.itacademy.keikom.taxi.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.IOrderDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Order;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public Order getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
