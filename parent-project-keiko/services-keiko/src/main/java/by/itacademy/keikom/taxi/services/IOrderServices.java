package by.itacademy.keikom.taxi.services;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Order;

public interface IOrderServices {

	Order save(Order order);

	void delete(Integer id);

	Order getById(Integer id);

	List<Order> getAll();
}
