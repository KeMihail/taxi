package by.itacademy.keikom.taxi.services;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.User;

public interface IUserServices {

	User save(User user);

	void delete(Integer id);

	User getById(Integer id);

	List<User> getAll();
}
