package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.IBrandDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.services.IBrandServices;

@Service
public class BrandServicesImpl implements IBrandServices {

	@Autowired
	private static IBrandDao dao;

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
