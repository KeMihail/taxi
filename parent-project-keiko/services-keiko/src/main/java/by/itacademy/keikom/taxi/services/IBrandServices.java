package by.itacademy.keikom.taxi.services;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;

public interface IBrandServices {

	Brand save(Brand brand);

	void delete(Integer id);

	Brand getById(Integer id);

	List<Brand> getAll();
}
