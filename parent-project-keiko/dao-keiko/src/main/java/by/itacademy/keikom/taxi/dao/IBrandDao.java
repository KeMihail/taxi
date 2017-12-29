package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;

public interface IBrandDao {

	Integer create(Brand brand);

	void delete(Integer id);

	void update(Brand brand);

	Brand getById(Integer id);

	List<Brand> getAll();
}
