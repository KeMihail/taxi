package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;

public interface ICarOptionDao {

	Integer create(CarOption carOption);

	void delete(Integer id);

	void update(CarOption carOption);

	CarOption getById(Integer id);

	List<CarOption> getAll();
}
