package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Rate;

public interface IRateDao {

	Integer create(Rate rate);

	void delete(Integer id);

	void update(Rate rate);

	Rate getById(Integer id);

	List<Rate> getAll();
}
