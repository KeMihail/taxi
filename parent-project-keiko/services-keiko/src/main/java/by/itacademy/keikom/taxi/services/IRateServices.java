package by.itacademy.keikom.taxi.services;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Rate;

public interface IRateServices {
	Rate save(Rate rate);

	void delete(Integer id);

	Rate getById(Integer id);

	List<Rate> getAll();
}
