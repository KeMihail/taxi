package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Order;

public interface IOrderDao {

	Integer create(Order order);

	void delete(Integer id);

	void update(Order order);

	Order getById(Integer id);

	List<Order> getAll();
}
