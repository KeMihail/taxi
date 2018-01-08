package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Authentication;

public interface IAuthenticationDao {

	void create(Authentication authentication);

	void delete(Integer id);

	void update(Authentication authentication);

	Authentication getById(Integer id);

	List<Authentication> getAll();
}
