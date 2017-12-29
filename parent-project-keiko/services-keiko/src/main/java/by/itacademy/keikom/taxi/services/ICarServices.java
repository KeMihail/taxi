package by.itacademy.keikom.taxi.services;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Car;

public interface ICarServices {

	Car save(Car car);

	void delete(Integer id);

	Car getById(Integer id);

	List<Car> getAll();
}
