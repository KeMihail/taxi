package by.itacademy.keikom.taxi.services;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Model;

public interface IModelServices {

	Model save(Model model);

	void delete(Integer id);

	Model getById(Integer id);

	List<Model> getAll();
}
