package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.ICostsDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Costs;
import by.itacademy.keikom.taxi.services.ICostsServices;

@Service
public class CostsServicesImpl implements ICostsServices {

	@Autowired
	private ICostsDao dao;

	@Override
	public Costs save(Costs costs) {

		costs.setModified(new Timestamp(new Date().getTime()));

		if (costs.getCreated() != null) {
			dao.update(costs);
		} else {
			costs.setCreated(new Timestamp(new Date().getTime()));
			dao.create(costs);
		}
		return costs;
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public Costs getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<Costs> getAll() {
		return dao.getAll();
	}
}
