package by.itacademy.keikom.taxi.services;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Costs;

public interface ICostsServices {

	Costs save(Costs costs);

	void delete(Integer id);

	Costs getById(Integer id);

	List<Costs> getAll();
}
