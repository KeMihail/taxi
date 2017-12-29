package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.impl.CarDaoImpl;
import by.itacademy.keikom.taxi.services.ICarServices;

public class CarServicesImpl implements ICarServices {

	private static CarDaoImpl dao = CarDaoImpl.getInstance();
	private static CarServicesImpl instance = null;

	private CarServicesImpl() {
	}

	public static synchronized CarServicesImpl getInstance() {
		if (instance == null) {
			instance = new CarServicesImpl();
		}
		return instance;
	}

	@Override
	public Car save(Car car) {
		if (car.getId() == null) {
			car.setCreated(new Timestamp(new Date().getTime()));
			Integer id = dao.create(car);
			car.setId(id);
		} else {
			car.setModified(new Timestamp(new Date().getTime()));
			dao.update(car);
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
