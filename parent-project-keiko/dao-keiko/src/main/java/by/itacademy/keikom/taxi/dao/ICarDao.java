package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Car;

public interface ICarDao {

	Integer create(Car car);

	void delete(Integer id);

	void update(Car car);

	Car getById(Integer id);

	List<Car> getAll();
}
