package by.itacademy.keikom.taxi.services;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Authentication;

public interface IAuthenticationServices {

	Authentication save(Authentication authentication);

	void delete(Integer id);

	Authentication getById(Integer id);

	List<Authentication> getAll();
}
