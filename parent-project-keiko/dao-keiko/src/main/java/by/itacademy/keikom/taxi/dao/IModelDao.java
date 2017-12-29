package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Model;

public interface IModelDao {

	Integer create(Model model);

	void delete(Integer id);

	void update(Model model);

	Model getById(Integer id);

	List<Model> getAll();
}
