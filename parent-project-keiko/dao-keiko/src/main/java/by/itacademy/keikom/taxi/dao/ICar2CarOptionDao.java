package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Car2CarOption;

public interface ICar2CarOptionDao {

	Integer create(Car2CarOption obj);

	void delete(Integer id);

	void update(Car2CarOption obj);

	Car2CarOption getById(Integer id);

	List<Car2CarOption> getAll();
}
