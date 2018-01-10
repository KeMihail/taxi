package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.ICarOptionDao;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.services.ICarOptionServices;

@Service
public class CarOptionServicesImpl implements ICarOptionServices {

	@Autowired
	private ICarOptionDao dao;

	@Override
	public CarOption save(CarOption carOption) {

		carOption.setModified(new Timestamp(new Date().getTime()));

		if (carOption.getId() != null) {
			dao.update(carOption);
		} else {
			carOption.setCreated(new Timestamp(new Date().getTime()));
			Integer id = dao.create(carOption);
			carOption.setId(id);
		}
		return carOption;
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public CarOption getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<CarOption> getAll() {
		return dao.getAll();
	}
}
