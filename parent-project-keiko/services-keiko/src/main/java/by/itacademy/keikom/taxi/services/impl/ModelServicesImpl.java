package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.IModelDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.services.IModelServices;

@Service
public class ModelServicesImpl implements IModelServices {

	@Autowired
	private IModelDao dao;

	@Override
	public Model save(Model model) {

		model.setModified(new Timestamp(new Date().getTime()));

		if (model.getId() != null) {
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
