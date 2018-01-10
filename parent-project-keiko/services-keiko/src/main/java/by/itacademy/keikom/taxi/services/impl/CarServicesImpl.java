package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.ICarDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.services.ICarServices;

@Service
public class CarServicesImpl implements ICarServices {

	@Autowired
	private ICarDao dao;

	@Override
	public Car save(Car car) {
		car.setModified(new Timestamp(new Date().getTime()));

		if (car.getId() != null) {
			dao.update(car);
		} else {
			car.setCreated(new Timestamp(new Date().getTime()));
			Integer id = dao.create(car);
			car.setId(id);
		}
		return car;
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public Car getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<Car> getAll() {
		return dao.getAll();
	}
}
