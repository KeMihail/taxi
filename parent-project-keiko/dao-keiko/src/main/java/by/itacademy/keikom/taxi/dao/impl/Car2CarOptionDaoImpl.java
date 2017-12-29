package by.itacademy.keikom.taxi.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.ICar2CarOptionDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Car2CarOption;

public class Car2CarOptionDaoImpl extends AbstractDaoImpl implements ICar2CarOptionDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(Car2CarOptionDaoImpl.class);
	private static Car2CarOptionDaoImpl instance = null;

	private Car2CarOptionDaoImpl() {
	}

	public static synchronized Car2CarOptionDaoImpl getInstance() {
		if (instance == null) {
			instance = new Car2CarOptionDaoImpl();
		}
		return instance;
	}

	@Override
	public Integer create(Car2CarOption obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Car2CarOption obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Car2CarOption getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car2CarOption> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
