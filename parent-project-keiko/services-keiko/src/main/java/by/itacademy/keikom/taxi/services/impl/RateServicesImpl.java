package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.dao.impl.RateDaoImpl;
import by.itacademy.keikom.taxi.services.IRateServices;

public class RateServicesImpl implements IRateServices {

	private static RateServicesImpl instance = null;
	private static final RateDaoImpl dao = RateDaoImpl.getInstance();

	private RateServicesImpl() {

	}

	public static synchronized RateServicesImpl getInstance() {
		if (instance == null) {
			instance = new RateServicesImpl();
		}
		return instance;
	}

	@Override
	public Rate save(Rate rate) {
		if (rate.getId() == null) {
			rate.setCreated(new Timestamp(new Date().getTime()));
			Integer id = dao.create(rate);
			rate.setId(id);
		} else {
			rate.setModified(new Timestamp(new Date().getTime()));
			dao.update(rate);
		}
		return rate;
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public Rate getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<Rate> getAll() {
		return dao.getAll();
	}

}
