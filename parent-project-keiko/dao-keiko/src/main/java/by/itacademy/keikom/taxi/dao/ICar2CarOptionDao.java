package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Car2CarOption;

public interface ICar2CarOptionDao {

	Integer create(Car2CarOption obj);

	void delete(Car2CarOption obj);

	void update(Car2CarOption obj, Car2CarOption newObj);

	Car2CarOption getById(Car2CarOption obj);

	List<Car2CarOption> getAll();
}
