package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.User;

public interface IUserDao {

	Integer create(User user);

	void delete(Integer id);

	void update(User user);

	User getById(Integer id);

	List<User> getAll();
}
