package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.IOrderDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Order;
import by.itacademy.keikom.taxi.services.IOrderServices;

@Service
public class OrderServicesImpl implements IOrderServices {

	@Autowired
	private IOrderDao dao;

	@Override
	public Order save(Order order) {

		order.setModified(new Timestamp(new Date().getTime()));

		if (order.getId() != null) {
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
