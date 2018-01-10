package by.itacademy.keikom.taxi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.ICar2CarOptionDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Car2CarOption;
import by.itacademy.keikom.taxi.services.ICar2CarOptionServices;

@Service
public class Car2CarOptionServicesImpl implements ICar2CarOptionServices {

	@Autowired
	private ICar2CarOptionDao dao;

	@Override
	public Car2CarOption create(Car2CarOption obj) {
		return dao.create(obj);
	}

	@Override
	public void update(Car2CarOption obj, Car2CarOption newObj) {
		dao.update(obj, newObj);
	}

	@Override
	public void delete(Car2CarOption obj) {
		dao.delete(obj);
	}

	@Override
	public List<Integer> getByIdOption(Integer carId) {
		return dao.getOptionsByCar(carId);
	}

	@Override
	public List<Integer> getByIdCar(Integer carOptionId) {
		return dao.getCarsByOption(carOptionId);
	}

	@Override
	public List<Car2CarOption> getAll() {
		return dao.getAll();
	}

	@Override
	public Car2CarOption getById(Car2CarOption obj) {
		return dao.getById(obj);
	}
}
