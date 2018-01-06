package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.impl.BrandDaoImpl;
import by.itacademy.keikom.taxi.services.IBrandServices;

public class BrandServicesImpl implements IBrandServices {

	private static BrandDaoImpl dao = BrandDaoImpl.getInstance();
	private static BrandServicesImpl instance = null;

	private BrandServicesImpl() {
	}

	public static synchronized BrandServicesImpl getInstance() {
		if (instance == null) {
			instance = new BrandServicesImpl();
		}
		return instance;
	}

	@Override
	public Brand save(Brand brand) {

		brand.setModified(new Timestamp(new Date().getTime()));

		if (brand.getId() != null) {
			dao.update(brand);
		} else {
			brand.setCreated(new Timestamp(new Date().getTime()));
			Integer id = dao.create(brand);
			brand.setId(id);
		}
		return brand;
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public Brand getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<Brand> getAll() {
		return dao.getAll();
	}
}
