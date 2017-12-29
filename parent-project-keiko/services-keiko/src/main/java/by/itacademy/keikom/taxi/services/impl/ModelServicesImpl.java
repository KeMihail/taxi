package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.impl.ModelDaoImpl;
import by.itacademy.keikom.taxi.services.IModelServices;

public class ModelServicesImpl implements IModelServices {

	private ModelServicesImpl() {
	}

	private static ModelServicesImpl instance = null;

	public static synchronized ModelServicesImpl getInstance() {
		if (instance == null) {
			instance = new ModelServicesImpl();
		}
		return instance;
	}

	ModelDaoImpl dao = ModelDaoImpl.getInstance();

	@Override
	public Model save(Model model) {

		if (model.getId() != null) {
			model.setModified(new Timestamp(new Date().getTime()));
			dao.update(model);
		} else {
			model.setCreated(new Timestamp(new Date().getTime()));
			Integer id = dao.create(model);
			model.setId(id);
		}
		return model;
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public Model getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<Model> getAll() {
		return dao.getAll();
	}

}
