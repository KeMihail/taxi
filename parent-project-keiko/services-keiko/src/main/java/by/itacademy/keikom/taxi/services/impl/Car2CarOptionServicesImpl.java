package by.itacademy.keikom.taxi.services.impl;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Car2CarOption;
import by.itacademy.keikom.taxi.dao.impl.Car2CarOptionDaoImpl;
import by.itacademy.keikom.taxi.services.ICar2CarOptionServices;

public class Car2CarOptionServicesImpl implements ICar2CarOptionServices {

	private static Car2CarOptionDaoImpl dao = Car2CarOptionDaoImpl.getInstance();
	private static Car2CarOptionServicesImpl instance = null;

	private Car2CarOptionServicesImpl() {
	}

	public static synchronized Car2CarOptionServicesImpl getInstance() {
		if (instance == null) {
			instance = new Car2CarOptionServicesImpl();
		}
		return instance;
	}

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
